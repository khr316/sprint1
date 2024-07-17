package com.project.sprint1.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.sprint1.dao.BoardDao;
import com.project.sprint1.dao.PostDao;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BoardController {
    @Autowired
    BoardDao boardDao;
    @Autowired
    PostDao postDao;

    @GetMapping("board/insert") // 글쓰기 페이지 주소
    public String boardInsert(HttpSession session,
                                Model model) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // html에서 쓸 수 있도록 모델에 (세션, 로그인 한)아이디 담아 보내기
        model.addAttribute("id", session.getAttribute("id"));
        
        // 어떤 물건인지 종류 카테고리 이름으로 담아서 html로 보내기
        List<Map<String, Object>> category = boardDao.category();
        model.addAttribute("category", category);
        
        return "board/insert"; // 글쓰기 html파일
    }

    // 글쓰기 페이지에 위치 등록하기위한 페이지 팝업창
    @GetMapping("board/location")
    public String boardLocation(@RequestParam(defaultValue = "") String dong,   // 디폴트 빈칸
                                Model model,
                                HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // 주소 테이블 가져와서 model에 담기
        List<Map<String, Object>> locList = boardDao.location(dong);
        model.addAttribute("locList", locList);

        return "board/location";
    }

    // 글쓰기 등록 누르면 진행되는 Action 
    // 글쓰기 페이지에서 보낸 input 값 받아서 데이터베이스(post 테이블)에 저장
    @PostMapping("board/insert/action")
    public String boardInsertAction(@RequestParam String id, // 아이디는 세션으로 받을거임
                                    @RequestParam String code, // 분실물 등록인지 습득물 등록인지 구분
                                    @RequestParam String title, // 제목
                                    @RequestParam String year, // 분실or습득 날짜
                                    @RequestParam String month, 
                                    @RequestParam String day,
                                    @RequestParam String location1, // 분실or습득 위치
                                    @RequestParam String location2,
                                    @RequestParam MultipartFile image, // 분실or습득물 이미지
                                    @RequestParam String category, // 분실or습득물 종류
                                    @RequestParam String itemName, // 분실or습득물 상세종류
                                    @RequestParam String content, // 글 내용
                                    HttpSession session) throws IOException {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        
        String date = year +"년"+ month +"월"+ day +"일"; // 하나의 값(날짜)으로 통합

        // 이미지 저장 경로 (vscode 누구 컴퓨터로 쓰는지에 따라 경로 수정 필수)
        String uploadPath = "C:/KEPCO/sprint1_김혜림/sprint1_김혜림/sprint1_최종최종최종/src/main/resources/static/img/";
        String filePath;

        // 이미지 등록 할 경우
        if (!image.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileName = uploadPath + uuid.toString() + "_" + image.getOriginalFilename();
            
            // 보여줄 이미지 (저장된 경로에서 꺼내오기)
            filePath = "/img/" + uuid.toString() + "_" + image.getOriginalFilename();
            File saveFile = new File(fileName);
            image.transferTo(saveFile);
        } else {
            // 이미지 등록 안 할 경우 기본 이미지 설정
            filePath = "/img/NO.png"; // 사진 파일을 넣지 않았을때 출력하는 이미지(기본 이미지)
        }

        // 데이터베이스에 값 등록 insert
        boardDao.boardInsert(id, code, title, date, location1, location2, 
                            filePath, category, itemName, content);

        // 메인페이지로 return 
        return "redirect:/main";
    }

    // 분실or습득물 상세종류 선택 창
    @GetMapping("board/category")
    public String categorySelect(Model model, 
                                // 글쓰기 페이지에서 선택한 종류의 코드 값 받기
                                @RequestParam String catCode,
                                HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        // 선택한 종류의 상세종류 리스트 model에 담아서 보내기
        List<Map<String, Object>> categoryDetail = boardDao.categoryDetail(catCode);
        model.addAttribute("categoryDetail", categoryDetail);
        
        // 상세종류 선택 창 return
        return "board/category";
    }

    // 게시글 상세 조회
    // 게시판에서 글 선택하면 글의 seq 를 보냄
    @GetMapping("detail")
    public String postDetail(@RequestParam int seq, 
                            Model model, 
                            HttpSession session) {

        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // 해당 seq의 게시글 상세 정보 model에 담기
        Map<String, Object> post = postDao.postDetail(seq);
        model.addAttribute("post", post);

        // 해당 seq의 게시글에 달린 댓글 목록 model에 담기
        List<Map<String, Object>> comments = postDao.commentSelect(seq);
        model.addAttribute("commentSet", comments);

        // 게시글 누를 때마다 조회수 1 증가
        boardDao.cntIncrease(seq);

        // detail 페이지에 댓글 작성 기능 존재
        // 댓글 작성자에 입력될 세션 아이디 model에 담기
        model.addAttribute("userId", session.getAttribute("id"));

        return "board/detail"; // 게시글 상세보기 페이지
    }

    // 게시글 수정 (글 작성자만 가능, 제목이랑 내용만)
    @PostMapping("/board/detail/update")
    public String postUpdate(@RequestParam int seq,
                            @RequestParam String title,
                            @RequestParam String content,
                            HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // 게시글 수정
        postDao.updatePost(seq, title, content);

        return "redirect:/detail?seq=" + seq;
    }

    // 게시글 삭제 (글 작성자, 관리자만 가능)
    @GetMapping("/board/detail/delete")
    public String postDelete(@RequestParam int seq, 
                            HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // 게시글 삭제
        postDao.deletePost(seq);

        return "redirect:/main";
    }

    // 댓글 작성
    @PostMapping("board/comment")
    public String addComment(@RequestParam String postSeq, // 댓글 작성할 글의 seq
                            @RequestParam String userId, // 댓글 작성자 아이디
                            @RequestParam String content, // 댓글 내용
                            HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        postDao.insertComment(postSeq, userId, content);

        return "redirect:/detail?seq=" + postSeq; // 댓글 작성 후 게시글 상세보기 페이지로 리다이렉트
    }

    // 댓글 수정 (댓글 작성자만 가능)
    @PostMapping("/board/comment/update")
    public String boardCommentEdit(@RequestParam String commentseq,
                                    @RequestParam String newContent,
                                    // 댓글 작성 후 게시글 상세보기 페이지로 리다이렉트
                                    // 댓글 단 글의 seq 필요
                                    @RequestParam String seq,
                                    HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        postDao.updateComment(newContent, commentseq);

        return "redirect:/detail?seq=" + seq;
    }

    // 댓글 삭제 (댓글 작성자, 관리자만 가능)
    @GetMapping("/board/comment/delete")
    public String boardCommentEdit(@RequestParam String commentseq, 
                                    @RequestParam String seq,
                                    HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        postDao.deleteComment(commentseq);

        return "redirect:/detail?seq=" + seq;
    }



}

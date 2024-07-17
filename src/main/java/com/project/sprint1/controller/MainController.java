package com.project.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sprint1.dao.MainDao;
import com.project.sprint1.dao.PostDao;

import java.util.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    MainDao mainDao;
    @Autowired
    PostDao postDao;

    @GetMapping("main") // 메인 페이지 (비로그인, 로그인 둘 다 접속 가능)
    public String main(HttpSession session, Model model) {
        // 세션에서 사용자 아이디 가져오기
        model.addAttribute("userId", session.getAttribute("userId"));

        return "main";
    }

    @GetMapping("/logout") // 로그아웃
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/main"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    // 습득물 게시판 페이지
    @GetMapping("/finditem")
    public String finditem(Model model, HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        // 세션 아이디가 있는 경우 (로그인 한 경우)
        if(session.getAttribute("id") != null){
            // 습득물 게시글 리스트 model에 담기
            List<Map<String, Object>> userposts = mainDao.selectUserFiPost();
            model.addAttribute("userfiposts", userposts);

            // 어느 지역인지 선택
            // 시도 리스트 model에 담기
            List<Map<String, Object>> sidoList = mainDao.selectSido();
            model.addAttribute("sidoList", sidoList);

            // 습득물 게시판으로
            return "menu/finditem";
        }
        // 세션 아이디가 없는 경우 메인으로
        return "redirect:/main";
    }

    // 분실물 게시판 페이지
    @GetMapping("/lostitem")
    public String lostitem(Model model, HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        // 세션 아이디가 있는 경우 (로그인 한 경우)
        if(session.getAttribute("id") != null){
            // 분실물 게시글 리스트 model에 담기
            List<Map<String, Object>> userposts = mainDao.selectUserLiPost();
            model.addAttribute("userliposts", userposts);

            // 어느 지역인지 선택
            // 시도 리스트 model에 담기
            List<Map<String, Object>> sidoList = mainDao.selectSido();
            model.addAttribute("sidoList", sidoList);
            
            // 분실물 게시판으로
            return "menu/lostitem";
        }
        // 세션 아이디가 없는 경우 메인으로
        return "redirect:/main";
    }

    // 제목,내용,지역 검색
    @GetMapping("/searchfi")
    public String searchFiPost(@RequestParam(defaultValue = "") String keyword, // 키워드
                               @RequestParam(defaultValue = "") String sido, // 지역
                               Model model,
                               HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        // 해당 키워드, 해당 지역 게시글 리스트 model에 담기
        // 해당 키워드도 model에 담기
        List<Map<String, Object>> searchResult = mainDao.selectSearchfiPost(keyword, sido);
        model.addAttribute("posts", searchResult);
        model.addAttribute("keyword", keyword);

        // 습득물 검색 페이지
        return "menu/searchfi";
    }

    // 동일
    @GetMapping("/searchli") 
    public String searchLiPost(@RequestParam(defaultValue = "") String keyword, 
                               @RequestParam(defaultValue = "") String sido,
                               Model model,
                               HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        List<Map<String, Object>> searchResult = mainDao.selectSearchLiPost(keyword, sido);
        model.addAttribute("posts", searchResult);
        model.addAttribute("keyword", keyword);

        return "menu/searchli";
    }

    // 내정보
    @GetMapping("/myinfo")
    public String myinfo() {
        return "menu/myinfo";
    }

    // 회원정보
    @GetMapping("/userinfo")
    public String userinfo() {
        return "menu/userinfo";
    }

}

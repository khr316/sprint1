package com.project.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sprint1.dao.MypageDao;
import com.project.sprint1.dao.MessageDao;

import jakarta.servlet.http.HttpSession;

import java.util.*;

@Controller
public class MypageController {
    @Autowired
    MypageDao mypageDao;
    @Autowired
    MessageDao messageDao;

    // 마이페이지 회원 정보 조회
    @GetMapping("mypage")
    public String mypage(HttpSession session,
                        Model model) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        
        // 세션 아이디 .toString() 스트링 자료형으로 변환
        String id = session.getAttribute("id").toString();
        // 아이디 model에 담기
        model.addAttribute("id", id);

        // 회원 정보 model에 담기
        Map<String, Object> userInfoSet = mypageDao.userInfoSelect(id);
        model.addAttribute("userInfoSet", userInfoSet);
        // 회원이 작성한 글 리스트 model에 담기
        List<Map<String, Object>> userBaordSet = mypageDao.userBoardSelect(id);
        model.addAttribute("userBaordSet", userBaordSet);

        // 회원에게 새로운 쪽지가 왔을 경우
        int messageCnt = messageDao.messageCnt(id);
        if (messageCnt > 0) {
            // 알림 표시 model에 담기
            model.addAttribute("messageCnt", "❗");
        }

        return "mypage";
    }

    // 마이페이지에서 탈퇴 누른 경우 진행되는 Action
    @GetMapping("delete/user")
    public String deleteUser(HttpSession session,
                            @RequestParam String id) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        
        // 데이터베이스에서 해당 아이디 삭제
        mypageDao.deleteUser(id);
        // 세션 종료 후
        session.invalidate();
        // 메인으로 return 
        return "redirect:/main";
    }

    // 마이페이지에서 전화번호 수정
    @GetMapping("update/user/phone")
    public String updateUserPhone(@RequestParam String phone,
                                  HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        // 세션 아이디 .toString() 스트링 자료형으로 변환
        String userId = session.getAttribute("id").toString();
        // 데이터베이스에 해당 아이디로 저장 된 전화번호 수정
        mypageDao.updatePhone(userId, phone);

        return "redirect:/mypage";
    }

    // 이메일 수정
    @GetMapping("update/user/email")
    public String updateUserEmail(@RequestParam String email,
                                  HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        String userId = session.getAttribute("id").toString();
        mypageDao.updateEmail(userId, email);

        return "redirect:/mypage";
    }

    // 비밀번호 수정
    @GetMapping("update/user/pw")
    public String updateUserPw(@RequestParam String pw,
                                HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        String userId = session.getAttribute("id").toString();
        mypageDao.updatePw(userId, pw);

        return "redirect:/mypage";
    }


    // 게시글 찾는 중 -> 찾기 완료 -> 찾는 중 -> 찾기 완료 ---
    // 버튼 누른 경우 게시글 상태 코드 업데이트 
    // 0이면 찾는 중, 1이면 찾기완료
    @GetMapping("find/check")
    public String findCheck(@RequestParam String find, 
                            @RequestParam String seq,
                            HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        mypageDao.findUpdate(find, seq);
        return "redirect:/mypage";
    }

    // -------------------------------------------------------------------
    // 비밀번호 찾기 기능
    @GetMapping("find-password")
    public String findPassword() {
        return "find-password";
    }
    @GetMapping("show-password")
    public String findShowPassword(@RequestParam String id, Model model) {
        String password = mypageDao.findPasswordSelect(id);

        if (password != null) {
            String showpassword = id + " 님의 비밀번호는 " + password + " 입니다.";
            model.addAttribute("showpassword", showpassword);
        } else {
            model.addAttribute("showpassword", id + " 로 가입된 회원이 없습니다.");
        }

        return "find-password";
    }    
    // -------------------------------------------------------------------
}

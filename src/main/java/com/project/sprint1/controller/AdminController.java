package com.project.sprint1.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.project.sprint1.dao.AdminDao;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



// AdminController

@Controller
public class AdminController {

    @Autowired
    AdminDao adminDao;

    // 관리자 페이지
    @GetMapping("admin")
    public String admin(HttpSession session, Model model) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id").toString();
        if (sessionId == null) {
            return "redirect:/login";
        }
        // 로그인 한 회원의 레벨
        int level = adminDao.levelSelect(sessionId); 
        // 레벨이 999인 경우 (레벨 999는 관리자임)
        if(level == 999){
            // 가입한 회원 목록 리스트 model에 담기
            List<Map<String,Object>> getAllUsers = adminDao.getAllUsers();
            model.addAttribute("users", getAllUsers);    
            // 작성된 게시글 리스트 model에 담기        
            List<Map<String,Object>> getAllPosts = adminDao.getAllPosts();
            model.addAttribute("posts", getAllPosts);
            // 관리자 페이지로
            return "adminpage";
        }
        else{ // 레벨이 999가 아닌 경우 메인으로
            return "redirect:/main";
        }
    }

    // 회원 삭제 기능 (탈퇴 시키기)
    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam String userId) {
        adminDao.deleteUser(userId);
        return "redirect:/admin";
    }

    // 게시글 삭제 기능
    @PostMapping("/admin/deletePost")
    public String deletePost(@RequestParam String postId) {
        // 게시글 seq
        adminDao.deletePost(postId);
        return "redirect:/admin";
    }
    


}

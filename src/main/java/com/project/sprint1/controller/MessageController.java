package com.project.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sprint1.dao.MessageDao;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MessageController {
    @Autowired
    MessageDao messageDao;
    
    // 쪽지 메인페이지
    @GetMapping("message")
    public String message(HttpSession session,
                          Model model){
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // 세션 아이디 스트링 자료형으로 변환
        String userId = session.getAttribute("id").toString();
        // 세션 아이디가 받은 쪽지 리스트 model에 담기
        List<Map<String,Object>> userList = messageDao.userSelect(userId);
        model.addAttribute("sessionId", userId);
        model.addAttribute("userList", userList);
        
        // 확인 안 한 새로운 쪽지가 있는 경우
        int messageCnt = messageDao.messageCnt(userId);
        if(messageCnt > 0){
            model.addAttribute("messageCnt", "❗");
        }

        return "message/message";
    }

    // 쪽지 보내기 페이지
    @GetMapping("message/send")
    public String messageSend(@RequestParam String userid,
                              HttpSession session,
                              Model model){
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        // 쪽지 받을 아이디
        model.addAttribute("userid", userid);
        // 쪽지 보낸 아이디
        model.addAttribute("sessionid", session.getAttribute("id"));
        return "message/message_insert";
    }

    // 새로운 쪽지 확인 기능
    // 새로운 쪽지가 오면 쪽지를 눌러야 확인 가능
    // 새로운 쪽지 0, 확인하면 1로 바꿔줌
    @GetMapping("message/check")
    public String messageCheck(@RequestParam String seq,
                                HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        messageDao.messageCheckUpdate(seq);
        return "redirect:/message/message_notice";
    }

    
    // 쪽지 삭제
    @GetMapping("message/delete")
    public String messageDelete(@RequestParam String seq,
                                HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
        // 해당 seq의 쪽지 데이터베이스에서 삭제
        messageDao.messageDelete(seq);
        
        return "redirect:/message/message_notice";
    }

    // 쪽지 보냈을 때 진행되는 Action
    @GetMapping("message/send/action")
    public String messageSendAction(@RequestParam String sendid, // 쪽지 보내는 아이디
                                    @RequestParam String receiveid, // 받는 아이디
                                    @RequestParam String messageContent,
                                    HttpSession session) { // 쪽지 내용
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }
                
        // 데이터베이스에 저장
        messageDao.insertMessage(sendid, receiveid, messageContent);

        return "redirect:/message";
    }

    // 쪽지 확인 창
    @GetMapping("message/message_notice")
    public String messageNotice(Model model,
                                HttpSession session) {
        // 로그인 안 한 경우 접속 불가
        String sessionId = (String) session.getAttribute("id");
        if (sessionId == null) {
            return "redirect:/login";
        }

        // 세션 아이디 .toString() 스트링 자료형으로 변환
        String id = session.getAttribute("id").toString();
        // 로그인 한 회원에게 온 쪽지 리스트 model에 담기
        List<Map<String, Object>> messageList = messageDao.messageSelect(id);
        model.addAttribute("messageList", messageList);

        // 로그인 한 회원이 보낸 쪽지 리스트 model에 담기
        List<Map<String, Object>> messageSendList = messageDao.messageSendSelect(id);
        model.addAttribute("messageSendList", messageSendList);

        return "message/message_notice";
    }
}
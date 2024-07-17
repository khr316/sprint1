package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MessageDao {
    @Autowired
    JdbcTemplate jt;

    // 쪽지 보내기 데이터베이스에 저장
    public void insertMessage(String sendid, // 보낸 아이디
                              String receiveid, // 받는 아이디
                              String messageContent) { // 내용
        String sqlStmt = "insert into message";
        sqlStmt += "(sendid,receiveid,message_content) ";
        sqlStmt += "values(?,?,?)";
        jt.update(sqlStmt, sendid, receiveid, messageContent);
    }

    // 쪽지 페이지에 나올 회원들 목록
    public List<Map<String,Object>> userSelect(String userId){
        String sqlStmt = "select seq,id,name ";
               sqlStmt += "from user ";
               // 관리자는 제외함
               sqlStmt += "where id != ? and id != 'admin'";
        return jt.queryForList(sqlStmt, userId);
    }

    // 받은 쪽지 리스트
    public int messageCnt(String userId){
        String sqlStmt = "select count(*) from message where receiveid = ? and messagecheck = 0";
        return jt.queryForObject(sqlStmt,int.class, userId);
    }

    // 쪽지 확인한 경우 1로 바뀌게 하기
    public void messageCheckUpdate(String seq) {
        String sqlStmt = "update message set messagecheck = 1 where seq = ?";
        jt.update(sqlStmt, seq);
    }
    
    // 쪽지 삭제
    public void messageDelete(String seq) {
        String sqlStmt = "delete from message where seq = ?";
        jt.update(sqlStmt, seq);
    }
    
    // 로그인 한 회원이 받은 쪽지
    public List<Map<String, Object>> messageSelect(String id) {
        String sqlStmt = "select seq, sendid, receiveid, message_content as message, reg_dt, messagecheck ";
        sqlStmt += "from message where receiveid = ?";
        return jt.queryForList(sqlStmt, id);
    }

    // 로그인 한 회원이 보낸 쪽지
    public List<Map<String, Object>> messageSendSelect(String id) {
        String sqlStmt = "select seq, sendid, receiveid, message_content as message, reg_dt ";
        sqlStmt += "from message where sendid = ?";
        return jt.queryForList(sqlStmt, id);
    }
    
} 
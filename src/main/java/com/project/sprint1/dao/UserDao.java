package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jt;

    // 회원가입 후 데이터베이스에 정보 저장
    public void signupAction(
            String userId,
            String userPw,
            String userName,
            String userPhone,
            String userEmail) {
        String sqlStmt = "INSERT INTO user(id, pw, name, phone, email) VALUES(?, ?, ?, ?, ?)";
        jt.update(sqlStmt, userId, userPw, userName, userPhone, userEmail);
    }
    // ID 중복체크
    public int dupIdCheck(String userId) {
        String sqlStmt = "SELECT COUNT(*) FROM user WHERE id = ?";
        return jt.queryForObject(sqlStmt, Integer.class, userId);
    }

    // 회원가입 된 ID PW인지 확인
    public Map<String, Object> login(String userId, String userPw) { 
        String sqlStmt = "SELECT * FROM user id = ? AND pw = ?";
        return jt.queryForMap(sqlStmt, userId, userPw);
    }

    // 회원 삭제
    public void deleteUser(String userId) { 
        String sqlStmt = "DELETE FROM user WHERE id = ?";
        jt.update(sqlStmt, userId);
    }

    // 비밀번호 일치 확인
    public List<Map<String, Object>> validatePassword(String userId, String userPw) {
        String sqlStmt = "SELECT id, pw FROM user WHERE id = ? AND pw = ?";
        return jt.queryForList(sqlStmt, userId, userPw);
    }

    // 로그인 했을 때 아이디 비번 동일한 회원 데이터베이스에 있는지 확인
    public int checkUser(String id, String pw) {
        String sqlStmt = "select count(*) from user where id = ? and pw = ?";
        return jt.queryForObject(sqlStmt, Integer.class, id, pw);
    }
}

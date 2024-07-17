package com.project.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// AdminDao

@Repository
public class AdminDao {

    @Autowired
    JdbcTemplate jt;

    // 로그인 한 아이디의 레벨
    public int levelSelect(String sessionId){
        String sqlStmt = "select level from user where id = ?";
        return jt.queryForObject(sqlStmt,int.class, sessionId);
    }

    // 모든 유저 조회
    public List<Map<String, Object>> getAllUsers() {
        String sqlStmt = "SELECT seq, id, pw, name, phone, email, level, reg_dt as regDt FROM user ";
        sqlStmt += "where level <> '999'";
        return jt.queryForList(sqlStmt);
    }

    // 모든 게시글 조회
    public List<Map<String, Object>> getAllPosts() {
        String sqlStmt = "SELECT seq,code, id, item_category as itemCategory, item_name as itemName,";
                    sqlStmt += " item_date as itemDate, item_image as itemImage, location1, location2,";
                    sqlStmt += " title, content, reg_dt as regDt FROM post";
        return jt.queryForList(sqlStmt);
    }

    // 관리자 권한으로 유저 정보 삭제
    public void deleteUser(String userId) {
        String sqlStmt = "DELETE FROM user WHERE id = ?";
        jt.update(sqlStmt, userId);
    }

    // 관리자 권한으로 게시글 삭제
    public void deletePost(String postId) {
        String sqlStmt = "DELETE FROM post WHERE seq = ?";
        jt.update(sqlStmt, postId);
    }
}

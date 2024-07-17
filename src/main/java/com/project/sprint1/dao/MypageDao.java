package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MypageDao {
    @Autowired
    JdbcTemplate jt;

    // 마이페이지에 나올 회원 정보
    public Map<String, Object> userInfoSelect(String id) {
        String sqlStmt = "select ";
        sqlStmt += "seq, ";
        sqlStmt += "id, ";
        sqlStmt += "pw, ";
        sqlStmt += "name, ";
        sqlStmt += " phone, ";
        sqlStmt += "email ";
        sqlStmt += "from user ";
        sqlStmt += "where id = ?";
        return jt.queryForMap(sqlStmt, id);
    }

    // 마이페이지에 나올 작성 글 리스트
    public List<Map<String, Object>> userBoardSelect(String id) {
        String sqlStmt = "select ";
        sqlStmt += "seq, ";
        sqlStmt += "code, ";
        sqlStmt += "title, ";
        sqlStmt += "reg_dt as regDt, find ";
        sqlStmt += "from post ";
        sqlStmt += "where id = ?";
        return jt.queryForList(sqlStmt, id);
    }

    // 탈퇴 기능
    // 데이터베이스에서 삭제
    public void deleteUser(String id) {
        String sqlStmt = "delete from user where id = ?";
        jt.update(sqlStmt, id);
    }

    // 전화번호 수정
    public void updatePhone(String userId, String phone) {
        String sqlStmt = "update user set phone = ? where id = '" + userId + "'";
        jt.update(sqlStmt, phone);
    }

    // 이메일 수정
    public void updateEmail(String userId, String email) {
        String sqlStmt = "update user set email = ? where id = '" + userId + "'";
        jt.update(sqlStmt, email);
    }

    // 비밀번호 수정
    public void updatePw(String userId, String pw) {
        String sqlStmt = "update user set pw = ? where id = '" + userId + "'";
        jt.update(sqlStmt, pw);
    }

    // 게시글 찾는 중, 찾기 완료 버튼 하나로 바뀌게 하는 기능
    public void findUpdate(String find, String seq) {
        String sqlStmt = "update post set find = ? where seq = ?";
        jt.update(sqlStmt, find, seq);
    }

    // -----------------------------------------------------------------
    // 비밀번호 찾기 기능
    public String findPasswordSelect(String id) {
        String sqlStmt = "select pw from user where id = ?";
        List<String> results = jt.query(sqlStmt, new Object[] { id }, (rs, rowNum) -> rs.getString("pw"));
        return results.isEmpty() ? null : results.get(0);
    }
    // -----------------------------------------------------------------
}

package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordDao {
    @Autowired
    JdbcTemplate jt;

    // 비밀번호 찾기에서 입력한 아이디 이메일이 데이터베이스에 있는지 확인
    public boolean checkUserExists(String id, String email) {
        String sqlStmt = "SELECT COUNT(*) FROM user WHERE id = ? AND email = ?";
        Integer count = jt.queryForObject(sqlStmt, new Object[] { id, email }, Integer.class);
        return count != null && count > 0;
    }

    // 발급된 임시비밀번호로 업데이트 
    public void updatePassword(String id, String newPassword) {
        String sqlStmt = "UPDATE user SET pw = ? WHERE id = ?";
        jt.update(sqlStmt, newPassword, id);
    }
}

package com.project.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {
    @Autowired
    JdbcTemplate jt;

    // 게시판에서 게시글 눌렀을 때 해당 글의 seq를 이용해 게시글 정보 조회
    public Map<String, Object> postDetail(int seq) {
        String sqlStmt = "SELECT seq,code, id, item_category as itemCategory, item_name as itemName,";
                    sqlStmt += " item_date as itemDate, item_image as itemImage, location1, location2,";
                    sqlStmt += " title, content, reg_dt as regDt FROM post WHERE seq = ?";
        return jt.queryForMap(sqlStmt, seq);
    }

    // 위 글에 등록된 댓글 목록 리스트
    public List<Map<String, Object>> commentSelect(int seq) {
        String sqlStmt = "SELECT seq,id, comment as commentContent, reg_dt as regDt ";
                    sqlStmt += "FROM comment WHERE board_seq = ?";
        return jt.queryForList(sqlStmt, seq);
    }

    // 게시글 수정 (제목, 내용)
    public void updatePost(int seq, String title, String content) {
        String sqlStmt = "UPDATE post SET title = ?, content = ? WHERE seq = ?";
        jt.update(sqlStmt, title, content, seq);
    }

    // 게시글 삭제
    public void deletePost(int seq) {
        String sqlStmt = "DELETE FROM post WHERE seq = ?";
        jt.update(sqlStmt, seq);
    }

    // 댓글 입력
    public void insertComment(String postSeq, String userId, String content) {
        String sqlStmt = "INSERT INTO comment (board_seq, id, comment) VALUES (?, ?, ?)";
        jt.update(sqlStmt, postSeq, userId, content);
    }

    // 댓글 삭제
    public void deleteComment(String seq) {
        String sqlStmt = "DELETE FROM comment WHERE seq = ?";
        jt.update(sqlStmt, seq);
    }

    // 댓글 수정
    public void updateComment(String newContent, String commentseq) {
    String sqlStmt = "update comment set comment = ? where seq = ?";
    jt.update(sqlStmt, newContent,commentseq);
    }

    
}
package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MainDao {
    @Autowired
    JdbcTemplate jt;

    // 습득물 게시판 게시물의 제목, 작성일자, 분류코드, 습득 상세 위치, 첨부이미지를 조희
    public List<Map<String, Object>> selectUserFiPost() {
        String sqlStmt = "SELECT seq,title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += "item_image as itemImage, cnt,find FROM post where code = 1 order by reg_dt desc";

        return jt.queryForList(sqlStmt);
    }

    // 분실물 게시판 게시물의 제목, 작성일자, 분류코드, 습득 상세 위치, 첨부이미지를 조회
    public List<Map<String, Object>> selectUserLiPost() {
        String sqlStmt = "SELECT seq,title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += "item_image as itemImage, cnt,find FROM post where code = 0 order by reg_dt desc";
        return jt.queryForList(sqlStmt);
    }

    // 주인을 찾아요! (습득물) 게시판에서 keyword(검색어)가 포함된 제목,내용, 습득 위치를 가진 게시글을 조회
    public List<Map<String, Object>> selectSearchfiPost(String keyword, String sido) {
        String sqlStmt = "SELECT seq, title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += "item_image as itemImage, cnt,find FROM post WHERE code = 1 ";
        sqlStmt += "and (title LIKE ? or content LIKE ?) ";
        sqlStmt += "and location1 like ? order by reg_dt desc";
        return jt.queryForList(sqlStmt, "%" + keyword + "%", "%" + keyword + "%", "%"+sido+"%");
    }

    // 잃어버렸나요? (분실물) 게시판에서 keyword(검색어)가 포함된 제목,내용, 분실 위치를 가진 게시글을 조회
    public List<Map<String, Object>> selectSearchLiPost(String keyword, String sido) {
        String sqlStmt = "SELECT seq, title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += "item_image as itemImage, cnt,find FROM post WHERE code = 0 ";
        sqlStmt += "and (title LIKE ? or content LIKE ?) ";
        sqlStmt += "and location1 like ? order by reg_dt desc";
        return jt.queryForList(sqlStmt, "%" + keyword + "%", "%" + keyword + "%", "%"+sido+"%");
    }

    // 지역 선택
    public List<Map<String,Object>> selectSido(){
        String sqlStmt = "select distinct sido from location";
        return jt.queryForList(sqlStmt);
    }
}

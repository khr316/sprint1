package com.project.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
    @Autowired
    JdbcTemplate jt;

    // 글쓰기에서 위치 검색 -> 검색한 dong이 포함된 지역 리스트
    public List<Map<String, Object>> location(String dong) {
        String sqlStmt = "select distinct ";
        sqlStmt += "sido,";
        sqlStmt += "gu,";
        sqlStmt += "dong, ";
        sqlStmt += "ri ";
        sqlStmt += "from location ";
        sqlStmt += "where dong like ?";
        return jt.queryForList(sqlStmt, dong);
    }

    // 글쓰기 작성 후 데이터베이스에 등록
    public void boardInsert(String id,
                            String code,
                            String title,
                            String date,
                            String location1,
                            String location2,
                            String filePath,
                            String category,
                            String itemName,
                            String content) {
        String sqlStmt = "INSERT INTO post(id, code, title, item_date, location1, location2, ";
        sqlStmt += "item_image, item_category, item_name, content) ";
        sqlStmt += "VALUES(?,?,?,?,?,?,?,?,?,?)";
        jt.update(sqlStmt, id, code, title, date, location1, location2, filePath, category, itemName,
                content);
    }

    // 분실or습득물 종류 리스트
    // 종류 코드, 종류 이름
    public List<Map<String,Object>> category(){
        String sqlStmt = "select category_code as catCode, category_name as catName ";
               sqlStmt += "from category";
        return jt.queryForList(sqlStmt);
    }
    // 분실or습득물 상세종류 리스트
    // 종류 코드, 상세종류 코드, 상세종류 이름 
    // 종류 코드 = 종류 select 했을 때 보내지는 value 값
    public List<Map<String,Object>> categoryDetail(String catCode){
        String sqlStmt = "select category_code as catCode, ";
               sqlStmt += "category_detail_code as catDeCode, ";
               sqlStmt += "category_detail as catDeName ";
               sqlStmt += "from category_detail ";
               sqlStmt += "where category_code = ?";
        return jt.queryForList(sqlStmt,catCode);
    }

    // 글 누를 때마다 조회수 1씩 증가
    public void cntIncrease(int seq){
        String sqlStmt = "update post set cnt = cnt+1 where seq = ?";
        jt.update(sqlStmt, seq);
    }

}

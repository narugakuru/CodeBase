package com.raisei.dao;

import com.raisei.pojo.Books;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
//增删改查
    int addBook(@Param("book") Books book);
    int deleteBook(@Param("bookID") int id);
    int updateBook(Books book);
    Books selectBookByID(@Param("bookID") int id);
//全查
    List<Books> queryAllBook();

}

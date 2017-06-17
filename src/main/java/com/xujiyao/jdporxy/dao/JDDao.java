package com.xujiyao.jdporxy.dao;

import com.xujiyao.jdporxy.eniity.JDEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 42224 on 2017/6/17.
 */
@Repository
public interface JDDao {

    List<JDEntity> findAllBooks();

    Integer deleteBookById(String bookID) ;

    Integer addBook(JDEntity entity);


    Integer batchInsertBook(List<JDEntity> entities);

}

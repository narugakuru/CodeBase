package com.raisei.dao;

import com.raisei.pojo.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student findUser();
    Student findById(int id);
    Student findByUserId(Student student);


}

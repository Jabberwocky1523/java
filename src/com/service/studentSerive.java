package com.service;

import java.util.List;

import com.bean.student;

public interface studentSerive {
    List<student> getStudents();
    student findByid(int id);
    int addstudent(student student);
    int deletestudent(student student);
    int updateStudent(student student);
}

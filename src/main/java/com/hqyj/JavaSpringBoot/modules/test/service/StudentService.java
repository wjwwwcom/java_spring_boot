package com.hqyj.JavaSpringBoot.modules.test.service;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.JavaSpringBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Result<Student> insertStudent(Student student);

    Student getStudentBystudentId(int studentId);

    Page<Student> getStudentByPage(SearchVo searchVo );

    List<Student> getByStudentName(String studentName,int cardId);

}

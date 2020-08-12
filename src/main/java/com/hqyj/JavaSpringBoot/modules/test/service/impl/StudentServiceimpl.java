package com.hqyj.JavaSpringBoot.modules.test.service.impl;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.test.entity.Student;
import com.hqyj.JavaSpringBoot.modules.test.repository.StudentRepository;
import com.hqyj.JavaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,"insertStudent success",student);
    }
}

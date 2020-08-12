package com.hqyj.JavaSpringBoot.modules.test.service;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.test.entity.Student;

public interface StudentService {

    Result<Student> insertStudent(Student student);
}

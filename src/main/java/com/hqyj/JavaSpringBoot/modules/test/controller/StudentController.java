package com.hqyj.JavaSpringBoot.modules.test.controller;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.test.entity.Student;
import com.hqyj.JavaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cpi")
public class StudentController {
    @Autowired
    private StudentService studentService;


    /*
    * 新增一条student记录
    * url:127.0.0.1/cpi/student ---post
    * json==>{"studentName":"zhangsan","studentCard":{"cardId":"1"}}
    */
     @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }
}

package com.hqyj.JavaSpringBoot.modules.test.controller;

import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.JavaSpringBoot.modules.test.entity.Student;
import com.hqyj.JavaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

    /*
    *   通过Id查询单个学生信息
    * url:127.0.0.1/cpi/astudent/1 ---get
    * */
    @GetMapping("/astudent/{studentId}")
    public Student getStudentBystudentId(@PathVariable int studentId) {
         return studentService.getStudentBystudentId(studentId);
    }

      /*
      * 脚本分页查询
      * url：127.0.0.1/cpi/sutdents ---post
      * json==>{"currentPage":"1","pageSize":"4","keyWord":"zh","orderBy":"studentName","sort":"asc"}
      * */
      @PostMapping(value = "/sutdents",consumes = "application/json")
    public Page<Student> getStudentByPage(@RequestBody SearchVo searchVo ) {
          return studentService.getStudentByPage(searchVo);
    }

    /*
    * 属性查询
    * url：127.0.0.1/cpi/bystudent?studentName=zhangsan ---get
    * */
     @GetMapping("/bystudent")
    public List<Student> getByStudentName(@RequestParam String studentName,@RequestParam(defaultValue = "0",required = false) int cardId) {
          return studentService.getByStudentName(studentName,cardId);
    }


}

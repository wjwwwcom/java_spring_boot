package com.hqyj.JavaSpringBoot.modules.test.service.impl;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.hqyj.JavaSpringBoot.modules.common.vo.Result;
import com.hqyj.JavaSpringBoot.modules.common.vo.SearchVo;
import com.hqyj.JavaSpringBoot.modules.test.entity.Student;
import com.hqyj.JavaSpringBoot.modules.test.repository.StudentRepository;
import com.hqyj.JavaSpringBoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Student getStudentBystudentId(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Page<Student> getStudentByPage(SearchVo searchVo ) {
        //选中排序字段
        String orderby = StringUtils.isBlank(searchVo.getOrderBy()) ? "studentId":searchVo.getOrderBy();
        //选择排序类型
        Sort.Direction direction= StringUtils.isBlank(searchVo.getSort()) ||
                searchVo.getSort().equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort=new Sort(direction,orderby);
        //创建一个分页对象
        Pageable pageable= PageRequest.of(searchVo.getCurrentPage()-1,searchVo.getPageSize(),sort);
           //创建一个模板（查询条件）Example对象
           Student student = new Student();
           student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher = ExampleMatcher.matching()
             //模糊查询，即%{studentName}%
            .withMatcher("studentName", match -> match.contains())
                // 忽略字段，即不管id是什么值都不加入查询条件
             .withIgnorePaths("studentId");
          Example<Student> example = Example.of(student,matcher);
        return studentRepository.findAll(example,pageable);
    }

    @Override
    public List<Student> getByStudentName(String studentName , int cardId) {

        if (cardId>0){
            return Optional.ofNullable(studentRepository.getStudentsByColumns(String.format("%s%s%s", "%", studentName, "%"),cardId)).orElse(Collections.emptyList());
        }else {
            //return Optional.ofNullable(studentRepository.findByStudentName(studentName)).orElse(Collections.emptyList());
            return Optional.ofNullable(studentRepository.findByStudentNameLike(String.format("%s%s%s", "%", studentName, "%"))).orElse(Collections.emptyList());
        }
    }


}

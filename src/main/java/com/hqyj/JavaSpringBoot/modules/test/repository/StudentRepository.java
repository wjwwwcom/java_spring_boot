package com.hqyj.JavaSpringBoot.modules.test.repository;

import com.hqyj.JavaSpringBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
     //根据字段查询
    List<Student> findByStudentName( String studentName);
    //字段模糊查询
    List<Student> findByStudentNameLike( String studentName);
    //两个字段以上联合查询
     //通过名称模糊及cardId 查询
  //  @Query(value = "select s from Student s where s.studentName like ?1 and s.studentCard.cardId = ?2")
   // @Query(value = "select s from Student s where s.studentName like :studentName and s.studentCard.cardId = :cardId")
      @Query(nativeQuery = true,value = "select * from h_student where student_name like :studentName and card_id =:cardId")
    List<Student> getStudentsByColumns(@Param("studentName") String studentName,@Param("cardId") int cardId);


}

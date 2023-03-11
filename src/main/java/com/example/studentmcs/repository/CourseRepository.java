package com.example.studentmcs.repository;

import com.example.studentmcs.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
//    @Query(value = "SELECT c FROM Course WHERE c.course_title LIKE CONCAT('%', :query, '%') OR c.course_id LIKE CONCAT('%', :query, '%')")
    @Query("SELECT c FROM Course c WHERE c.courseTitle LIKE CONCAT('%',:query,'%')")
    public List<Course> searchCourses(String query);
}

package vn.edu.hcmut.cse.adse.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmut.cse.adse.lab.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    // only need to write method's name, framework will automatically implement it into SQL queries.
    List<Student> findByNameContaining(String keyword);
//    @Query("SELECT MAX(id) FROM students") int getCurrentMaxId();
}
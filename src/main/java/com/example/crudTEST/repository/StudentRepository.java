package com.example.crudTEST.repository;

import com.example.crudTEST.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

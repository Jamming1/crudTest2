package com.develhope.crudtest1;

import com.develhope.crudtest1.entities.Student;
import com.develhope.crudtest1.repositories.StudentRepositories;
import com.develhope.crudtest1.service.StudentService;
import com.example.crudTEST.entity.Student;
import com.example.crudTEST.repository.StudentRepository;
import com.example.crudTEST.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Student student;

    @Test
    void checkStudentWorking() throws Exception {
        Student student = new Student();
        student.setWorking(true);
        student.setName("Jamming");
        student.setSurname("Parrillo");

        Student studentFromDB = studentRepository.save(student);
        assertThat(studentFromDB).isNotNull();
        assertThat(studentFromDB.getId()).isNotNull();

        Student studentFromService = studentService.setUserActivationStatus(student.getId(), true);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

    }
    @Test
    void findById(Student studentFromDB, Student studentFromService) {
        Student studentFromFind = studentRepository.findById(studentFromDB.getId()).get();
        assertThat(studentFromFind).isNotNull();
        assertThat(studentFromFind.getId()).isNotNull();
        assertThat(studentFromFind.getId()).isEqualTo(studentFromDB.getId());
        assertThat(studentFromService.isWorking()).isTrue();
    }
}
package com.example.crudTEST.controller;

import com.example.crudTEST.entity.Student;
import com.example.crudTEST.repository.StudentRepository;
import com.example.crudTEST.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//ha un Controller per le operazioni CRUD:
@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    //(1) crea un nuovo Student
    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student){
        Student newStudent = studentRepository.save(student);
       return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }
    //(2) per ottenere la lista di tutti gli Student
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    //(3) per prendere uno Student specifico passando primary key come path variable
    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Student> getSingle(@PathVariable Long id){

        Optional<Student> optionalStudent = studentRepository.findById(id);
        return ResponseEntity.of(optionalStudent);

    }
    //(4) per aggiornare uno Student
    @PostMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id,@RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.get().setName(student.getName());
        optionalStudent.get().setSurname(student.getSurname());
        return ResponseEntity.of(optionalStudent);
    }
    //(5) per aggiornare il valore isWorking value
    @PutMapping("/{id}/working")
    public ResponseEntity<Student> setStudentActivation(@PathVariable Long id, @RequestParam boolean working){

        studentService.setUserActivationStatus(id, working);
        return ResponseEntity.ok().build();
    }
    //(6) per cancellare uno Student
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

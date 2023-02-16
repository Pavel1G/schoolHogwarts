package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentInfo(@PathVariable long id) {
        var student = studentService.getStudent(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }

    @GetMapping()
    public Collection<Student> getAllStudentInfo() {
        return studentService.getAllStudent();
    }

    @GetMapping(path = "/student_parallel_threads")
    public void studentsWithParallelThreads(){
        studentService.getStudentsWithParallelThreads();
    }

    @GetMapping(path = "/student_parallel_threads_synchro")
    public void studentsWithParallelThreadsSynhro(){
        studentService.getStudentsWithParallelThreadsSynchro();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<String>> sortedStudents(){
        return ResponseEntity.ok(studentService.getSortedStudents());
    }

//    @GetMapping("/avarage_age")
//    public ResponseEntity<Double> averageAgeStudents(){
//        return ResponseEntity.ok(studentService.calculateAvarageAge());
//    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        var foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/filter/{age}")
    public ResponseEntity<Collection<Student>> findStudentsByAge(@PathVariable int age) {
        var students = studentService.getAllStudentsByAge(age);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<?> findStudentsByAgeBetween(@RequestParam int minAge,
                                                      @RequestParam int maxAge) {
        var students = studentService.findByAgeBetween(minAge, maxAge);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "/faculty")
    public ResponseEntity<?> findFacultyStudent(@RequestParam Long idStudent) {
        Faculty foundFaculty = studentService.findFacultByStudent(idStudent);
        if (foundFaculty == null) {
            return new ResponseEntity<>("Студент отчислен/не учится", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping("/count")
    public Integer countAllStudent() {
        return studentService.getCountAllStudent();
    }

    @GetMapping("/avarage_age")
    public String avarageAgeByStudents() {
        return studentService.getAvarageAgeByStudents();
    }

    @GetMapping("/last_five_student")
    public Collection<Student> lastFiveStudent() {
        return studentService.getLastFiveStudent();
    }
}

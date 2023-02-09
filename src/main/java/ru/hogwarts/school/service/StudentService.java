package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    // Add Logger
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);



    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.debug("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Optional<Student> getStudent(long id) {
        logger.debug("Was invoked method for get student by ID: {}", id);
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {
        logger.debug("Was invoked method for change student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Was invoked method for delete student by ID: {}", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudentsByAge(int age) {
        logger.debug("Was invoked method for get student by age: {}", age);
        return studentRepository.getByAge(age);
    }

    public Collection<Student> getAllStudent() {
        logger.debug("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.debug("Was invoked method for get student between min age {} and max age {}", minAge, maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Faculty findFacultByStudent(Long idStudent) {
        logger.debug("Was invoked method for get faculty by student ID: {}", idStudent);
        return studentRepository.findById(idStudent).get().getFaculty();
    }

    public Integer getCountAllStudent() {
        logger.debug("Was invoked method for get count student");
        return studentRepository.getCountAllStudent();
    }

    public String getAvarageAgeByStudents() {
        logger.debug("Was invoked method for get student's avarage age");
        Double age = studentRepository.getAvarageAgeByAllStudent();
        return new DecimalFormat("#.#").format(age);
    }

    public Collection<Student> getLastFiveStudent() {
        logger.debug("Was invoked method for get last 5 students in the repository");
        return studentRepository.getLastFiveStudent();
    }
}

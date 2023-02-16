package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

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

    public List<String> getSortedStudents() {
        logger.debug("Was invoked method for get students sorted by name");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public void getStudentsWithParallelThreads() {
        logger.debug("Was invoked method for get student's by parallels threads");

        List<String> studentList = studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getId))
                .map(Student::getName)
                .limit(6)
                .collect(Collectors.toList());
        System.out.println(studentList);
        System.out.println("-----------------------------");
        System.out.println(studentList.get(0));
        System.out.println(studentList.get(1));

        new Thread(() ->
        {
            System.out.println(studentList.get(2));
            System.out.println(studentList.get(3));
        }).start();

        new Thread(() ->
        {
            System.out.println(studentList.get(4));
            System.out.println(studentList.get(5));
        }).start();
    }

    public void getStudentsWithParallelThreadsSynchro() {
        logger.debug("Was invoked method for get student's by parallels threads (synchronized block)");

        List<String> studentList = studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getId))
                .map(Student::getName)
                .limit(6)
                .collect(Collectors.toList());
        System.out.println(studentList);
        System.out.println("-----------------------------");

        synchronized (this) {
            System.out.println(studentList.get(0));
            System.out.println(studentList.get(1));

            new Thread(() ->
            {
                System.out.println(studentList.get(2));
                System.out.println(studentList.get(3));
            }).start();

            new Thread(() ->
            {
                System.out.println(studentList.get(4));
                System.out.println(studentList.get(5));
            }).start();
        }
    }

//    public Double calculateAvarageAge() {
//        logger.debug("Was invoked method for get student's avarage age");
//        return studentRepository.findAll().stream()
//                .collect(averagingDouble(Student::getAge));
//    }


}

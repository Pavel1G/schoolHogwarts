package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private HashMap<Long, Student> students = new HashMap<>();
    private Long generateStudentID = 1L;

    public Student createStudent(Student student) {
        student.setId(generateStudentID++);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudentByID(Long studentID) {
        return students.get(studentID);
    }

    public Student updateStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            return null;
        }
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(Long studentID) {
        return students.remove(studentID);
    }

    public Collection<Student> getAll() {
        return students.values();
    }

    public Collection<Student> getStudentByAge(int ageStudent) {
        return students.values().stream()
                .filter(s -> s.getAge() == ageStudent)
                .toList();
    }
}

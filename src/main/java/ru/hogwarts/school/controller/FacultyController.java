package ru.hogwarts.school.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")

public class FacultyController {
    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;

    public FacultyController(FacultyService facultyService,
                             FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    //Работа с классом Page
    @GetMapping
    public Page<Faculty> getAllFaculty(@PageableDefault Pageable pageable) {
        return facultyService.getAllFaculty(pageable);
    }

    @GetMapping("{facultyID}")
    public ResponseEntity<?> getFaculty(@PathVariable Long facultyID) {
        return ResponseEntity.ok(facultyService.getFacultyByID(facultyID));
    }

    @GetMapping(path = "find")
    public ResponseEntity<?> findFaculty(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultiesByColorContainsIgnoreCase(name, color));
    }

    @GetMapping(path = "faculty")
    public ResponseEntity<?> findFaculty(@RequestParam Long idStudent) {
        var findFaculty = facultyService.findFacultyByStudentId(idStudent);
        return ResponseEntity.ok((findFaculty));
    }

    @GetMapping(path = "students")
    public ResponseEntity<?> getAllStudents(@RequestParam Long facultyID) {
        var students = facultyService.getAllStudents(facultyID);
        // Перенести всю логику в Service по аналогии выше.
        if (students.isEmpty()) {
            return new ResponseEntity<>("Студентов нет.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping("{color}")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@PathVariable String color) {
        var faculty = facultyService.getFacultyByColor(color);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("{facultyID}")
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(faculty);
        if (updateFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{facultyID}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long facultyID) {
        facultyService.deleteFaculty(facultyID);
        return ResponseEntity.ok().build();
    }
}
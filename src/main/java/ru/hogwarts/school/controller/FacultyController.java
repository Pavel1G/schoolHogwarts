package ru.hogwarts.school.controller;

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

    @GetMapping("{facultyID}")
    public ResponseEntity<?> getFaculty(@PathVariable Long facultyID) {
        var faculty = facultyService.getFacultyByID(facultyID);
        if (faculty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty.get());
    }

    @GetMapping()
    public ResponseEntity<?> findFaculty(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String color) {
        Collection<Faculty> temp = facultyService.findFacultiesByColorContainsIgnoreCase(name, color);
        if (temp.isEmpty()) {
            return new ResponseEntity<>("Факультеты не найдены", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(temp);
    }

    @GetMapping(path = "faculty")
    public ResponseEntity<?> findFaculty(@RequestParam Long idStudent) {
        var findFaculty = facultyService.findFacultyByStudentId(idStudent);
        return ResponseEntity.ok((findFaculty));
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

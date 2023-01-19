package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyByID(Long facultyID) {
        return facultyRepository.getById(facultyID);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyID) {
        facultyRepository.deleteById(facultyID);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.getFacultiesByColor(color);
    }
}

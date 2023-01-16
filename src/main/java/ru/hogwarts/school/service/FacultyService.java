package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long generateFacultyID = 1L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generateFacultyID);
        faculties.put(faculty.getId(), faculty);
        generateFacultyID++;
        return faculty;
    }

    public Faculty getFacultyByID(Long facultyID) {
        return faculties.get(facultyID);
    }

    public Faculty updateFaculty(Long facultyID, Faculty faculty) {
        if (!faculties.containsKey(facultyID)) {
            return null;
        }
        faculties.put(facultyID, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facultyID) {
        return faculties.remove(facultyID);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor() == color)
                .toList();
    }
}

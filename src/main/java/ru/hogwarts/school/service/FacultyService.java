package ru.hogwarts.school.service;

import liquibase.pro.packaged.F;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.entityNotFoundMyException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyByID(Long facultyID) {
        logger.debug("Was invoked method for get faculty by ID: {}", facultyID);
        logger.error("There is not faculty with id = " + facultyID);
        return facultyRepository.findById(facultyID)
                .orElseThrow(()->new entityNotFoundMyException("Не найден факультет с id: " + facultyID));
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.debug("Was invoked method for change faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyID) {
        logger.debug("Was invoked method for delete faculty");
        facultyRepository.deleteById(facultyID);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.debug("Was invoked method for get faculty by color: {}", color);
        return facultyRepository.getFacultiesByColor(color);
    }

    public Collection<Faculty> findFacultiesByColorContainsIgnoreCase(String name, String color) {
        logger.debug("Was invoked method for get faculty by color or name");

        var faculties = facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
        if(faculties.isEmpty()){
            new entityNotFoundMyException("Список факультетов(поиск по имени/цвету пустой.");
        }
        return faculties;
    }

    public Collection<Faculty> findAllFaculty() {
        logger.debug("Was invoked method for get all faculties");

        return facultyRepository.findAll();
    }

    public Faculty findFacultyByStudentId(Long idStudent) {
        logger.debug("Was invoked method for get faculty by ID");

        return facultyRepository.findFacultyByStudentId(idStudent);
    }

    //Работа с классом Page
    public Page<Faculty> getAllFaculty(Pageable pageable) {
        logger.debug("Was invoked method for get faculty by ID (another view)");

        var faculties = facultyRepository.findAll(pageable);
        if(faculties.isEmpty()){
            throw new entityNotFoundMyException("Список факультетов пустой.");
        }
        return faculties;
    }

    public Collection<Student> getAllStudents(Long facultyID) {
        logger.debug("Was invoked method for get students by faculty ID");

        return facultyRepository.findById(facultyID).get().getStudent();
    }

    public String getLongestName() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparing(f -> f.getName().length()))
                .map(Faculty::getName)
                .orElse(String.valueOf(new entityNotFoundMyException("Список факультетов пустой.")));
    }
}
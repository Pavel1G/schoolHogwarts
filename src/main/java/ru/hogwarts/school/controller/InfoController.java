package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.infoport.InfoPortService;

@RestController
@RequestMapping("port")
public class InfoController {

    @Autowired
    private InfoPortService infoPortService;

    @GetMapping("/info")
    public ResponseEntity<?> getInfoPort() {
        return ResponseEntity.ok(infoPortService.getPort());
    }
}

package ru.hogwarts.school.service.infoport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("main")
public class InfoPortServiceMain implements InfoPortService {

    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public Integer getPort() {
        return serverPort;
    }
}

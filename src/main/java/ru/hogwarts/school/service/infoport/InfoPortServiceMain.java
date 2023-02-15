package ru.hogwarts.school.service.infoport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Profile("main")
public class InfoPortServiceMain implements InfoPortService {

    @Value("${server.port}")
    private Integer serverPort;

    @Override
    public Integer getPort() {
        return serverPort;
    }

    @Override
    public Integer getIntegerByStream() {
        return ((1 + 1_000_000) / 2) * 1_000_000;

    }


}

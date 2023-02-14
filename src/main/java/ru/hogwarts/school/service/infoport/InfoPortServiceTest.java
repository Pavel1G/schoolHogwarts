package ru.hogwarts.school.service.infoport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!main")
public class InfoPortServiceTest implements InfoPortService {
    @Override
    public Integer getPort() {
        return 0;
    }

    @Override
    public Integer getIntegerByStream() {
        return 0;
    }
}

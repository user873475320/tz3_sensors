package ru.sensors.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sensors.dto.SensorDTO;
import ru.sensors.models.Sensor;
import ru.sensors.repositories.SensorRepository;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final ModelMapper modelMapper;
    private final SensorRepository sensorRepository;

    @Transactional
    public void registrateNewSensor(SensorDTO sensorDTO) {
        sensorRepository.save(modelMapper.map(sensorDTO, Sensor.class));
    }
}

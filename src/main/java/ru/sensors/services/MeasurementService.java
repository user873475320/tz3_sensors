package ru.sensors.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sensors.dto.MeasurementDTO;
import ru.sensors.models.Measurement;
import ru.sensors.repositories.MeasurementRepository;
import ru.sensors.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;
    private final SensorRepository sensorRepository;

    public List<MeasurementDTO> getAllMeasurements() {
        return measurementRepository.findAll().stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDTO.class)).toList();
    }

    public Map<String, Long> getRainyDaysCount() {
        return new HashMap<>(Map.of("rainyDaysCount", measurementRepository.countByRainingTrue()));
    }

    @Transactional
    public void addMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setSensor(sensorRepository.findByName(measurementDTO.getSensor().getName()));
        measurement.setCreatedAt(LocalDateTime.now());

        measurementRepository.save(measurement);
    }
}

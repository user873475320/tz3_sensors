package ru.sensors.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sensors.dto.MeasurementDTO;
import ru.sensors.dto.SensorDTO;
import ru.sensors.repositories.SensorRepository;

@Component
public class MeasurementValidator implements Validator {

    private final SensorRepository sensorRepository;

    public MeasurementValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        SensorDTO sensorDTO = measurementDTO.getSensor();

        if (sensorDTO != null && sensorRepository.findByName(sensorDTO.getName()) == null) {
            errors.rejectValue("sensor", "400", "There is no sensor with name: " + sensorDTO.getName());
        }
    }
}

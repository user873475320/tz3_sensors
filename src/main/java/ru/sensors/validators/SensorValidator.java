package ru.sensors.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.sensors.dto.SensorDTO;
import ru.sensors.repositories.SensorRepository;

@Component
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorRepository.findByName(sensorDTO.getName()) != null) {
            errors.rejectValue("name", "409", "This name is already taken.");
        }
    }
}

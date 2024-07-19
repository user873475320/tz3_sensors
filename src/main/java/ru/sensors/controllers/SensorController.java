package ru.sensors.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sensors.dto.SensorDTO;
import ru.sensors.services.SensorService;
import ru.sensors.utils.Utils;
import ru.sensors.validators.SensorValidator;


@RestController
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @PostMapping("/sensors/registration")
    public ResponseEntity<String> registrateSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                       BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Utils.getReadableErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }

        sensorService.registrateNewSensor(sensorDTO);

        return new ResponseEntity<>("Sensor registered successfully", HttpStatus.OK);
    }
}

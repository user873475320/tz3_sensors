package ru.sensors.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sensors.dto.MeasurementDTO;
import ru.sensors.services.MeasurementService;
import ru.sensors.utils.Utils;
import ru.sensors.validators.MeasurementValidator;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementValidator measurementValidator;
    private final MeasurementService measurementService;

    @GetMapping()
    public ResponseEntity<List<MeasurementDTO>> getAllMeasurements() {
        return ResponseEntity.ok(measurementService.getAllMeasurements());
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Map<String, Long>> getRainyDaysCount() {
        return ResponseEntity.ok(measurementService.getRainyDaysCount());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {

        measurementValidator.validate(measurementDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(Utils.getReadableErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }

        measurementService.addMeasurement(measurementDTO);

        return ResponseEntity.ok("Measurement added successfully");
    }
}

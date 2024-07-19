package ru.sensors.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementDTO {

    @NotNull
    @DecimalMin(value = "-100.0", message = "Value must be greater than or equal to -100.0")
    @DecimalMax(value = "100.0", message = "Value must be less than or equal to 100.0")
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;
}

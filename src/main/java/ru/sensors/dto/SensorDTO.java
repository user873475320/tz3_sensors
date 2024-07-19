package ru.sensors.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SensorDTO {

    @NotBlank
    @Size(min = 3, max = 30, message = "Name length should be between 3 and 30 characters")
    private String name;
}

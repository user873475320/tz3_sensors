package ru.sensors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "-100.0", message = "Value must be greater than or equal to -100.0")
    @DecimalMax(value = "100.0", message = "Value must be less than or equal to 100.0")
    @Column(nullable = false)
    private Double value;

    @NotNull
    @Column(nullable = false)
    private Boolean raining = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    private LocalDateTime createdAt;
}

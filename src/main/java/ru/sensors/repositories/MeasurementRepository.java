package ru.sensors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sensors.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    long countByRainingTrue();
}

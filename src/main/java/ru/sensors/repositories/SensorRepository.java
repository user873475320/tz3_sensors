package ru.sensors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sensors.models.Sensor;


public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findByName(String name);
}

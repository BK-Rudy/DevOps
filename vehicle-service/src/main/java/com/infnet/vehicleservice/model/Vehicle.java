package com.infnet.vehicleservice.model;

import com.infnet.vehicleservice.model.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"vehicle\"")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private String color;

    private String licensePlate;

    private Integer mileage;

    private Year manufactureYear;

    private Year modelYear;

    private VehicleType type;

    @Embedded
    private Engine engine;

    public Vehicle(String brand, String model, String color, Year manufactureYear,
                   Year modelYear, String licensePlate, Integer mileage, VehicleType type, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.manufactureYear = manufactureYear;
        this.modelYear = modelYear;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.type = type;
        this.engine = engine;
    }
}

package com.infnet.vehicleservice.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Engine {
    private String fuelType;
    private Integer displacement;
    private Integer horsepower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engine)) return false;
        Engine engine = (Engine) o;
        return Objects.equals(fuelType, engine.fuelType) &&
                Objects.equals(displacement, engine.displacement) &&
                Objects.equals(horsepower, engine.horsepower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelType, displacement, horsepower);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "fuelType='" + fuelType + '\'' +
                ", displacement=" + displacement +
                ", horsepower=" + horsepower +
                '}';
    }
}

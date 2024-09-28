package com.infnet.vehicleservice;

import com.infnet.vehicleservice.model.Engine;
import com.infnet.vehicleservice.model.Vehicle;
import com.infnet.vehicleservice.model.enums.VehicleType;
import com.infnet.vehicleservice.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.Year;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VehicleRepository vehicleRepository;

    private Vehicle testVehicle;

    @BeforeEach
    void setup() {
        vehicleRepository.deleteAll();

        testVehicle = Vehicle.builder()
                .brand("Toyota")
                .model("Corolla")
                .color("Prata")
                .licensePlate("ABC-1234")
                .mileage(50000)
                .manufactureYear(Year.of(2015))
                .modelYear(Year.of(2016))
                .type(VehicleType.CAR)
                .engine(new Engine("Gasolina", 1800, 140))
                .build();
        vehicleRepository.save(testVehicle);
    }

    @Test
    void testFindAllVehicles() {
        ResponseEntity<Vehicle[]> response = restTemplate.getForEntity("/api/vehicles", Vehicle[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void testFindVehicleById() {
        String url = "/api/vehicles/" + testVehicle.getId();
        ResponseEntity<Vehicle> response = restTemplate.getForEntity(url, Vehicle.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(testVehicle.getId());
    }

    @Test
    void testCreateVehicle() {
        Vehicle newVehicle = Vehicle.builder()
                .brand("Honda")
                .model("Civic")
                .color("Preto")
                .licensePlate("XYZ-9876")
                .mileage(20000)
                .manufactureYear(Year.of(2018))
                .modelYear(Year.of(2019))
                .type(VehicleType.CAR)
                .engine(new Engine("Gasolina", 1600, 130))
                .build();

        ResponseEntity<Vehicle> response = restTemplate.postForEntity("/api/vehicles", newVehicle, Vehicle.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
    }

    @Test
    void testUpdateVehicle() {
        String url = "/api/vehicles/" + testVehicle.getId();
        Vehicle updatedVehicle = Vehicle.builder()
                .brand("Toyota")
                .model("Corolla")
                .color("Azul")
                .licensePlate("ABC-1234")
                .mileage(60000)
                .manufactureYear(Year.of(2015))
                .modelYear(Year.of(2016))
                .type(VehicleType.CAR)
                .engine(new Engine("Gasolina", 1800, 140))
                .build();

        restTemplate.put(url, updatedVehicle);
        ResponseEntity<Vehicle> response = restTemplate.getForEntity(url, Vehicle.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getColor()).isEqualTo("Azul");
        assertThat(response.getBody().getMileage()).isEqualTo(60000);
    }

    @Test
    void testDeleteVehicle() {
        String url = "/api/vehicles/" + testVehicle.getId();
        restTemplate.delete(url);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}

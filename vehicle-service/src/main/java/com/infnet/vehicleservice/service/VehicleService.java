package com.infnet.vehicleservice.service;

import com.infnet.vehicleservice.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Optional<Vehicle> findById(Long id) throws Exception;
    List<Vehicle> findAll() throws Exception;
    List<Vehicle> findByModel(String model) throws Exception;
    Vehicle save(Vehicle veiculo) throws Exception;
    Optional<Vehicle> update(Long id,Vehicle veiculo) throws Exception;
    Optional<Vehicle> delete(long id) throws Exception;
}

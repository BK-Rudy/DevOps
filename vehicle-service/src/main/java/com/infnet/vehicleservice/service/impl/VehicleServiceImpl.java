package com.infnet.vehicleservice.service.impl;

import com.infnet.vehicleservice.model.Vehicle;
import com.infnet.vehicleservice.repository.VehicleRepository;
import com.infnet.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Optional<Vehicle> findById(Long id) throws Exception {
        log.info("Buscando veículo...");
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new Exception("Erro: Veículo com ID: " + id + " não encontrado."));

        log.info("Veículo encontrado com sucesso.");

        return Optional.of(vehicle);
    }

    @Override
    public List<Vehicle> findAll() throws Exception {
        log.info("Buscando veículos...");
        List<Vehicle> vehicles = vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            log.error("Erro: Nenhum veículo encontrado");
            throw new Exception("Erro: Não há veículos cadastrados.");
        }

        log.info("Encontrado {} veículos.", vehicles.size());

        return vehicles;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        log.info("Criando veículo...");

        if (vehicle.getModel().isBlank()) {
            throw new IllegalArgumentException("Erro: Modelo inválido.");
        }
        if (vehicle.getBrand().isBlank()) {
            throw new IllegalArgumentException("Erro: Marca inválida.");
        }
        if (vehicle.getLicensePlate().isBlank()) {
            throw new IllegalArgumentException("Erro: Placa inválida.");
        }

        log.info("Veículo criado com sucesso.");

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> update(Long id, Vehicle vehicle) throws Exception {
        Optional<Vehicle> optVehicle = vehicleRepository.findById(id);
        if (optVehicle.isEmpty()) {
            throw new Exception("Erro: Veículo não encontrado, tente outro ID.");
        }
        Vehicle existingVehicle = optVehicle.get();

        if (vehicle.getBrand() != null) {
            existingVehicle.setBrand(vehicle.getBrand());
        }
        if (vehicle.getModel() != null) {
            existingVehicle.setModel(vehicle.getModel());
        }
        if (vehicle.getColor() != null) {
            existingVehicle.setColor(vehicle.getColor());
        }
        if (vehicle.getLicensePlate() != null) {
            existingVehicle.setLicensePlate(vehicle.getLicensePlate());
        }
        if (vehicle.getMileage() != null) {
            existingVehicle.setMileage(vehicle.getMileage());
        }
        if (vehicle.getManufactureYear() != null) {
            existingVehicle.setManufactureYear(vehicle.getManufactureYear());
        }
        if (vehicle.getModelYear() != null) {
            existingVehicle.setModelYear(vehicle.getModelYear());
        }

        vehicle.setId(id);
        vehicleRepository.save(existingVehicle);

        return Optional.of(existingVehicle);
    }

    @Override
    public Optional<Vehicle> delete(long id) throws Exception {
        log.info("Excluindo veículo...");

        Optional<Vehicle> vehicle = vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            log.error("Veículo com ID " + id + " não encontrado.");
            throw new Exception("Erro: Não foi possível encontrar veículo com ID: "+ id);
        }

        vehicleRepository.deleteById(id);
        log.info("Veículo excluído com sucesso.");

        return vehicle;
    }
}

package com.infnet.vehicleservice.repository;

import com.infnet.vehicleservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT u FROM Vehicle u WHERE u.model = :model")
    List<Vehicle> findByModel(@Param("model") String model);
}

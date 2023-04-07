package com.polizas.backendpolizas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polizas.backendpolizas.models.DetallePoliza;

public interface PolizaDetRepository extends JpaRepository<DetallePoliza, Integer> {
    
}

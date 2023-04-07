package com.polizas.backendpolizas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polizas.backendpolizas.models.DetalleArticulo;

public interface DetalleRepository extends JpaRepository<DetalleArticulo, Integer> {
    
}

package com.polizas.backendpolizas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polizas.backendpolizas.models.Poliza;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Integer> {
    
}

package com.polizas.backendpolizas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polizas.backendpolizas.models.ViewPolizas;

public interface ViewPolizasRepository extends JpaRepository<ViewPolizas, Long> {
    
}

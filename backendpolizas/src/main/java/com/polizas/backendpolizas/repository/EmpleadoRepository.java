package com.polizas.backendpolizas.repository;

import org.springframework.stereotype.Repository;

import com.polizas.backendpolizas.models.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
    
}

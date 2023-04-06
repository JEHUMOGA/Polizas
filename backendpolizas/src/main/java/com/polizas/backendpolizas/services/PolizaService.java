package com.polizas.backendpolizas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polizas.backendpolizas.models.PolizaModel;
import com.polizas.backendpolizas.repository.PolizaRepository;

@Service
public class PolizaService {
    
    @Autowired
    PolizaRepository polizaRepository;

    public List<PolizaModel> obtenerPolizas(){
        return polizaRepository.findAll();
    }

}

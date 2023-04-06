package com.polizas.backendpolizas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polizas.backendpolizas.models.PolizaModel;
import com.polizas.backendpolizas.services.PolizaService;

@RestController
@RequestMapping(value = "poliza")
public class PolizasController {

    @Autowired
    PolizaService polizaService;

    @GetMapping(value = "consultar")
    public ResponseEntity<List<PolizaModel>> getPolizas(){
        List<PolizaModel> polizas = polizaService.obtenerPolizas();
        return new ResponseEntity<>(polizas, HttpStatus.OK);
    }
    
}

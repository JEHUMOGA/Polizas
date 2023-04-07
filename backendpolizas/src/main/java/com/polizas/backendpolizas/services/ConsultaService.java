package com.polizas.backendpolizas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polizas.backendpolizas.models.DetalleArticulo;
import com.polizas.backendpolizas.models.DetallePoliza;
import com.polizas.backendpolizas.models.Empleado;
import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.repository.DetalleRepository;
import com.polizas.backendpolizas.repository.EmpleadoRepository;
import com.polizas.backendpolizas.repository.PolizaDetRepository;
import com.polizas.backendpolizas.repository.PolizaRepository;

@Service
public class ConsultaService {
    @Autowired
    private PolizaRepository polizaRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DetalleRepository detalleRepository;
    @Autowired
    private PolizaDetRepository polizaDetRepository;
/*
    public Optional<Poliza> obtenerPoliza(int idpolizas){
        return polizaRepository.findById(idpolizas);
    }
    public Optional<Empleado> obtenerEmpleado(int idempleado){
        return empleadoRepository.findById(idempleado);
    }
    public Optional<DetalleArticulo> obtenerDetalle(int sku){
        return detalleRepository.findById(sku);
    }
    */

    public Poliza obtenerPoliza(int idpolizas){
        return polizaRepository.findById(idpolizas).get();
    }
    public Empleado obtenerEmpleado(int idempleado){
        return empleadoRepository.findById(idempleado).get();
    }
    public DetalleArticulo obtenerDetalle(int sku){
        return detalleRepository.findById(sku).get();
    }
    public DetallePoliza obtenerDetPoliza(int idpoliza){
        return polizaDetRepository.findById(idpoliza).get();
    }

    public void actualizarPoliza(DetallePoliza detallePoliza){
        polizaDetRepository.save(detallePoliza);
    }
}

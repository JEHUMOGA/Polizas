package com.polizas.backendpolizas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polizas.backendpolizas.models.DetalleArticulo;
import com.polizas.backendpolizas.models.Empleado;
import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.repository.DetalleRepository;
import com.polizas.backendpolizas.repository.EmpleadoRepository;
import com.polizas.backendpolizas.repository.PolizaRepository;

@Service
public class PolizaService {
    
    @Autowired
    private PolizaRepository polizaRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DetalleRepository detalleRepository;
    
    public Poliza obtenerPoliza(int idpolizas){
        return polizaRepository.findById(idpolizas).get();
    }
    public Empleado obtenerEmpleado(int idempleado){
        return empleadoRepository.findById(idempleado).get();
    }
    public DetalleArticulo obtenerDetalle(int sku){
        return detalleRepository.findById(sku).get();
    }
    public void actualizarPoliza(Poliza detallePoliza){
        polizaRepository.save(detallePoliza);
    }

    public Integer generaPoliza(Poliza poliza, Empleado empleado, DetalleArticulo articulo ){
        return polizaRepository.generarPoliza(empleado.getNombreemp(), empleado.getApellidopatemp(), articulo.getNombre(), poliza.getCantidad());
    }

    public Integer actualizarPoliza(Integer idpol, Poliza poliza, Empleado empleado, DetalleArticulo articulo ){
        return polizaRepository.actualizarPoliza(idpol,empleado.getNombreemp(), empleado.getApellidopatemp(), articulo.getNombre(), poliza.getCantidad());
    }

}

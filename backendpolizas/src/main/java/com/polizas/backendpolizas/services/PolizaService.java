package com.polizas.backendpolizas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polizas.backendpolizas.models.DetalleArticulo;
import com.polizas.backendpolizas.models.Empleado;
import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.models.ViewPolizas;
import com.polizas.backendpolizas.repository.DetalleRepository;
import com.polizas.backendpolizas.repository.EmpleadoRepository;
import com.polizas.backendpolizas.repository.PolizaRepository;
import com.polizas.backendpolizas.repository.ViewPolizasRepository;

@Service
public class PolizaService {
    
    @Autowired
    private PolizaRepository polizaRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DetalleRepository detalleRepository;
    @Autowired
    private ViewPolizasRepository viewPolizasRepository;
    
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

    public Integer generaPoliza(Poliza poliza){
        Integer idEmp = poliza.getEmpleadogenero();
        Integer idArt = poliza.getSku();
        Integer cant = poliza.getCantidad();
        return polizaRepository.generarPoliza(idEmp, idArt, cant);
    }

    public Integer actualizarPoliza(Integer idpol, Poliza poliza){
        Integer idEmp = poliza.getEmpleadogenero();
        Integer idArt = poliza.getSku();
        Integer cant = poliza.getCantidad();
        return polizaRepository.actualizarPoliza(idpol,idEmp, idArt, cant);
    }

    public List<Empleado> listaEmpleados(){
        return empleadoRepository.findAll();
    }
    public List<DetalleArticulo> listaArticulos(){
        return detalleRepository.findAll();
    }

    public List<ViewPolizas> listaPolizas(){
        return viewPolizasRepository.findAll();
    }

    public Integer eliminarPoliza(int idpol){
        return polizaRepository.eliminarPoliza(idpol);
    }

}

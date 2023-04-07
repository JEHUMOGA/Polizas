package com.polizas.backendpolizas.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polizas.backendpolizas.models.Data;
import com.polizas.backendpolizas.models.DetalleArticulo;
import com.polizas.backendpolizas.models.DetallePoliza;
import com.polizas.backendpolizas.models.Empleado;
import com.polizas.backendpolizas.models.Meta;
import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.models.Response;
import com.polizas.backendpolizas.services.ConsultaService;
import com.polizas.backendpolizas.services.PolizaService;

@RestController
@RequestMapping(value = "poliza")
public class PolizasController {

    @Autowired
    PolizaService polizaService;

    @Autowired
    ConsultaService consultaService;

    @GetMapping(value = "consultar")
    public ResponseEntity<List<Poliza>> getPolizas(HttpServletResponse response){
        response.setHeader("Meta", "STATUS: OK");
        List<Poliza> polizas = polizaService.obtenerPolizas();

        return new ResponseEntity(polizas, HttpStatus.OK);
    }

    @GetMapping( value = "obtenerpoliza/{idpoliza}")
    public Response getPoliza(@PathVariable Integer idpoliza){
        Response response = new Response();
        try{
            DetallePoliza polDet = consultaService.obtenerDetPoliza(idpoliza);
            Poliza poliza = consultaService.obtenerPoliza(polDet.getIdpolizas());
            Empleado empleado = consultaService.obtenerEmpleado(polDet.getEmpleadogenero());
            DetalleArticulo articulo = consultaService.obtenerDetalle(polDet.getSku());

            Data data = new Data();
            data.setPoliza(poliza);
            data.setEmpleado(empleado);
            data.setDetalleArticulo(articulo);

            Meta meta = new Meta();
            meta.setStatus("OK");

            response.setData(data);
            response.setMeta(meta);

        }catch(Exception e){
            Meta meta = new Meta();
            meta.setStatus("FAILURE");
            Data data = new Data();
            data.setMensaje("Ha ocurrido un error al consultar la póliza.");
            response.setMeta(meta);
            response.setData(data);

        }

        return response;
    }

    @PutMapping(value = "actualizar/{idpoliza}")
    public Response putPoliza(@PathVariable Integer idpoliza, @RequestBody DetallePoliza detallePoliza){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        DetallePoliza poliza = consultaService.obtenerDetPoliza(idpoliza);
        poliza.setEmpleadogenero(detallePoliza.getEmpleadogenero());
        poliza.setSku(detallePoliza.getSku());
        poliza.setCantidad(detallePoliza.getCantidad());
        consultaService.actualizarPoliza(poliza);
        //Meta meta = new Meta();
        meta.setStatus("OK");
        data.setMensaje("Se actualizo correctamente la poliza " + detallePoliza.getIdpolizas());
        response.setMeta(meta);
        response.setData(data);
        return response;
        
    }

    

}

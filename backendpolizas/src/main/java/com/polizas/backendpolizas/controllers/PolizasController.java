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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polizas.backendpolizas.models.Data;
import com.polizas.backendpolizas.models.DetalleArticulo;
//import com.polizas.backendpolizas.models.DetallePoliza;
import com.polizas.backendpolizas.models.Empleado;
import com.polizas.backendpolizas.models.Meta;
import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.models.Response;
//import com.polizas.backendpolizas.services.ConsultaService;
import com.polizas.backendpolizas.services.PolizaService;

@RestController
@RequestMapping(value = "poliza")
public class PolizasController {

    @Autowired
    PolizaService polizaService;

   // @Autowired
    //ConsultaService consultaService;
    /*
    @GetMapping(value = "consultar")
    public ResponseEntity<List<Poliza>> getPolizas(HttpServletResponse response){
        response.setHeader("Meta", "STATUS: OK");
        List<Poliza> polizas = polizaService.obtenerPolizas();

        return new ResponseEntity(polizas, HttpStatus.OK);
    }*/

    @GetMapping( value = "obtenerpoliza/{idpoliza}")
    public Response getPoliza(@PathVariable Integer idpoliza){
        Response response = new Response();
        try{
            //DetallePoliza polDet = polizaService.obtenerDetPoliza(idpoliza);
            Poliza poliza = polizaService.obtenerPoliza(idpoliza);
            Empleado empleado = polizaService.obtenerEmpleado(poliza.getEmpleadogenero());
            DetalleArticulo articulo = polizaService.obtenerDetalle(poliza.getSku());

            Data data = new Data();
            data.setPoliza(poliza);
            data.setEmpleado(empleado);
            data.setDetalleArticulo(articulo);

            Meta meta = new Meta();
            meta.setStatus("OK");

            response.setData(data);
            response.setMeta(meta);

        }catch(Exception e){
            response = generaFailure("Ha ocurrido un error al consultar la póliza.");

        }

        return response;
    }

    @PutMapping(value = "actualizar/{idpoliza}")
    public Response putPoliza(@PathVariable Integer idpoliza, @RequestBody Data dat){
        Response response = new Response();
        try{
            Meta meta = new Meta();
            Data data = new Data();

            Integer res = polizaService.actualizarPoliza(idpoliza, dat.getPoliza(), dat.getEmpleado(), dat.getDetalleArticulo());
            
            if(res < 0)
                return generaFailure("Ha ocurrido un error al intentar actualizar la póliza.");            
            //Meta meta = new Meta();
            meta.setStatus("OK");
            data.setMensaje("Se actualizo correctamente la póliza " + idpoliza);
            response.setMeta(meta);
            response.setData(data);
        } catch(Exception e){
            response = generaFailure("Ha ocurrido un error al intentar actualizar la póliza.");
        }
        
        return response;
        
    }

    @PostMapping(value = "generarpoliza")
    public Response generarPoliza(@RequestBody Data dat){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        try{
            Poliza poliza = dat.getPoliza();
            Empleado empleado = dat.getEmpleado();
            DetalleArticulo articulo = dat.getDetalleArticulo();
            Integer idpoliza = polizaService.generaPoliza(poliza, empleado, articulo);
            Poliza polizaResponse = polizaService.obtenerPoliza(idpoliza);
            DetalleArticulo articuloResponse = polizaService.obtenerDetalle(polizaResponse.getSku());
            Empleado empleadoResponse = polizaService.obtenerEmpleado(polizaResponse.getEmpleadogenero());
            
            data.setPoliza(polizaResponse);
            data.setEmpleado(empleadoResponse);
            data.setDetalleArticulo(articuloResponse);

            meta.setStatus("OK");

            response.setMeta(meta);
            response.setData(data);
            
        }catch(Exception e){
            response = generaFailure("Ha ocurrido un error en los grabados de póliza.");
        }

        return response;
    }

    private Response generaFailure(String mensaje){
        Response response = new Response();
        Meta meta = new Meta();
        meta.setStatus("FAILURE");
        Data data = new Data();
        data.setMensaje(mensaje);
        response.setMeta(meta);
        response.setData(data);
        return response;
    }

}

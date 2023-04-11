package com.polizas.backendpolizas.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.polizas.backendpolizas.models.Data;
import com.polizas.backendpolizas.models.DetalleArticulo;
//import com.polizas.backendpolizas.models.DetallePoliza;
import com.polizas.backendpolizas.models.Empleado;
import com.polizas.backendpolizas.models.Meta;
import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.models.Response;
import com.polizas.backendpolizas.models.ViewPolizas;
import com.polizas.backendpolizas.repository.EmpleadoRepository;
//import com.polizas.backendpolizas.services.ConsultaService;
import com.polizas.backendpolizas.services.PolizaService;

@RestController
@RequestMapping(value = "poliza")
public class PolizasController {

    @Autowired
    PolizaService polizaService;

    @GetMapping( value = "obtenerpoliza/{idpoliza}")
    public Response getPoliza(@PathVariable Integer idpoliza){
        Response response = new Response();
        try{
            //DetallePoliza polDet = polizaService.obtenerDetPoliza(idpoliza);
            Poliza poliza = polizaService.obtenerPoliza(idpoliza);
            Empleado empleado = polizaService.obtenerEmpleado(poliza.getEmpleadogenero());
            DetalleArticulo articulo = polizaService.obtenerDetalle(poliza.getSku());

            poliza.setEmpleadogenero(null);
            poliza.setSku(null);
            empleado.setIdempleado(null);
            
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
    public Response putPoliza(@PathVariable Integer idpoliza, @RequestBody Poliza pol){
        Response response = new Response();
        try{
            Meta meta = new Meta();
            Data data = new Data();

            Integer res = polizaService.actualizarPoliza(idpoliza, pol);
            
            if(res < 0)
                return generaFailure("Ha ocurrido un error al intentar actualizar la póliza.");       
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
    public Response generarPoliza(@RequestBody Poliza pol){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        try{
            System.out.println("empleadogenero: " + pol.getEmpleadogenero() + " idArticulo: " + pol.getSku() + " cantidad: " + pol.getCantidad());
            Integer idpoliza = polizaService.generaPoliza(pol);
            Poliza polizaResponse = polizaService.obtenerPoliza(idpoliza);
            DetalleArticulo articuloResponse = polizaService.obtenerDetalle(polizaResponse.getSku());
            Empleado empleadoResponse = polizaService.obtenerEmpleado(polizaResponse.getEmpleadogenero());
            empleadoResponse.setIdempleado(null);
            polizaResponse.setEmpleadogenero(null);
            polizaResponse.setSku(null);
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
    
    @GetMapping(value = "listapolizas")
    public Response listaPolizas(){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        try{
            List<ViewPolizas> lista = polizaService.listaPolizas();
            meta.setStatus("OK");
            data.setListapolizas(lista);
            response.setMeta(meta);
            response.setData(data);  
        }catch (Exception e){
            response = generaFailure("Ha ocurrido un error al obtener pólizas.");
        }

        return response;
    }

    @GetMapping( value = "listaarticulos")
    public Response listaArticulos(){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        try{
            List<DetalleArticulo> listArt = polizaService.listaArticulos();
            meta.setStatus("OK");
            data.setListaArticulos(listArt);
            response.setMeta(meta);
            response.setData(data);
        }catch(Exception e){
            response = generaFailure("Ha ocurrido un error al obtener los articulos");
        }
        return response;
    }

    @GetMapping( value = "listaempleados")
    public Response listaEmpleados(){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        try{
            List<Empleado> listEmp = polizaService.listaEmpleados();
            meta.setStatus("OK");
            data.setListaEmpleados(listEmp);
            response.setMeta(meta);
            response.setData(data);
        }catch(Exception e){
            response = generaFailure("Ha ocurrido un error al obtener los empleados");
        }
        return response;
    }

    @DeleteMapping(value = "eliminarpoliza/{idpol}")
    public Response eliminarPoliza(@PathVariable Integer idpol){
        Response response = new Response();
        Meta meta = new Meta();
        Data data = new Data();
        try{
            Integer res = polizaService.eliminarPoliza(idpol);
            if(res < 0){
                return generaFailure("Ha ocurrido un error al eliminar la póliza.");
            }
            meta.setStatus("OK");
            data.setMensaje("Póliza eliminada correctamente.");
            response.setMeta(meta);
            response.setData(data);
        }catch (Exception e){
            response = generaFailure("Ha ocurrido un error al eliminar la póliza.");
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

package com.polizas.backendpolizas.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Data {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Poliza poliza;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Empleado empleado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    DetalleArticulo detalleArticulo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String mensaje;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<ViewPolizas> listapolizas;
}

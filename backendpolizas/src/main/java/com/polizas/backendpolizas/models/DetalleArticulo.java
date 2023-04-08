package com.polizas.backendpolizas.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Inventario")
@Getter
@NoArgsConstructor
public class DetalleArticulo {
    
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    int sku;
    String nombre;
}

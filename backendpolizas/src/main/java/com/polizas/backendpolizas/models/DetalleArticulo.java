package com.polizas.backendpolizas.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Inventario")
@Getter
@NoArgsConstructor
public class DetalleArticulo {
    @Id
    int sku;
    String nombre;
}

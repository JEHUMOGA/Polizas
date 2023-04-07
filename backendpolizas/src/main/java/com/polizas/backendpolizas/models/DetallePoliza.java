package com.polizas.backendpolizas.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "polizas")
@Getter
@NoArgsConstructor
public class DetallePoliza {
    @Id
    int idpolizas;
    int empleadogenero;
    int sku;
    int cantidad;
    String fecha;
}

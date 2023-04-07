package com.polizas.backendpolizas.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "polizas")
@Getter
@Setter
@NoArgsConstructor
public class DetallePoliza {
    @Id
    int idpolizas;
    int empleadogenero;
    int sku;
    int cantidad;
}

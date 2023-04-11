package com.polizas.backendpolizas.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
@Getter
@Entity
@Table(name = "view_polizas")
public class ViewPolizas {
    @Id
    int idpolizas;
    int sku;
    String nombrearticulo;
    int cantidad;
}

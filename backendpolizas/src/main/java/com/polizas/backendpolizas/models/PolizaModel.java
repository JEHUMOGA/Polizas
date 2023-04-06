package com.polizas.backendpolizas.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="polizas")
@Getter
@NoArgsConstructor
public class PolizaModel {
    @Id
    int idpolizas;
    int empleadogenero;
    int sku;
    int cantidad;
    Date fecha;
}

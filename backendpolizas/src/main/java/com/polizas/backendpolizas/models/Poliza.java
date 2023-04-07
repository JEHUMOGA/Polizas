package com.polizas.backendpolizas.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Polizas")
@Getter
@NoArgsConstructor
public class Poliza {
    @Id
    int idpolizas;
    int cantidad;
}

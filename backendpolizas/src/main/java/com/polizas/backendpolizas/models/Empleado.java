package com.polizas.backendpolizas.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleado")
@Getter
@NoArgsConstructor
public class Empleado {
    @JsonIgnore
    @Id
    int idempleado;
    String nombreemp;
    String apellidopatemp;
}

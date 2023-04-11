package com.polizas.backendpolizas.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Empleado")
@Getter
@Setter
@NoArgsConstructor
public class Empleado {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    Integer idempleado;
    @Column(name = "nombreemp")
    String nombre;
    @Column(name = "apellidopatemp")
    String apellido;
}

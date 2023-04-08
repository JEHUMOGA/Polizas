package com.polizas.backendpolizas.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Polizas")
@Setter
@Getter
@NoArgsConstructor
//@JsonFilter("empleadogenero")
@JsonIgnoreProperties(value = {"empleadogenero"})
public class Poliza {
    @Id
    int idpolizas;
//    @JsonIgnore
    int empleadogenero;
    @JsonIgnore
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    int sku;
    int cantidad;
}

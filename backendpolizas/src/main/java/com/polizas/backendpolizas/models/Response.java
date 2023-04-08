package com.polizas.backendpolizas.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    Meta meta;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Data data;
}

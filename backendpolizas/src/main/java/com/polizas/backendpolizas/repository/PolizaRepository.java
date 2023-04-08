package com.polizas.backendpolizas.repository;

import java.util.List;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.polizas.backendpolizas.models.Poliza;
import com.polizas.backendpolizas.models.ViewPolizas;


@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Integer> {
    
    @Query( value = "SELECT generar_poliza(:nomEmp,:apeEmp,:nomArt,:cant);", nativeQuery = true)
    Integer generarPoliza(@Param("nomEmp") String nomemp, @Param("apeEmp") String apeemp, 
                          @Param("nomArt") String nomart, @Param("cant") Integer Cant);

    @Query( value = "SELECT actualizar_poliza(:idpol, :nomEmp, :apeEmp, :nomArt, :cant);", nativeQuery = true)
    Integer actualizarPoliza(@Param("idpol") Integer idPol, @Param("nomEmp") String nomemp, @Param("apeEmp") String apeemp, 
                             @Param("nomArt") String nomart, @Param("cant") Integer Cant);
}

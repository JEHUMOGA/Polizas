package com.polizas.backendpolizas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.polizas.backendpolizas.models.Poliza;


@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Integer> {
    
    @Query( value = "SELECT generar_poliza(:idEmp,:idArt,:cant);", nativeQuery = true)
    Integer generarPoliza(@Param("idEmp") Integer idEmp, @Param("idArt") Integer idArt, @Param("cant") Integer Cant);

    @Query( value = "SELECT actualizar_poliza(:idpol, :idEmp, :idArt, :cant);", nativeQuery = true)
    Integer actualizarPoliza(@Param("idpol") Integer idPol, @Param("idEmp") Integer nomemp, @Param("idArt") Integer apeemp, 
                             @Param("cant") Integer cant);
    
    @Query( value = "SELECT eliminar_poliza(:idpol);", nativeQuery = true)
    Integer eliminarPoliza(@Param("idpol") Integer idPol);
}

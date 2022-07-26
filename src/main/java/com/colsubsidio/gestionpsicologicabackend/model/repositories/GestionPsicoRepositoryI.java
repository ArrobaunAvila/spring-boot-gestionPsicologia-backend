/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.repositories;

import com.colsubsidio.gestionpsicologicabackend.model.entities.GestionPsicologo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface GestionPsicoRepositoryI extends JpaRepository<GestionPsicologo, Integer>{
    
    
    
    
    @Query(value="select P.* from gestionPsicologo P where P.idRegistroGestion=:idGestion",nativeQuery = true)            
    List<GestionPsicologo> cargarGestionesRegistro(@Param("idGestion")Integer  idGestion);
    
    @Query(value="select P.* from gestionPsicologo P where P.envioKibana=:envioKibana",nativeQuery = true)            
    List<GestionPsicologo> gestionesNoEnviadasKibana(@Param("envioKibana")boolean  envioKibana);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.repositories;


import com.colsubsidio.gestionpsicologicabackend.model.entities.RegistroGestionPsicologica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface RegistroGestionRepository extends JpaRepository<RegistroGestionPsicologica, Integer>{
    
    
    
    @Query("SELECT f FROM RegistroGestionPsicologica f where f.envioKibana=:envioKibana")            
    List<RegistroGestionPsicologica> cargarRegistrosEnGestionList();
    
}

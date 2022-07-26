/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.repositories;

import com.colsubsidio.gestionpsicologicabackend.model.entities.EstadoGestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface EstadoGestionRepository  extends JpaRepository<EstadoGestion, Short>{
    
    
    
    
}

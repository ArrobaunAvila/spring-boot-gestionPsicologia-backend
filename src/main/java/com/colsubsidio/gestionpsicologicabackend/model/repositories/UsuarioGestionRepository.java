/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.gestionpsicologicabackend.model.repositories;

import com.colsubsidio.gestionpsicologicabackend.model.entities.UsuarioGestion;
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
public interface UsuarioGestionRepository extends JpaRepository<UsuarioGestion, Integer>{
    
    
    @Query("SELECT f FROM UsuarioGestion f where f.usuarioLogin=:usuarioLogin") 
    public List<UsuarioGestion> usuarioPorLogin(@Param("usuarioLogin")String  usuarioLogin);
    
     @Query("SELECT f FROM UsuarioGestion f where f.usuarioLogin=:usuarioLogin and f.password=:password") 
    public List<UsuarioGestion> usuarioPorLoginPass(@Param("usuarioLogin")String  usuarioLogin,
            @Param("password")String  password);
    
}

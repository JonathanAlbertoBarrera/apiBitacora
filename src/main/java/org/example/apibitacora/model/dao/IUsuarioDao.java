package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioDao extends JpaRepository<Usuario,String> {

    @Query("SELECT u FROM Usuario u WHERE u.estatus = true AND u.rol!='Administrador' ")
    List<Usuario> getAllUsuariosActivos();

    @Query("SELECT u FROM Usuario u WHERE  u.rol!='Administrador' ")
    List<Usuario> getAllUsuariosSinAdmin();
}

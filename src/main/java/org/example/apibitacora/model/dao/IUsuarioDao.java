package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDao extends JpaRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.estatus = true AND u.rol!='Administrador' ")
    List<Usuario> getAllUsuariosActivos();

    @Query("SELECT u FROM Usuario u WHERE  u.rol!='Administrador' ")
    List<Usuario> getAllUsuariosSinAdmin();

    public Optional<Usuario> findByCorreo(String correo);

    public Optional<Usuario> findByMatricula(String matricula);

}

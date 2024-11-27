package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILaboratorioDao extends JpaRepository<Usuario,Long> {

}

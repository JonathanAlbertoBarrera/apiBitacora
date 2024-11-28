package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.Laboratorio;
import org.example.apibitacora.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILaboratorioDao extends JpaRepository<Laboratorio,Long> {
    List<Laboratorio> findAllByEstatusTrue();

}

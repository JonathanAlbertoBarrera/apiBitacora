package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILaboratorioDao extends JpaRepository<Laboratorio,Long> {
    List<Laboratorio> findAllByEstatusTrue();

}

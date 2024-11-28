package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistroDao extends JpaRepository<Registro,Long> {
}

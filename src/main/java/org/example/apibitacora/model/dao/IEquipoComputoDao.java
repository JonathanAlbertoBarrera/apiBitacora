package org.example.apibitacora.model.dao;

import org.example.apibitacora.model.EquipoComputo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEquipoComputoDao extends JpaRepository<EquipoComputo,Long> {
    public Optional<EquipoComputo> findByCodigo(String codigo);
}

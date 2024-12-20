package org.example.apibitacora.service;

import org.example.apibitacora.model.Laboratorio;
import org.example.apibitacora.response.LaboratorioResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILaboratorioService {
    public ResponseEntity<LaboratorioResponseRest> getAllLabs();
    public ResponseEntity<LaboratorioResponseRest> getAllLabsActivos();
    public ResponseEntity<LaboratorioResponseRest> crearLab(Laboratorio laboratorio);
    public ResponseEntity<LaboratorioResponseRest> actualizar(Laboratorio laboratorio,Long id);
    public ResponseEntity<LaboratorioResponseRest> cambiarEstatus(Long id);
}

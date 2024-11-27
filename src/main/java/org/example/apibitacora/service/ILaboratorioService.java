package org.example.apibitacora.service;

import org.example.apibitacora.response.LaboratorioResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILaboratorioService {
    public ResponseEntity<LaboratorioResponseRest> getAllLabs();
}

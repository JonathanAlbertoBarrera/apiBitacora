package org.example.apibitacora.service;

import org.example.apibitacora.model.Registro;
import org.example.apibitacora.response.RegistroResponseRest;
import org.springframework.http.ResponseEntity;

public interface IRegistroService {
    public ResponseEntity<RegistroResponseRest> getAllRegistros();
    public ResponseEntity<RegistroResponseRest> crearRegistro(Registro registro);
}

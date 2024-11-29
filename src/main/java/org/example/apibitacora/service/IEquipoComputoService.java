package org.example.apibitacora.service;

import org.example.apibitacora.model.EquipoComputo;
import org.example.apibitacora.response.EquipoComputoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IEquipoComputoService {
    public ResponseEntity<EquipoComputoResponseRest> getAllsEquipoComputos();
    public ResponseEntity<EquipoComputoResponseRest> getEquipoComputoByCodigo(String codigoEquipo);
    public ResponseEntity<EquipoComputoResponseRest> crearEquipoComputo(EquipoComputo equipoComputo);
    public ResponseEntity<EquipoComputoResponseRest> actualizarEquipoComputo(String codigo, EquipoComputo equipoComputo);
    public ResponseEntity<EquipoComputoResponseRest> cambiarStatusEquipoComputo(String codigo);

}

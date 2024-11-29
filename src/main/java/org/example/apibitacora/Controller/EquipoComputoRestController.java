package org.example.apibitacora.Controller;


import org.example.apibitacora.model.EquipoComputo;
import org.example.apibitacora.response.EquipoComputoResponseRest;
import org.example.apibitacora.service.IEquipoComputoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EquipoComputoRestController {
    @Autowired
    private IEquipoComputoService service;

    @GetMapping("/equipos")
    public ResponseEntity<EquipoComputoResponseRest> getAllEquipos() {
        ResponseEntity<EquipoComputoResponseRest> response = service.getAllsEquipoComputos();
        return response;
    }

    @GetMapping("/equipos/{codigo}")
    public ResponseEntity<EquipoComputoResponseRest> getEquipo(@PathVariable String codigo) {
        ResponseEntity<EquipoComputoResponseRest> response = service.getEquipoComputoByCodigo(codigo);
        return response;
    }

    @PostMapping("/equipos")
    public ResponseEntity<EquipoComputoResponseRest> crearEquipo(@RequestBody EquipoComputo equipoComputo){
        ResponseEntity<EquipoComputoResponseRest> response = service.crearEquipoComputo(equipoComputo);
        return response;
    }

    @PutMapping("/equipos/{codigo}")
    public ResponseEntity<EquipoComputoResponseRest> actualizarEquipo(@PathVariable String codigo, @RequestBody EquipoComputo equipoComputo){
        ResponseEntity<EquipoComputoResponseRest> response = service.actualizarEquipoComputo(codigo, equipoComputo);
        return response;
    }
    @PutMapping("/equipos/status/{codigo}")
    public ResponseEntity<EquipoComputoResponseRest> actualizarEquipoStatus(@PathVariable String codigo){
        ResponseEntity<EquipoComputoResponseRest> response = service.cambiarStatusEquipoComputo(codigo);
        return response;
    }
}
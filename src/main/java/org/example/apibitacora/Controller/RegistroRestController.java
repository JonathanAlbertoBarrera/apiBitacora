package org.example.apibitacora.Controller;


import org.example.apibitacora.model.Registro;
import org.example.apibitacora.response.RegistroResponseRest;
import org.example.apibitacora.service.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class RegistroRestController {

    @Autowired
    private IRegistroService service;

    //TODOS LOS REGISTROS
    @GetMapping("/registros")
    public ResponseEntity<RegistroResponseRest> getAllRegistros(){
        ResponseEntity<RegistroResponseRest> response=service.getAllRegistros();
        return response;
    }

    //OBTENER UN REGISTRO POR ID
    @GetMapping("/registros/{id_registro}")
    public ResponseEntity<RegistroResponseRest> obtenerRegistrobyId(@PathVariable Long id_registro){
        ResponseEntity<RegistroResponseRest> response=service.obtenerRegistrobyId(id_registro);
        return response;
    }

    //CREAR LOS REGISTROS
    @PostMapping("/registros")
    public ResponseEntity<RegistroResponseRest> crearRegistro(@RequestBody Registro registro){
        ResponseEntity<RegistroResponseRest> response=service.crearRegistro(registro);
        return response;
    }
}

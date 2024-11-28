package org.example.apibitacora.Controller;


import org.example.apibitacora.model.Registro;
import org.example.apibitacora.response.RegistroResponseRest;
import org.example.apibitacora.service.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class RegistroRestController {

    @Autowired
    private IRegistroService service;

    //TODOS LOS REGISTROS
    @GetMapping("/Registros")
    public ResponseEntity<RegistroResponseRest> getAllRegistros(){
        ResponseEntity<RegistroResponseRest> response=service.getAllRegistros();
        return response;
    }

    //CREAR LOS REGISTROS
    @PostMapping("/Registros")
    public ResponseEntity<RegistroResponseRest> crearRegistro(@RequestBody Registro registro){
        ResponseEntity<RegistroResponseRest> response=service.crearRegistro(registro);
        return response;
    }
}

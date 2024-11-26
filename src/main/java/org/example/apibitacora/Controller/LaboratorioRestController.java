package org.example.apibitacora.Controller;

import org.example.apibitacora.model.Laboratorio;
import org.example.apibitacora.response.LaboratorioResponseRest;
import org.example.apibitacora.service.ILaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LaboratorioRestController {

    @Autowired
    private ILaboratorioService service;

    //TODOS LOS LABS
    @GetMapping("/labsAll")
    public ResponseEntity<LaboratorioResponseRest> getAllUsuarios(){
        ResponseEntity<LaboratorioResponseRest> response=service.getAllLabs();
        return response;
    }

    //CREAR LABORATORIO
    @PostMapping("/labs")
    public ResponseEntity<LaboratorioResponseRest> crearLab(@RequestBody Laboratorio laboratorio){
        ResponseEntity<LaboratorioResponseRest> response=service.crearLab(laboratorio);
        return response;
    }


}

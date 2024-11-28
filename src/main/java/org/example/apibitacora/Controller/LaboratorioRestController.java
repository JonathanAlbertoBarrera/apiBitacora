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
    public ResponseEntity<LaboratorioResponseRest> getAllLabs(){
        ResponseEntity<LaboratorioResponseRest> response=service.getAllLabs();
        return response;
    }

    //LABS ACTIVOS
    @GetMapping("/labs")
    public ResponseEntity<LaboratorioResponseRest> getAllLabsActivos(){
        ResponseEntity<LaboratorioResponseRest> response=service.getAllLabsActivos();
        return response;
    }

    //CREAR LABORATORIO
    @PostMapping("/labs")
    public ResponseEntity<LaboratorioResponseRest> crearLab(@RequestBody Laboratorio laboratorio){
        ResponseEntity<LaboratorioResponseRest> response=service.crearLab(laboratorio);
        return response;
    }

    //ACTUALIZAR LABORATORIO
    @PutMapping("/labs/{id}")
    public ResponseEntity<LaboratorioResponseRest> actualizar(@RequestBody Laboratorio laboratorio, @PathVariable Long id){
        ResponseEntity<LaboratorioResponseRest> response=service.actualizar(laboratorio,id);
        return response;
    }

    //CAMBIAR ESTADO DE UN LABORATORIO
    @PutMapping("/labsModi/{id}")
    public ResponseEntity<LaboratorioResponseRest> cambiarEstatus(@PathVariable Long id){
        ResponseEntity<LaboratorioResponseRest> response=service.cambiarEstatus(id);
        return response;
    }




}

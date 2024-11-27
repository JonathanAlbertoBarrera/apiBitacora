package org.example.apibitacora.Controller;

import org.example.apibitacora.response.LaboratorioResponseRest;
import org.example.apibitacora.service.ILaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LaboratorioRestController {

    @Autowired
    private ILaboratorioService service;

    //TODOS LOS LABS
    @GetMapping("/LabsAll")
    public ResponseEntity<LaboratorioResponseRest> getAllUsuarios(){
        ResponseEntity<LaboratorioResponseRest> response=service.getAllLabs();
        return response;
    }


}

package org.example.apibitacora.service;

import org.example.apibitacora.model.Laboratorio;
import org.example.apibitacora.model.dao.ILaboratorioDao;
import org.example.apibitacora.response.LaboratorioResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ILaboratorioImpl implements ILaboratorioService{

    @Autowired
    private ILaboratorioDao laboratorioDao;

    //TODOS LOS LABS
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LaboratorioResponseRest> getAllLabs() {
        LaboratorioResponseRest response = new LaboratorioResponseRest();

        try {
            List<Laboratorio> laboratorios=laboratorioDao.findAll();
            response.getLaboratorioResponse().setLaboratorios(laboratorios);
            response.setMetada("Ok", "00", "Laboratorios encontrados");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al encontrar laboratorios");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

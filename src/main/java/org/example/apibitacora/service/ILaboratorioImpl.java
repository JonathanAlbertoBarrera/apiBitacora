package org.example.apibitacora.service;

import org.example.apibitacora.model.Laboratorio;
import org.example.apibitacora.model.dao.ILaboratorioDao;
import org.example.apibitacora.response.LaboratorioResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //CREAR LABORATORIO
    @Override
    @Transactional
    public ResponseEntity<LaboratorioResponseRest> crearLab(Laboratorio laboratorio){
        LaboratorioResponseRest response = new LaboratorioResponseRest();
        List<Laboratorio> list = new ArrayList<>();

        try {
            Laboratorio labGuardado = laboratorioDao.save(laboratorio);
            if (labGuardado != null) {
                list.add(labGuardado);
                response.getLaboratorioResponse().setLaboratorios(list);
                response.setMetada("Respuesta OK", "00", "Laboratorio creado exitosamente");
            } else {
                response.setMetada("Respuesta No Creada", "-1", "Laboratorio no creado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar el laboratorio");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //actualizar laboratorio
    @Override
    @Transactional
    public ResponseEntity<LaboratorioResponseRest> actualizar(Laboratorio laboratorio, Long id) {
        LaboratorioResponseRest response = new LaboratorioResponseRest();
        List<Laboratorio> list = new ArrayList<>();

        try {

            Optional<Laboratorio> labBuscado = laboratorioDao.findById(id);

            if (labBuscado.isPresent()) {
                labBuscado.get().setId_lab(laboratorio.getId_lab());
                labBuscado.get().setDocencia(laboratorio.getDocencia());
                labBuscado.get().setNombre_lab(laboratorio.getNombre_lab());
                labBuscado.get().setEstatus(laboratorio.isEstatus());

                Laboratorio laboratorioActualizar = laboratorioDao.save(labBuscado.get());

                if( laboratorioActualizar != null ) {
                    response.setMetada("Respuesta ok", "00", "Laboratorio actualizado");
                    list.add(laboratorioActualizar);
                    response.getLaboratorioResponse().setLaboratorios(list);
                } else {
                    response.setMetada("Respuesta nok", "-1", "Laboratorio no actualizado");
                    return new ResponseEntity<LaboratorioResponseRest>(response, HttpStatus.BAD_REQUEST);
                }


            } else {
                response.setMetada("Respuesta no ok", "-1", "laboratorio no actualizado");
                return new ResponseEntity<LaboratorioResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch ( Exception e) {
            e.getStackTrace();
            response.setMetada("Respuesta no ok", "-1", "Laboratorio no actualizado");
            return new ResponseEntity<LaboratorioResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LaboratorioResponseRest>(response, HttpStatus.OK);
    }
}

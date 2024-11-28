package org.example.apibitacora.service;

import org.example.apibitacora.model.Laboratorio;
import org.example.apibitacora.model.Registro;
import org.example.apibitacora.model.dao.IRegistroDao;
import org.example.apibitacora.response.LaboratorioResponseRest;
import org.example.apibitacora.response.RegistroResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroServiceImpl implements IRegistroService{
    @Autowired
    private IRegistroDao registroDao;

    //todos los registros
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<RegistroResponseRest> getAllRegistros() {
        RegistroResponseRest response = new RegistroResponseRest();

        try {
            List<Registro> registros=registroDao.findAll();
            response.getRegistroResponse().setRegistros(registros);
            response.setMetada("Ok", "00", "Registros encontrados");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al encontrar los registros");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CREAR REGISTRO
    @Override
    @Transactional
    public ResponseEntity<RegistroResponseRest> crearRegistro(Registro registro){
        RegistroResponseRest response = new RegistroResponseRest();
        List<Registro> list = new ArrayList<>();

        try {
            Registro registroGuardado = registroDao.save(registro);
            if (registroGuardado != null) {
                list.add(registroGuardado);
                response.getRegistroResponse().setRegistros(list);
                response.setMetada("Respuesta OK", "00", "Registro creado exitosamente");
            } else {
                response.setMetada("Registro No Creada", "-1", "Registro no creado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar el laboratorio");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

package org.example.apibitacora.service;

import org.example.apibitacora.model.EquipoComputo;
import org.example.apibitacora.model.dao.IEquipoComputoDao;
import org.example.apibitacora.response.EquipoComputoResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoComputoServiceImpl implements IEquipoComputoService{

    @Autowired
    private IEquipoComputoDao equipoComputoDao;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EquipoComputoResponseRest> getAllsEquipoComputos() {
        EquipoComputoResponseRest response = new EquipoComputoResponseRest();
        try{
            List<EquipoComputo> equipoComputos = equipoComputoDao.findAll();
            response.getEquipoComputoResponse().setEquipoComputos(equipoComputos);
            response.setMetada("Ok", "00", "Equipos de computo encontrado");
        }catch(Exception e){
            response.setMetada("Ok", "00", "Equipos no encontrado");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<EquipoComputoResponseRest> getEquipoComputoByCodigo(String codigoEquipo) {
        EquipoComputoResponseRest response = new EquipoComputoResponseRest();
        try {
            Optional <EquipoComputo> equipoOptionar = equipoComputoDao.findByCodigo(codigoEquipo);

            if(equipoOptionar.isPresent()){
                List<EquipoComputo> equipos = new ArrayList<>();
                equipos.add(equipoOptionar.get());
                response.getEquipoComputoResponse().setEquipoComputos(equipos);
                response.setMetada("Ok", "00", "Equipo encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                response.setMetada("Ok", "00", "Equipo no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al encontrar usuarios activos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<EquipoComputoResponseRest> crearEquipoComputo(EquipoComputo equipoComputo) {
        EquipoComputoResponseRest response = new EquipoComputoResponseRest();
        List<EquipoComputo> list = new ArrayList<>();
        try{
            Optional <EquipoComputo> equipoOptionar = equipoComputoDao.findByCodigo(equipoComputo.getCodigo());
            if(equipoOptionar.isPresent()){
                response.setMetada("Error", "-1", "El equipo ya existe");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            EquipoComputo equipoGuardado = equipoComputoDao.save(equipoComputo);
            if(equipoGuardado != null){
                list.add(equipoGuardado);
                response.getEquipoComputoResponse().setEquipoComputos(list);
                response.setMetada("Ok", "00", "Equipo creado");
            }else{
                response.setMetada("Ok", "00", "Equipo no creado");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            e.printStackTrace();
            response.setMetada("Respuesta No creada", "-1", "Error al crear el equipo");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<EquipoComputoResponseRest> actualizarEquipoComputo(String codigo, EquipoComputo equipoComputo) {
        EquipoComputoResponseRest response = new EquipoComputoResponseRest();
        List<EquipoComputo> list = new ArrayList<>();

        try {
            // Buscar el equipo por código
            Optional<EquipoComputo> equipoExistente = equipoComputoDao.findByCodigo(codigo);

            if (equipoExistente.isPresent()) {
                // Obtener el equipo actual
                EquipoComputo equipo = equipoExistente.get();

                // Actualizar solo los campos no nulos del equipoComputo proporcionado
                if (equipoComputo.getNumeroEnLab() != 0) {
                    equipo.setNumeroEnLab(equipoComputo.getNumeroEnLab());
                }
                if (equipoComputo.getCodigo() != null && !equipoComputo.getCodigo().equals(equipo.getCodigo())) {
                    // Validar que no exista otro equipo con el mismo código
                    Optional<EquipoComputo> equipoMismoCodigo = equipoComputoDao.findByCodigo(equipoComputo.getCodigo());
                    if (equipoMismoCodigo.isPresent()) {
                        response.setMetada("Error", "-1", "El código ya está en uso por otro equipo");
                        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
                    }
                    equipo.setCodigo(equipoComputo.getCodigo());
                }
                equipo.setStatus(equipoComputo.isStatus()); // Este es un booleano, no requiere validación nula
                if (equipoComputo.getLaboratorio() != null) {
                    equipo.setLaboratorio(equipoComputo.getLaboratorio());
                }

                // Guardar los cambios
                EquipoComputo equipoActualizado = equipoComputoDao.save(equipo);
                if (equipoActualizado != null) {
                    list.add(equipoActualizado);
                    response.getEquipoComputoResponse().setEquipoComputos(list);
                    response.setMetada("Ok", "00", "Equipo actualizado");
                } else {
                    response.setMetada("Error", "-1", "No se pudo guardar el equipo");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            } else {
                response.setMetada("Error", "-1", "El equipo no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetada("Error", "-1", "Error al actualizar el equipo");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @Override
    @Transactional
    public ResponseEntity<EquipoComputoResponseRest> cambiarStatusEquipoComputo(String codigo) {
        EquipoComputoResponseRest response = new EquipoComputoResponseRest();
        try{
            Optional<EquipoComputo> equipoExistente = equipoComputoDao.findByCodigo(codigo);
            if(equipoExistente.isEmpty()){
                response.setMetada("Error", "-1", "El equipo no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            EquipoComputo equipo = equipoExistente.get();
            equipo.setStatus(!equipo.isStatus());

            equipoComputoDao.save(equipo);
            response.setMetada("Ok", "00", "Equipo actualizado");
        }catch(Exception e){
            response.setMetada("Error", "-1", "Error al actualizar el equipo");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
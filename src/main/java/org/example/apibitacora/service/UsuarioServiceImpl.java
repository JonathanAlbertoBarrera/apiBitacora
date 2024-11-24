package org.example.apibitacora.service;

import org.example.apibitacora.model.Usuario;
import org.example.apibitacora.model.dao.IUsuarioDao;
import org.example.apibitacora.response.UsuarioResponseRest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDao usuarioDao;
    // Método para encriptar directamente con jBCrypt
    private String encryptPassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }


    //TODOS LOS USUARIOS
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> getAllUsuarios() {
        UsuarioResponseRest response = new UsuarioResponseRest();

        try {
            List<Usuario> usuarios = usuarioDao.findAll();
            response.getUsuarioResponse().setUsuario(usuarios);
            response.setMetada("Ok", "00", "Usuarios encontrados");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al encontrar usuarios");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODOS LOS USUARIOS, SIN EL ADMIN
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosSinAdmin() {
        UsuarioResponseRest response = new UsuarioResponseRest();

        try {
            List<Usuario> usuarios = usuarioDao.getAllUsuariosSinAdmin();
            response.getUsuarioResponse().setUsuario(usuarios);
            response.setMetada("Ok", "00", "Usuarios sin admin encontrados");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al encontrar usuarios sin admin");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODOS LOS USUARIOS (ACTIVOS), SIN EL ADMIN
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosActivos(){
        UsuarioResponseRest response=new UsuarioResponseRest();
        try {
            List<Usuario> usuarios = usuarioDao.getAllUsuariosActivos();
            response.getUsuarioResponse().setUsuario(usuarios);
            response.setMetada("Ok", "00", "Usuarios activos encontrados");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al encontrar usuarios activos");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CREAR UN USUARIO
    @Override
    @Transactional
    public ResponseEntity<UsuarioResponseRest> crearUsuario(Usuario usuario) {
        UsuarioResponseRest response = new UsuarioResponseRest();
        List<Usuario> list = new ArrayList<>();

        try {
            // Encriptar la contraseña
            usuario.setContrasenia(encryptPassword(usuario.getContrasenia()));

            Usuario usuarioGuardado = usuarioDao.save(usuario);
            if (usuarioGuardado != null) {
                list.add(usuarioGuardado);
                response.getUsuarioResponse().setUsuario(list);
                response.setMetada("Respuesta OK", "00", "Usuario creado exitosamente");
            } else {
                response.setMetada("Respuesta No Creada", "-1", "Usuario no creado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException e) {
            response.setMetada("Error", "-1", "El correo ya está en uso");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar el usuario");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
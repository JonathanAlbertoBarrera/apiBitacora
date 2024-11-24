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
import java.util.Optional;

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

    //OBTENER USUARIO MEDIANTE SU CORREO
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> getUsuarioByCorreo(String correo) {
        UsuarioResponseRest response = new UsuarioResponseRest();

        try {
            Optional<Usuario> usuarioOptional = usuarioDao.findByCorreo(correo);

            if (usuarioOptional.isPresent()) {
                // Si el usuario existe
                List<Usuario> usuarios = new ArrayList<>();
                usuarios.add(usuarioOptional.get());
                response.getUsuarioResponse().setUsuario(usuarios);
                response.setMetada("Ok", "00", "Usuario por correo encontrado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Si no se encontró el usuario
                response.setMetada("No encontrado", "01", "Usuario no encontrado con el correo: " + correo);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Manejo de excepciones generales
            response.setMetada("Error", "-1", "Error al buscar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método privado para verificar la contraseña ingresada contra la almacenada.
     */
    private boolean checkPassword(String rawPassword, String encryptedPassword) {
        // Comparar usando BCrypt o el método de encriptación que uses
        return BCrypt.checkpw(rawPassword, encryptedPassword);
    }

    //PARA INICIAR SESION
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UsuarioResponseRest> iniciarSesion(String correo, String contrasenia) {
        UsuarioResponseRest response = new UsuarioResponseRest();
        try {
            // Buscar el usuario por correo
            Optional<Usuario> usuarioOptional = usuarioDao.findByCorreo(correo);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                // Verificar la contraseña
                if (checkPassword(contrasenia, usuario.getContrasenia())) {
                    // Si las credenciales son correctas, construir la respuesta
                    List<Usuario> usuarios = new ArrayList<>();
                    usuarios.add(usuario);

                    response.getUsuarioResponse().setUsuario(usuarios);
                    response.setMetada("Ok", "00", "Inicio de sesión exitoso");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    // Contraseña incorrecta
                    response.setMetada("Error", "-1", "Contraseña incorrecta");
                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                }
            } else {
                // Usuario no encontrado
                response.setMetada("Error", "-1", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Manejo de errores generales
            response.setMetada("Error", "-1", "Error al iniciar sesión");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<UsuarioResponseRest> crearUsuario(Usuario usuario) {
        UsuarioResponseRest response = new UsuarioResponseRest();
        List<Usuario> list = new ArrayList<>();

        try {
            // Verificar si el correo ya está en uso
            Optional<Usuario> usuarioExistente = usuarioDao.findByCorreo(usuario.getCorreo());
            if (usuarioExistente.isPresent()) {
                response.setMetada("Error", "-1", "El correo ya está en uso");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            // Encriptar la contraseña
            usuario.setContrasenia(encryptPassword(usuario.getContrasenia()));

            // Guardar el usuario
            Usuario usuarioGuardado = usuarioDao.save(usuario);
            if (usuarioGuardado != null) {
                list.add(usuarioGuardado);
                response.getUsuarioResponse().setUsuario(list);
                response.setMetada("Respuesta OK", "00", "si Usuario creado exitosamente");
            } else {
                response.setMetada("Respuesta No Creada", "-1", "Usuario no creado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar el usuario");
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}

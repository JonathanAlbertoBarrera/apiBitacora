package org.example.apibitacora.service;

import org.example.apibitacora.model.Usuario;
import org.example.apibitacora.response.UsuarioResponseRest;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {
    public ResponseEntity<UsuarioResponseRest> getAllUsuarios();
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosSinAdmin();
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosActivos();
    public ResponseEntity<UsuarioResponseRest> getUsuarioByCorreo(String correo);

    public ResponseEntity<UsuarioResponseRest> iniciarSesion(String correo, String contrasenia);
    public ResponseEntity<UsuarioResponseRest> crearUsuario(Usuario usuario);
}

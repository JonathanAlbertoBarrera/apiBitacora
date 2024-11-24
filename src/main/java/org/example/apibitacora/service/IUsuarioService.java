package org.example.apibitacora.service;

import org.example.apibitacora.model.Usuario;
import org.example.apibitacora.response.UsuarioResponseRest;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {
    public ResponseEntity<UsuarioResponseRest> getAllUsuarios();
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosSinAdmin();
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosActivos();
    //agregar por id
    public ResponseEntity<UsuarioResponseRest> crearUsuario(Usuario usuario);
}

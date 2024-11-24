package org.example.apibitacora.Controller;

import org.example.apibitacora.model.Usuario;
import org.example.apibitacora.response.UsuarioResponseRest;
import org.example.apibitacora.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService service;

    //TODOS LOS USUARIOS
    @GetMapping("/Allusuarios")
    public ResponseEntity<UsuarioResponseRest> getAllUsuarios(){
        ResponseEntity<UsuarioResponseRest> response=service.getAllUsuarios();
        return response;
    }

    //TODOS LOS USUARIOS, SIN EL ADMIN
    @GetMapping("/usuariosSinAdmin")
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosSinAdmin(){
        ResponseEntity<UsuarioResponseRest> response=service.getAllUsuariosSinAdmin();
        return response;
    }

    //TODOS LOS USUARIOS (ACTIVOS), SIN EL ADMIN
    @GetMapping("/usuarios")
    public ResponseEntity<UsuarioResponseRest> getAllUsuariosActivos(){
        ResponseEntity<UsuarioResponseRest> response=service.getAllUsuariosActivos();
        return response;
    }

    //CREAR USUARIO
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponseRest> crearUsuario(@RequestBody Usuario usuario){
        ResponseEntity<UsuarioResponseRest> response=service.crearUsuario(usuario);
        return response;
    }

}

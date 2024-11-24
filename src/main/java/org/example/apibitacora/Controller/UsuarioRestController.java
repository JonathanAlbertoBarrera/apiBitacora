package org.example.apibitacora.Controller;

import org.example.apibitacora.model.Usuario;
import org.example.apibitacora.model.dto.UsuarioDTO;
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

    //OBTENER USUARIO MEDIANTE SU CORREO
    @GetMapping("/usuarios/{correo}")
    public ResponseEntity<UsuarioResponseRest> getUsuarioByCorreo(@PathVariable String correo){
        ResponseEntity<UsuarioResponseRest> response=service.getUsuarioByCorreo(correo);
        return response;
    }

    //INICIAR SESION
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseRest> iniciarSesion(@RequestParam String correo, @RequestParam String contrasenia) {
        ResponseEntity<UsuarioResponseRest> response=service.iniciarSesion(correo,contrasenia);
        return response;
    }

    //CREAR USUARIO
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponseRest> crearUsuario(@RequestBody Usuario usuario){
        ResponseEntity<UsuarioResponseRest> response=service.crearUsuario(usuario);
        return response;
    }

    //ACTUALIZAR USUARIO
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseRest> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        ResponseEntity<UsuarioResponseRest> response=service.actualizarUsuario(id,usuarioDTO);
        return  response;
    }
}

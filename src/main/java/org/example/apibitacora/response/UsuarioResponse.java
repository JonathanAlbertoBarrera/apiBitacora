package org.example.apibitacora.response;

import org.example.apibitacora.model.Usuario;

import java.util.List;

public class UsuarioResponse {
    private List<Usuario> usuario;

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }
}

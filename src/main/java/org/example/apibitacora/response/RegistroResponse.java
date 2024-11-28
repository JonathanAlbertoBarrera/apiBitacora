package org.example.apibitacora.response;

import org.example.apibitacora.model.Registro;

import java.util.List;

public class RegistroResponse {
    List<Registro> registros;

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }
}

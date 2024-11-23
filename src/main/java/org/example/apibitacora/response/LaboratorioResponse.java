package org.example.apibitacora.response;

import org.example.apibitacora.model.Laboratorio;

import java.util.List;

public class LaboratorioResponse {
    List<Laboratorio> laboratorios;

    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(List<Laboratorio> laboratorios) {
        this.laboratorios = laboratorios;
    }
}

package org.example.apibitacora.response;

import org.example.apibitacora.model.EquipoComputo;

import java.util.List;

public class EquipoComputoResponse {
    List<EquipoComputo> equipoComputos;

    public List<EquipoComputo> getEquipoComputos() {
        return equipoComputos;
    }

    public void setEquipoComputos(List<EquipoComputo> equipoComputos) {
        this.equipoComputos = equipoComputos;
    }
}

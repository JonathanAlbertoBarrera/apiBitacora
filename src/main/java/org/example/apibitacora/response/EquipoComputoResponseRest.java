package org.example.apibitacora.response;

public class EquipoComputoResponseRest extends ResponseRest{
    private EquipoComputoResponse equipoComputoResponse=new EquipoComputoResponse();

    public EquipoComputoResponse getEquipoComputoResponse() {
        return equipoComputoResponse;
    }

    public void setEquipoComputoResponse(EquipoComputoResponse equipoComputoResponse) {
        this.equipoComputoResponse = equipoComputoResponse;
    }
}

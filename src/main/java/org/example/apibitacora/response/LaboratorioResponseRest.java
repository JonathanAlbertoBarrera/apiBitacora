package org.example.apibitacora.response;

public class LaboratorioResponseRest extends ResponseRest{
    private LaboratorioResponse laboratorioResponse=new LaboratorioResponse();

    public LaboratorioResponse getLaboratorioResponse() {
        return laboratorioResponse;
    }

    public void setLaboratorioResponse(LaboratorioResponse laboratorioResponse) {
        this.laboratorioResponse = laboratorioResponse;
    }
}

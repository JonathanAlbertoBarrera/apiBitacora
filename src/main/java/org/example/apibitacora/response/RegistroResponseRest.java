package org.example.apibitacora.response;

public class RegistroResponseRest  extends ResponseRest{
    private RegistroResponse registroResponse=new RegistroResponse();

    public RegistroResponse getRegistroResponse() {
        return registroResponse;
    }

    public void setRegistroResponse(RegistroResponse registroResponse) {
        this.registroResponse = registroResponse;
    }
}

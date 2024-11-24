package org.example.apibitacora.model.dto;

public class UsuarioDTO {
    private String matricula;
    private String correo;
    private String nombre;
    private String carrera;
    private String grado_grupo;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getGrado_grupo() {
        return grado_grupo;
    }

    public void setGrado_grupo(String grado_grupo) {
        this.grado_grupo = grado_grupo;
    }
}

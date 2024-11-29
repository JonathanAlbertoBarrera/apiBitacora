package org.example.apibitacora.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="equiposcomputo")
public class EquipoComputo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_equipo;

    private int numeroEnLab; //por ejemplo: 07. ES LA NUMERACION EN LA VIDA REAL QUE TIENEN LAS PC
    private String codigo; //al escanear el QR, es lo que se obtiene
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Laboratorio laboratorio;


    public Long getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(Long id_equipo) {
        this.id_equipo = id_equipo;
    }

    public int getNumeroEnLab() {
        return numeroEnLab;
    }

    public void setNumeroEnLab(int numeroEnLab) {
        this.numeroEnLab = numeroEnLab;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
}

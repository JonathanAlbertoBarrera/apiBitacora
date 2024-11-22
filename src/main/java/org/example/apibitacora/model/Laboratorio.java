package org.example.apibitacora.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "laboratorios")
public class Laboratorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lab;

    private String nombre_lab;//por ejemplo CC11, esto es llenado por el admin segun la realidad
    private int docencia;
    private boolean estatus;

    public Long getId_lab() {
        return id_lab;
    }

    public void setId_lab(Long id_lab) {
        this.id_lab = id_lab;
    }

    public String getNombre_lab() {
        return nombre_lab;
    }

    public void setNombre_lab(String nombre_lab) {
        this.nombre_lab = nombre_lab;
    }

    public int getDocencia() {
        return docencia;
    }

    public void setDocencia(int docencia) {
        this.docencia = docencia;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
}

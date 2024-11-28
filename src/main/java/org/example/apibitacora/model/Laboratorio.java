package org.example.apibitacora.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "laboratorios")
public class Laboratorio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lab;

    private String nombre_lab;//por ejemplo CC11, esto es llenado por el admin segun la realidad
    private int docencia;
    private boolean estatus;

    @OneToMany(mappedBy = "laboratorio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Registro> registros;

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

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

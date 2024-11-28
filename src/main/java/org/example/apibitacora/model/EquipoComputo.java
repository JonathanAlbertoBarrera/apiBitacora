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
    private String valorCodigo; //al escanear el QR, es lo que se obtiene
    private boolean estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Laboratorio laboratorio;


    @OneToMany(mappedBy = "equipoComputo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Registro> registros;

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

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

    public String getValorCodigo() {
        return valorCodigo;
    }

    public void setValorCodigo(String valorCodigo) {
        this.valorCodigo = valorCodigo;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
}

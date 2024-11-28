package org.example.apibitacora.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registros")
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_registro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_alumno", nullable = false)
    private Usuario usuario; // Relación con el alumno (usuario)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lab", nullable = false)
    private Laboratorio laboratorio; // Relación con el laboratorio

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", nullable = false)
    private EquipoComputo equipoComputo; // Relación con el equipo de cómputo

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaInicial;

    @Column(nullable = false)
    private LocalTime horaFinal;

    @Column(nullable = false)
    private String docente; // Nombre del docente responsable

    private String comentario;

    @Column(nullable = false)
    private boolean estatus;

    // Getters y setters
    public Long getId_registro() {
        return id_registro;
    }

    public void setId_registro(Long id_registro) {
        this.id_registro = id_registro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public EquipoComputo getEquipoComputo() {
        return equipoComputo;
    }

    public void setEquipoComputo(EquipoComputo equipoComputo) {
        this.equipoComputo = equipoComputo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
}


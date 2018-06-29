
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RestReservaGeneric implements Serializable{
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int columna;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int fila;
    private String numTelefonoid;
    private Integer idProyeccionid;
    
    public RestReservaGeneric() {
    }
    
    public RestReservaGeneric(String estado, String numTelefonoid, int idProyeccionid, int columna, int fila ) {
        this.estado = estado;
        this.numTelefonoid = numTelefonoid;
        this.idProyeccionid = idProyeccionid;
        this.columna = columna;
        this.fila = fila;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getNumTelefonoid() {
        return numTelefonoid;
    }

    public void setNumTelefonoid(String numTelefonoid) {
        this.numTelefonoid = numTelefonoid;
    }

    public Integer getIdProyeccionid() {
        return idProyeccionid;
    }

    public void setIdProyeccionid(Integer idProyeccionid) {
        this.idProyeccionid = idProyeccionid;
    }
    
    
    
}

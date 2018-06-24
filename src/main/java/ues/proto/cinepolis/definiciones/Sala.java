/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevinmanjaro
 */
@Entity
@Table(catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")
    , @NamedQuery(name = "Sala.findByNumSala", query = "SELECT s FROM Sala s WHERE s.numSala = :numSala")
    , @NamedQuery(name = "Sala.findByCapacidad", query = "SELECT s FROM Sala s WHERE s.capacidad = :capacidad")
    , @NamedQuery(name = "Sala.findByFilas", query = "SELECT s FROM Sala s WHERE s.filas = :filas")
    , @NamedQuery(name = "Sala.findByColumnas", query = "SELECT s FROM Sala s WHERE s.columnas = :columnas")
    , @NamedQuery(name = "Sala.findByTipoSala", query = "SELECT s FROM Sala s WHERE s.tipoSala = :tipoSala")
    , @NamedQuery(name = "Sala.findByIdCine", query = "SELECT s FROM Sala s WHERE s.idCine = :idCine")})
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer numSala;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int capacidad;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int filas;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int columnas;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int tipoSala;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idCine;

    public Sala() {
    }

    public Sala(Integer numSala) {
        this.numSala = numSala;
    }

    public Sala(Integer numSala, int capacidad, int filas, int columnas, int tipoSala, int idCine) {
        this.numSala = numSala;
        this.capacidad = capacidad;
        this.filas = filas;
        this.columnas = columnas;
        this.tipoSala = tipoSala;
        this.idCine = idCine;
    }

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(int tipoSala) {
        this.tipoSala = tipoSala;
    }

    public int getIdCine() {
        return idCine;
    }

    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSala != null ? numSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.numSala == null && other.numSala != null) || (this.numSala != null && !this.numSala.equals(other.numSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.Sala[ numSala=" + numSala + " ]";
    }
    
}

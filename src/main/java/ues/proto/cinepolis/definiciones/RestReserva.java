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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevinmanjaro
 */
@Entity
@Table(name = "rest_reserva", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestReserva.findAll", query = "SELECT r FROM RestReserva r")
    , @NamedQuery(name = "RestReserva.findByIdReserva", query = "SELECT r FROM RestReserva r WHERE r.idReserva = :idReserva")
    , @NamedQuery(name = "RestReserva.findByEstado", query = "SELECT r FROM RestReserva r WHERE r.estado = :estado")
    , @NamedQuery(name = "RestReserva.findByColumna", query = "SELECT r FROM RestReserva r WHERE r.columna = :columna")
    , @NamedQuery(name = "RestReserva.findByFila", query = "SELECT r FROM RestReserva r WHERE r.fila = :fila")})
public class RestReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idReserva;
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

    public RestReserva() {
    }

    public RestReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public RestReserva(Integer idReserva, String estado, String numTelefonoid, int idProyeccionid, int columna, int fila ) {
        this.idReserva = idReserva;
        this.estado = estado;
        this.numTelefonoid = numTelefonoid;
        this.idProyeccionid = idProyeccionid;
        this.columna = columna;
        this.fila = fila;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestReserva)) {
            return false;
        }
        RestReserva other = (RestReserva) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestReserva[ idReserva=" + idReserva + " ]";
    }
    
}

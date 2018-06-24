/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Proyeccion.findAll", query = "SELECT p FROM Proyeccion p")
    , @NamedQuery(name = "Proyeccion.findByIdProyeccion", query = "SELECT p FROM Proyeccion p WHERE p.idProyeccion = :idProyeccion")
    , @NamedQuery(name = "Proyeccion.findByFecha", query = "SELECT p FROM Proyeccion p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Proyeccion.findByIdPelicula", query = "SELECT p FROM Proyeccion p WHERE p.idPelicula = :idPelicula")
    , @NamedQuery(name = "Proyeccion.findByNumSala", query = "SELECT p FROM Proyeccion p WHERE p.numSala = :numSala")
    , @NamedQuery(name = "Proyeccion.findByHorario", query = "SELECT p FROM Proyeccion p WHERE p.horario = :horario")})
public class Proyeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idProyeccion;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Integer idPelicula;
    private Integer numSala;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    public Proyeccion() {
    }

    public Proyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Integer getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyeccion != null ? idProyeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyeccion)) {
            return false;
        }
        Proyeccion other = (Proyeccion) object;
        if ((this.idProyeccion == null && other.idProyeccion != null) || (this.idProyeccion != null && !this.idProyeccion.equals(other.idProyeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.Proyeccion[ idProyeccion=" + idProyeccion + " ]";
    }
    
}

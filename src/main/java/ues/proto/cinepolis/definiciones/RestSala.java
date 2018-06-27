/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevinmanjaro
 */
@Entity
@Table(name = "rest_sala", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestSala.findAll", query = "SELECT r FROM RestSala r")
    , @NamedQuery(name = "RestSala.findById", query = "SELECT r FROM RestSala r WHERE r.id = :id")
    , @NamedQuery(name = "RestSala.findByNumSala", query = "SELECT r FROM RestSala r WHERE r.numSala = :numSala")
    , @NamedQuery(name = "RestSala.findByFilas", query = "SELECT r FROM RestSala r WHERE r.filas = :filas")
    , @NamedQuery(name = "RestSala.findByColumnas", query = "SELECT r FROM RestSala r WHERE r.columnas = :columnas")})
public class RestSala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int numSala;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int filas;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int columnas;
    @JoinColumn(name = "idCine_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RestCine idCineid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSalaid")
    private List<RestProyeccion> restProyeccionList;

    public RestSala() {
    }

    public RestSala(Integer id) {
        this.id = id;
    }

    public RestSala(Integer id, int numSala, int filas, int columnas) {
        this.id = id;
        this.numSala = numSala;
        this.filas = filas;
        this.columnas = columnas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
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

    public RestCine getIdCineid() {
        return idCineid;
    }

    public void setIdCineid(RestCine idCineid) {
        this.idCineid = idCineid;
    }

    @XmlTransient
    public List<RestProyeccion> getRestProyeccionList() {
        return restProyeccionList;
    }

    public void setRestProyeccionList(List<RestProyeccion> restProyeccionList) {
        this.restProyeccionList = restProyeccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestSala)) {
            return false;
        }
        RestSala other = (RestSala) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestSala[ id=" + id + " ]";
    }
    
}

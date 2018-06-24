/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevinmanjaro
 */
@Entity
@Table(name = "rest_proyeccion", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestProyeccion.findAll", query = "SELECT r FROM RestProyeccion r")
    , @NamedQuery(name = "RestProyeccion.findByIdProyeccion", query = "SELECT r FROM RestProyeccion r WHERE r.idProyeccion = :idProyeccion")
    , @NamedQuery(name = "RestProyeccion.findByFecha", query = "SELECT r FROM RestProyeccion r WHERE r.fecha = :fecha")})
public class RestProyeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idProyeccion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idPelicula_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RestPelicula idPeliculaid;
    @JoinColumn(name = "idSala_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RestSala idSalaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyeccionid")
    private List<RestReserva> restReservaList;

    public RestProyeccion() {
    }

    public RestProyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public RestProyeccion(Integer idProyeccion, Date fecha) {
        this.idProyeccion = idProyeccion;
        this.fecha = fecha;
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

    public RestPelicula getIdPeliculaid() {
        return idPeliculaid;
    }

    public void setIdPeliculaid(RestPelicula idPeliculaid) {
        this.idPeliculaid = idPeliculaid;
    }

    public RestSala getIdSalaid() {
        return idSalaid;
    }

    public void setIdSalaid(RestSala idSalaid) {
        this.idSalaid = idSalaid;
    }

    @XmlTransient
    public List<RestReserva> getRestReservaList() {
        return restReservaList;
    }

    public void setRestReservaList(List<RestReserva> restReservaList) {
        this.restReservaList = restReservaList;
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
        if (!(object instanceof RestProyeccion)) {
            return false;
        }
        RestProyeccion other = (RestProyeccion) object;
        if ((this.idProyeccion == null && other.idProyeccion != null) || (this.idProyeccion != null && !this.idProyeccion.equals(other.idProyeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestProyeccion[ idProyeccion=" + idProyeccion + " ]";
    }
    
}

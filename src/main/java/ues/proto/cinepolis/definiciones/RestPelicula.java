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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevinmanjaro
 */
@Entity
@Table(name = "rest_pelicula", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestPelicula.findAll", query = "SELECT r FROM RestPelicula r")
    , @NamedQuery(name = "RestPelicula.findById", query = "SELECT r FROM RestPelicula r WHERE r.id = :id")
    , @NamedQuery(name = "RestPelicula.findByNombre", query = "SELECT r FROM RestPelicula r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RestPelicula.findByDescripcion", query = "SELECT r FROM RestPelicula r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "RestPelicula.findByClasificacion", query = "SELECT r FROM RestPelicula r WHERE r.clasificacion = :clasificacion")
    , @NamedQuery(name = "RestPelicula.findByPortada", query = "SELECT r FROM RestPelicula r WHERE r.portada = :portada")})
public class RestPelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(nullable = false)
    private Object duracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String clasificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String portada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeliculaid")
    private List<RestProyeccion> restProyeccionList;
    @JoinColumn(name = "idGenero_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RestGenero idGeneroid;

    public RestPelicula() {
    }

    public RestPelicula(Integer id) {
        this.id = id;
    }

    public RestPelicula(Integer id, String nombre, Object duracion, String descripcion, String clasificacion, String portada) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.clasificacion = clasificacion;
        this.portada = portada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getDuracion() {
        return duracion;
    }

    public void setDuracion(Object duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    @XmlTransient
    public List<RestProyeccion> getRestProyeccionList() {
        return restProyeccionList;
    }

    public void setRestProyeccionList(List<RestProyeccion> restProyeccionList) {
        this.restProyeccionList = restProyeccionList;
    }

    public RestGenero getIdGeneroid() {
        return idGeneroid;
    }

    public void setIdGeneroid(RestGenero idGeneroid) {
        this.idGeneroid = idGeneroid;
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
        if (!(object instanceof RestPelicula)) {
            return false;
        }
        RestPelicula other = (RestPelicula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestPelicula[ id=" + id + " ]";
    }
    
}

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
@Table(name = "rest_genero", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestGenero.findAll", query = "SELECT r FROM RestGenero r")
    , @NamedQuery(name = "RestGenero.findById", query = "SELECT r FROM RestGenero r WHERE r.id = :id")
    , @NamedQuery(name = "RestGenero.findByNombre", query = "SELECT r FROM RestGenero r WHERE r.nombre = :nombre")})
public class RestGenero implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGeneroid")
    private List<RestPelicula> restPeliculaList;

    public RestGenero() {
    }

    public RestGenero(Integer id) {
        this.id = id;
    }

    public RestGenero(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @XmlTransient
    public List<RestPelicula> getRestPeliculaList() {
        return restPeliculaList;
    }

    public void setRestPeliculaList(List<RestPelicula> restPeliculaList) {
        this.restPeliculaList = restPeliculaList;
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
        if (!(object instanceof RestGenero)) {
            return false;
        }
        RestGenero other = (RestGenero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestGenero[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "rest_tiporeserva", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestTiporeserva.findAll", query = "SELECT r FROM RestTiporeserva r")
    , @NamedQuery(name = "RestTiporeserva.findById", query = "SELECT r FROM RestTiporeserva r WHERE r.id = :id")
    , @NamedQuery(name = "RestTiporeserva.findByNombre", query = "SELECT r FROM RestTiporeserva r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RestTiporeserva.findByPrecio", query = "SELECT r FROM RestTiporeserva r WHERE r.precio = :precio")})
public class RestTiporeserva implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoReservaid")
    private List<RestDetallereserva> restDetallereservaList;

    public RestTiporeserva() {
    }

    public RestTiporeserva(Integer id) {
        this.id = id;
    }

    public RestTiporeserva(Integer id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<RestDetallereserva> getRestDetallereservaList() {
        return restDetallereservaList;
    }

    public void setRestDetallereservaList(List<RestDetallereserva> restDetallereservaList) {
        this.restDetallereservaList = restDetallereservaList;
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
        if (!(object instanceof RestTiporeserva)) {
            return false;
        }
        RestTiporeserva other = (RestTiporeserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestTiporeserva[ id=" + id + " ]";
    }
    
}

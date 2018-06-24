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
    @NamedQuery(name = "TipoReserva.findAll", query = "SELECT t FROM TipoReserva t")
    , @NamedQuery(name = "TipoReserva.findByIdTipoReserva", query = "SELECT t FROM TipoReserva t WHERE t.idTipoReserva = :idTipoReserva")
    , @NamedQuery(name = "TipoReserva.findByIdTipoPelicula", query = "SELECT t FROM TipoReserva t WHERE t.idTipoPelicula = :idTipoPelicula")
    , @NamedQuery(name = "TipoReserva.findByPrecio", query = "SELECT t FROM TipoReserva t WHERE t.precio = :precio")})
public class TipoReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idTipoReserva;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idTipoPelicula;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double precio;

    public TipoReserva() {
    }

    public TipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public TipoReserva(Integer idTipoReserva, int idTipoPelicula, double precio) {
        this.idTipoReserva = idTipoReserva;
        this.idTipoPelicula = idTipoPelicula;
        this.precio = precio;
    }

    public Integer getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(Integer idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public int getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(int idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoReserva != null ? idTipoReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReserva)) {
            return false;
        }
        TipoReserva other = (TipoReserva) object;
        if ((this.idTipoReserva == null && other.idTipoReserva != null) || (this.idTipoReserva != null && !this.idTipoReserva.equals(other.idTipoReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.TipoReserva[ idTipoReserva=" + idTipoReserva + " ]";
    }
    
}

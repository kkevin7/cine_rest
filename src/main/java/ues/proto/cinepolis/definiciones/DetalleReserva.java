/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "DetalleReserva.findAll", query = "SELECT d FROM DetalleReserva d")
    , @NamedQuery(name = "DetalleReserva.findByIdReserva", query = "SELECT d FROM DetalleReserva d WHERE d.detalleReservaPK.idReserva = :idReserva")
    , @NamedQuery(name = "DetalleReserva.findByAsiento", query = "SELECT d FROM DetalleReserva d WHERE d.detalleReservaPK.asiento = :asiento")
    , @NamedQuery(name = "DetalleReserva.findByIdTipoReserva", query = "SELECT d FROM DetalleReserva d WHERE d.idTipoReserva = :idTipoReserva")})
public class DetalleReserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleReservaPK detalleReservaPK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int idTipoReserva;

    public DetalleReserva() {
    }

    public DetalleReserva(DetalleReservaPK detalleReservaPK) {
        this.detalleReservaPK = detalleReservaPK;
    }

    public DetalleReserva(DetalleReservaPK detalleReservaPK, int idTipoReserva) {
        this.detalleReservaPK = detalleReservaPK;
        this.idTipoReserva = idTipoReserva;
    }

    public DetalleReserva(Serializable idReserva, Serializable asiento) {
        this.detalleReservaPK = new DetalleReservaPK(idReserva, asiento);
    }

    public DetalleReservaPK getDetalleReservaPK() {
        return detalleReservaPK;
    }

    public void setDetalleReservaPK(DetalleReservaPK detalleReservaPK) {
        this.detalleReservaPK = detalleReservaPK;
    }

    public int getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(int idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleReservaPK != null ? detalleReservaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleReserva)) {
            return false;
        }
        DetalleReserva other = (DetalleReserva) object;
        if ((this.detalleReservaPK == null && other.detalleReservaPK != null) || (this.detalleReservaPK != null && !this.detalleReservaPK.equals(other.detalleReservaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.DetalleReserva[ detalleReservaPK=" + detalleReservaPK + " ]";
    }
    
}

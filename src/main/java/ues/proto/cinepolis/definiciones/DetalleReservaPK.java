/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.definiciones;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevinmanjaro
 */
@Embeddable
public class DetalleReservaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Serializable idReserva;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Serializable asiento;

    public DetalleReservaPK() {
    }

    public DetalleReservaPK(Serializable idReserva, Serializable asiento) {
        this.idReserva = idReserva;
        this.asiento = asiento;
    }

    public Serializable getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Serializable idReserva) {
        this.idReserva = idReserva;
    }

    public Serializable getAsiento() {
        return asiento;
    }

    public void setAsiento(Serializable asiento) {
        this.asiento = asiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        hash += (asiento != null ? asiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleReservaPK)) {
            return false;
        }
        DetalleReservaPK other = (DetalleReservaPK) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        if ((this.asiento == null && other.asiento != null) || (this.asiento != null && !this.asiento.equals(other.asiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.DetalleReservaPK[ idReserva=" + idReserva + ", asiento=" + asiento + " ]";
    }
    
}

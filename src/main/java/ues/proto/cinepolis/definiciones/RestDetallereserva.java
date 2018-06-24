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
@Table(name = "rest_detallereserva", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestDetallereserva.findAll", query = "SELECT r FROM RestDetallereserva r")
    , @NamedQuery(name = "RestDetallereserva.findById", query = "SELECT r FROM RestDetallereserva r WHERE r.id = :id")
    , @NamedQuery(name = "RestDetallereserva.findByAsiento", query = "SELECT r FROM RestDetallereserva r WHERE r.asiento = :asiento")})
public class RestDetallereserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String asiento;
    @JoinColumn(name = "idReserva_id", referencedColumnName = "idReserva", nullable = false)
    @ManyToOne(optional = false)
    private RestReserva idReservaid;
    @JoinColumn(name = "idTipoReserva_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private RestTiporeserva idTipoReservaid;

    public RestDetallereserva() {
    }

    public RestDetallereserva(Integer id) {
        this.id = id;
    }

    public RestDetallereserva(Integer id, String asiento) {
        this.id = id;
        this.asiento = asiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public RestReserva getIdReservaid() {
        return idReservaid;
    }

    public void setIdReservaid(RestReserva idReservaid) {
        this.idReservaid = idReservaid;
    }

    public RestTiporeserva getIdTipoReservaid() {
        return idTipoReservaid;
    }

    public void setIdTipoReservaid(RestTiporeserva idTipoReservaid) {
        this.idTipoReservaid = idTipoReservaid;
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
        if (!(object instanceof RestDetallereserva)) {
            return false;
        }
        RestDetallereserva other = (RestDetallereserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestDetallereserva[ id=" + id + " ]";
    }
    
}

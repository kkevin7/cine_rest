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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevinmanjaro
 */
@Entity
@Table(name = "rest_cliente", catalog = "Cinepolis", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestCliente.findAll", query = "SELECT r FROM RestCliente r")
    , @NamedQuery(name = "RestCliente.findByNumTelefono", query = "SELECT r FROM RestCliente r WHERE r.numTelefono = :numTelefono")
    , @NamedQuery(name = "RestCliente.findByNombre", query = "SELECT r FROM RestCliente r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "RestCliente.findByApellido", query = "SELECT r FROM RestCliente r WHERE r.apellido = :apellido")
    , @NamedQuery(name = "RestCliente.findByFechaNacimiento", query = "SELECT r FROM RestCliente r WHERE r.fechaNacimiento = :fechaNacimiento")})
public class RestCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String numTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numTelefonoid")
    private List<RestReserva> restReservaList;

    public RestCliente() {
    }

    public RestCliente(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public RestCliente(String numTelefono, String nombre, String apellido, Date fechaNacimiento) {
        this.numTelefono = numTelefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
        hash += (numTelefono != null ? numTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestCliente)) {
            return false;
        }
        RestCliente other = (RestCliente) object;
        if ((this.numTelefono == null && other.numTelefono != null) || (this.numTelefono != null && !this.numTelefono.equals(other.numTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ues.proto.cinepolis.definiciones.RestCliente[ numTelefono=" + numTelefono + " ]";
    }
    
}

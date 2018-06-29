
package ues.proto.cinepolis.rest;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FrmMenssages {
    
    public void msgCreadoExito() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente registado!", "Creado con Exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void msgNosecontrocliente() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se contro el cliente"));
    }
    
    public void msgFilaColumna() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Necesita que la columna y filas sean mayores a cero"));
    }
    
    public void msgErrorCliente() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Necesita un cliente valido"));
    }
    
    public void msgErrorEstado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Estado no valido"));
    }
    
    public void msgErrorEntity() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Entity vacia"));
    }
}

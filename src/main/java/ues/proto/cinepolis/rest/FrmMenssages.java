
package ues.proto.cinepolis.rest;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FrmMenssages {
    
    public void msgCreadoExito() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente registado!", "Creado con Exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}

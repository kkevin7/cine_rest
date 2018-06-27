
package ues.proto.cinepolis.rest;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FlowEvent;


@Named(value = "wizardPrimefaces")
@ViewScoped
public class WizardPrimefaces implements Serializable{

    public WizardPrimefaces()  {
    }
     
    private boolean skip;
     
    public boolean isSkip() {
        return skip;
    }
 
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
     
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
    
}

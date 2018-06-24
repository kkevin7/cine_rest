
package ues.proto.cinepolis.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import ues.proto.cinepolis.definiciones.RestTiporeserva;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;


@Named(value = "tipoReservaClient")
@ViewScoped
public class TipoReservaClient extends GenericURL implements Serializable{
    
    private final static String UrlResource = BASE_URI + "tipoReserva/";
    private Client cliente;
    private RestTiporeserva tiporeservaEntity;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestTiporeserva> listaReserva;
    
    public TipoReservaClient() {
        try {
            cliente = ClientBuilder.newClient();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    /**
     * Este metodo sirve sirve para que se inicie todo despues de cargar los
     * form.
     */
    @PostConstruct
    public void init() {
        this.tiporeservaEntity = new RestTiporeserva();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaReserva = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestTiporeserva>>() {
                    });
        } catch (Exception e) {
            System.err.println("No se puede llenar la tabla revisa ---------------------*****-------");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Este metodo sirve para llenar la lista que se ocupara en la vista.
     */
    @Deprecated
    public List<RestTiporeserva> findAll() {
        List<RestTiporeserva> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestTiporeserva>>() {
            });
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (this.cliente == null) {
                salida = new ArrayList();
            }
        }
        return salida;
    }

    public List<RestTiporeserva> findRange(int first, int pageSize) {
        List<RestTiporeserva> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestTiporeserva>>() {
            });
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (this.cliente == null) {
                salida = new ArrayList();
            }
        }
        return salida;
    }

    public int count() {
        try {
            WebTarget target = cliente.target(UrlResource).path("count");
            Integer salida = target.request(MediaType.APPLICATION_JSON).get(Integer.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return 0;
    }
    
    public RestTiporeserva findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
            RestTiporeserva salida = target.request(MediaType.APPLICATION_JSON).get(RestTiporeserva.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestTiporeserva crearRegistro() {
        if (tiporeservaEntity != null && tiporeservaEntity.getId() != null )  {
            try {
                RestTiporeserva salida = cliente.target(UrlResource)
                        .path("crear")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(tiporeservaEntity, MediaType.APPLICATION_JSON), RestTiporeserva.class);
                if (salida != null && salida.getId() != null ) {
                    return salida;
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (this.cliente != null) {
                this.cliente.close();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public RestTiporeserva getTiporeservaEntity() {
        return tiporeservaEntity;
    }

    public void setTiporeservaEntity(RestTiporeserva tiporeservaEntity) {
        this.tiporeservaEntity = tiporeservaEntity;
    }

    public List<RestTiporeserva> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(List<RestTiporeserva> listaReserva) {
        this.listaReserva = listaReserva;
    }
    
    
    
}

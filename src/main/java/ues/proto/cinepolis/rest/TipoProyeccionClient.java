
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
import ues.proto.cinepolis.definiciones.RestTipoproyeccion;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;


@Named(value = "tipoReservaClient")
@ViewScoped
public class TipoProyeccionClient extends GenericURL implements Serializable{
    
    private final static String UrlResource = BASE_URI + "tipoReserva/";
    private Client cliente;
    private RestTipoproyeccion tiporeservaEntity;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestTipoproyeccion> listaReserva;
    
    public TipoProyeccionClient() {
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
        this.tiporeservaEntity = new RestTipoproyeccion();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaReserva = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestTipoproyeccion>>() {
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
    public List<RestTipoproyeccion> findAll() {
        List<RestTipoproyeccion> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestTipoproyeccion>>() {
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

    public List<RestTipoproyeccion> findRange(int first, int pageSize) {
        List<RestTipoproyeccion> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestTipoproyeccion>>() {
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
    
    public RestTipoproyeccion findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
            RestTipoproyeccion salida = target.request(MediaType.APPLICATION_JSON).get(RestTipoproyeccion.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestTipoproyeccion crearRegistro() {
        if (tiporeservaEntity != null && tiporeservaEntity.getId() != null )  {
            try {
                RestTipoproyeccion salida = cliente.target(UrlResource)
                        .path("crear")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(tiporeservaEntity, MediaType.APPLICATION_JSON), RestTipoproyeccion.class);
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

    public RestTipoproyeccion getTiporeservaEntity() {
        return tiporeservaEntity;
    }

    public void setTiporeservaEntity(RestTipoproyeccion tiporeservaEntity) {
        this.tiporeservaEntity = tiporeservaEntity;
    }

    public List<RestTipoproyeccion> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(List<RestTipoproyeccion> listaReserva) {
        this.listaReserva = listaReserva;
    }
    
    
    
    
}

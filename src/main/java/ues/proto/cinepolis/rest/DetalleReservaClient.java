
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
import ues.proto.cinepolis.definiciones.RestDetallereserva;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;

@Named(value = "detalleReservaClient")
@ViewScoped
public class DetalleReservaClient extends GenericURL implements Serializable{
    
    private final static String UrlResource = BASE_URI + "detalleReserva/";
    private Client cliente;
    private RestDetallereserva claseEntity;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestDetallereserva> lista;

    public DetalleReservaClient() {
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
        this.claseEntity = new RestDetallereserva();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            lista = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestDetallereserva>>() {
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
    public List<RestDetallereserva> findAll() {
        List<RestDetallereserva> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestDetallereserva>>() {
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

    public List<RestDetallereserva> findRange(int first, int pageSize) {
        List<RestDetallereserva> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestDetallereserva>>() {
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
    
    public RestDetallereserva findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
            RestDetallereserva salida = target.request(MediaType.APPLICATION_JSON).get(RestDetallereserva.class);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestDetallereserva crearRegistro() {
        if (claseEntity != null && claseEntity.getAsiento() != null )  {
            try {
                RestDetallereserva salida = cliente.target(UrlResource)
                        .path("crear")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(claseEntity, MediaType.APPLICATION_JSON), RestDetallereserva.class);
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

    public RestDetallereserva getClaseEntity() {
        return claseEntity;
    }

    public void setClaseEntity(RestDetallereserva claseEntity) {
        this.claseEntity = claseEntity;
    }

    public List<RestDetallereserva> getLista() {
        return lista;
    }

    public void setLista(List<RestDetallereserva> lista) {
        this.lista = lista;
    }
    
    
    
    
}

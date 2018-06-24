
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
import ues.proto.cinepolis.definiciones.RestProyeccion;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;


@Named(value = "proyeccionclient")
@ViewScoped
public class ProyeccionClient extends GenericURL implements Serializable {
    
    private final static String UrlResource = BASE_URI + "proyeccion/";
    private Client cliente;
    private RestProyeccion proyeccionEntity;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestProyeccion> listaProyeccion;

    public ProyeccionClient() {
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
        this.proyeccionEntity = new RestProyeccion();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaProyeccion = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestProyeccion>>() {
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
    public List<RestProyeccion> findAll() {
        List<RestProyeccion> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestProyeccion>>() {
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

    public List<RestProyeccion> findRange(int first, int pageSize) {
        List<RestProyeccion> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestProyeccion>>() {
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
    
    public RestProyeccion findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{idProyeccion}").resolveTemplate("idProyeccion", id);
            RestProyeccion salida = target.request(MediaType.APPLICATION_JSON).get(RestProyeccion.class);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestProyeccion crearRegistro() {
        if (proyeccionEntity != null && proyeccionEntity.getIdProyeccion() != null )  {
            try {
                RestProyeccion salida = cliente.target(UrlResource)
                        .path("crear")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(proyeccionEntity, MediaType.APPLICATION_JSON), RestProyeccion.class);
                if (salida != null && salida.getFecha() != null ) {
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

    public RestProyeccion getProyeccionEntity() {
        return proyeccionEntity;
    }

    public void setProyeccionEntity(RestProyeccion proyeccionEntity) {
        this.proyeccionEntity = proyeccionEntity;
    }

    public List<RestProyeccion> getListaProyeccion() {
        return listaProyeccion;
    }

    public void setListaProyeccion(List<RestProyeccion> listaProyeccion) {
        this.listaProyeccion = listaProyeccion;
    }


    
    
}

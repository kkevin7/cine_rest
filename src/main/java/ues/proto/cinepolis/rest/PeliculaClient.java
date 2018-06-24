
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
import ues.proto.cinepolis.definiciones.RestPelicula;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;


@Named(value = "peliculaclient")
@ViewScoped
public class PeliculaClient extends GenericURL implements Serializable {

    private final static String UrlResource = BASE_URI + "pelicula/";
    private Client cliente;
    private RestPelicula peliculaEntity;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestPelicula> listaPelicula; 
    
    public PeliculaClient() {
        try {
            cliente = ClientBuilder.newClient();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    @PostConstruct
    public void init() {
        this.peliculaEntity = new RestPelicula();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaPelicula = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestPelicula>>() {
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
    public List<RestPelicula> findAll() {
        List<RestPelicula> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestPelicula>>() {
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

    public List<RestPelicula> findRange(int first, int pageSize) {
        List<RestPelicula> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestPelicula>>() {
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
    
    public RestPelicula findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
            RestPelicula salida = target.request(MediaType.APPLICATION_JSON).get(RestPelicula.class);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestPelicula crearRegistro() {
        if (peliculaEntity != null && peliculaEntity.getNombre() != null )  {
            try {
                RestPelicula salida = cliente.target(UrlResource)
                        .path("crear")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(peliculaEntity, MediaType.APPLICATION_JSON), RestPelicula.class);
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

    public RestPelicula getPeliculaEntity() {
        return peliculaEntity;
    }

    public void setPeliculaEntity(RestPelicula peliculaEntity) {
        this.peliculaEntity = peliculaEntity;
    }

    public List<RestPelicula> getListaPelicula() {
        return listaPelicula;
    }

    public void setListaPelicula(List<RestPelicula> listaPelicula) {
        this.listaPelicula = listaPelicula;
    }

    
    
    
}

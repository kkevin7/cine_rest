
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
import ues.proto.cinepolis.definiciones.RestGenero;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;

@Named(value = "generoclient")
@ViewScoped
public class GeneroClient extends GenericURL implements Serializable{

    private final static String UrlResource = BASE_URI + "genero";
    private Client cliente;
    private RestGenero generoEntity;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestGenero> listaGenero;
    
    public GeneroClient() {
        try {
            cliente = ClientBuilder.newClient();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    @PostConstruct
    public void init() {
        this.generoEntity = new RestGenero();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaGenero = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestGenero>>() {
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
    public List<RestGenero> findAll() {
        List<RestGenero> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestGenero>>() {
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

    public List<RestGenero> findRange(int first, int pageSize) {
        List<RestGenero> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestGenero>>() {
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
    
    public RestGenero findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{idCine}").resolveTemplate("idCine", id);
            RestGenero sGalida = target.request(MediaType.APPLICATION_JSON).get(RestGenero.class);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestGenero crearRegistro() {
        if (generoEntity != null && generoEntity.getNombre() != null )  {
            try {
                RestGenero salida = cliente.target(UrlResource)
                        .path("crear")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(generoEntity, MediaType.APPLICATION_JSON), RestGenero.class);
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

    public RestGenero getGeneroEntity() {
        return generoEntity;
    }

    public void setGeneroEntity(RestGenero generoEntity) {
        this.generoEntity = generoEntity;
    }

    public List<RestGenero> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<RestGenero> listaGenero) {
        this.listaGenero = listaGenero;
    }

}
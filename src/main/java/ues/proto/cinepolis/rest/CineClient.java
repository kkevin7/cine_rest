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
import ues.proto.cinepolis.definiciones.RestCine;

@Named(value = "cineclient")
@ViewScoped
public class CineClient extends GenericURL implements Serializable {

    private final static String UrlResource = BASE_URI + "cine/";
    private Client cliente;
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    private RestCine cineEntity = new RestCine();
    private List<RestCine> listaCine = new ArrayList<RestCine>();
    private List<RestCine> listaLocalidad = new ArrayList<RestCine>();
    
    private String localidad;
    private int idCinelocal;

    public CineClient() {
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
        this.cineEntity = new RestCine();
        //findAll();
        llenarTabla();
    }
    
    public void llenarLocal(){
        try {
            cineEntity = findById(cineEntity.getId());
            localidad = cineEntity.getLocalidad();
            idCinelocal = cineEntity.getId();
            
            setCineEntity(cineEntity);
            System.err.println("Esteeeeee el idCine-----> "+cineEntity.getId());
            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    /*** Llenar la una lista de la localidad del cine ***/
    
    public void llenarLocalidad(Integer id){
        try {

            listaLocalidad = cliente.target(UrlResource)
                    .queryParam("id", id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestCine>>(){});
            localidad = listaCine.get(0).getLocalidad();

        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    
    /*** Llenar una una lista con los cine ****/
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaCine = cliente.target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestCine>>(){});
        } catch (Exception e) {
            System.err.println("No se puede llenar la tabla revisa ---------------------*****-------");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    /**
     * Este metodo sirve para llenar la lista que se ocupara en la vista.
     */
    @Deprecated
    public List<RestCine> findAll() {
        List<RestCine> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestCine>>() {
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

    public List<RestCine> findRange(int first, int pageSize) {
        List<RestCine> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestCine>>() {
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
    
    public RestCine findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
            RestCine salida = target.request(MediaType.APPLICATION_JSON).get(RestCine.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestCine crearRegistro() {
        if (cineEntity != null && cineEntity.getNombre() != null )  {
            try {
                RestCine salida = cliente.target(UrlResource)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(cineEntity, MediaType.APPLICATION_JSON), RestCine.class);
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
    
    /********* Setter and Getter **********/

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public RestCine getCineEntity() {
        return cineEntity;
    }

    public void setCineEntity(RestCine cineEntity) {
        this.cineEntity = cineEntity;
    }

    public List<RestCine> getListaCine() {
        return listaCine;
    }

    public void setListaCine(List<RestCine> listaCine) {
        this.listaCine = listaCine;
    }

    public List<RestCine> getListaLocalidad() {
        return listaLocalidad;
    }

    public void setListaLocalidad(List<RestCine> listaLocalidad) {
        this.listaLocalidad = listaLocalidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getIdCinelocal() {
        return idCinelocal;
    }

    public void setIdCinelocal(int idCinelocal) {
        this.idCinelocal = idCinelocal;
    }

}

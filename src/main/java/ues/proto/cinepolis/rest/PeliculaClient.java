package ues.proto.cinepolis.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import ues.proto.cinepolis.definiciones.RestCine;
import ues.proto.cinepolis.definiciones.RestPelicula;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;

@Named(value = "peliculaclient")
@ViewScoped
public class PeliculaClient extends GenericURL implements Serializable {
    
    private final static String UrlResource = BASE_URI + "pelicula/";
    private Client cliente;

    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    private RestPelicula peliculaEntity;
    private RestCine cineEntity = new RestCine();
    private List<RestPelicula> listaPelicula = new ArrayList<RestPelicula>();
    private CineClient cineValor = new CineClient();

    /*--- Variables ---*/
    private List<RestPelicula> listaUnica = new ArrayList<RestPelicula>();
    private int idPeliculaLocal;
    private String nombreLocal;
    private String duracionLocal;
    private String generoLocal;

    /**
     * --- Variables url----
     */
    private int idCine;
    private int idPelicula;
    private int algo;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map params = facesContext.getExternalContext().getRequestParameterMap();
    Integer paramIdPelicula = new Integer((String) params.get("idPelicula"));
    Integer paramIdCine = new Integer((String) params.get("idCine"));
    
    public PeliculaClient() {
        try {
            cliente = ClientBuilder.newClient();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    @PostConstruct
    public void init() {
        //this.peliculaEntity = new RestPelicula();
        //findAll();
        llenarTabla();
        System.err.println("ID PELICULA ----->" + idPelicula + "param id pelicula " + paramIdPelicula);
        if (paramIdPelicula > 0) {
            llenarListaUnica(paramIdPelicula);
        }
        if (paramIdCine > 0) {
            llenarCine(paramIdCine);
        }
    }
    
    public RestCine findByIdPelicula(Integer id){
        try {
            WebTarget target = cliente.target(BASE_URI + "cine/").path("{id}").resolveTemplate("id", id);
            RestCine salida = target.request(MediaType.APPLICATION_JSON).get(RestCine.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
    
    public void llenarPelicula(int id) {
        try {
            peliculaEntity = findById(id);            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public void llenarCine(int id) {
        try {
            cineEntity = findByIdPelicula(id);            
            setCineEntity(cineEntity);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public void llenarListaUnica(int id) {
        try {
            peliculaEntity = findById(id);
            idPeliculaLocal = peliculaEntity.getId();
            nombreLocal = peliculaEntity.getNombre();
            duracionLocal = peliculaEntity.getDuracion().toString();
            setPeliculaEntity(peliculaEntity);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
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
     * Este metodo sirve para llenar la lista unica.
     */
    /*
    @Deprecated
    public List<RestPelicula> findbyUnicaLista(Integer id) {
        System.out.println("/*** este es el ID del pelicula " + id);
        List<RestPelicula> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
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
     */
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
    
    public RestPelicula findById(Integer id) {
        try {
            WebTarget target = cliente.target(UrlResource).path("{id}").resolveTemplate("id", id);
            RestPelicula salida = target.request(MediaType.APPLICATION_JSON).get(RestPelicula.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
    
    public RestPelicula crearRegistro() {
        if (peliculaEntity != null && peliculaEntity.getNombre() != null) {
            try {
                RestPelicula salida = cliente.target(UrlResource)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(peliculaEntity, MediaType.APPLICATION_JSON), RestPelicula.class);
                if (salida != null && salida.getId() != null) {
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
    
    public List<RestPelicula> getListaUnica() {
        return listaUnica;
    }
    
    public void setListaUnica(List<RestPelicula> listaUnica) {
        this.listaUnica = listaUnica;
    }
    
    public int getIdPeliculaLocal() {
        return idPeliculaLocal;
    }
    
    public void setIdPeliculaLocal(int idPeliculaLocal) {
        this.idPeliculaLocal = idPeliculaLocal;
    }
    
    public String getNombreLocal() {
        return nombreLocal;
    }
    
    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }
    
    public String getDuracionLocal() {
        return duracionLocal;
    }
    
    public void setDuracionLocal(String duracionLocal) {
        this.duracionLocal = duracionLocal;
    }
    
    public String getGeneroLocal() {
        return generoLocal;
    }
    
    public void setGeneroLocal(String generoLocal) {
        this.generoLocal = generoLocal;
    }
    
    public int getIdCine() {
        if (idCine > 0) {
            cineEntity = cineValor.findById(idCine);
            setCineEntity(cineEntity);
            
        }
        return idCine;
    }
    
    public void setIdCine(int idCine) {
        this.idCine = idCine;
    }
    
    public RestCine getCineEntity() {
        return cineEntity;
    }
    
    public void setCineEntity(RestCine cineEntity) {
        this.cineEntity = cineEntity;
    }
    
    public CineClient getCineValor() {
        return cineValor;
    }
    
    public void setCineValor(CineClient cineValor) {
        this.cineValor = cineValor;
    }
    
    public int getIdPelicula() {
        //System.err.println("ID PELICULA ----->"+idPelicula);
        return idPelicula;
    }
    
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    public int getAlgo() {
        
        return algo;
    }
    
    public void setAlgo(int algo) {
        this.algo = algo;
    }
    
    public FacesContext getFacesContext() {
        return facesContext;
    }
    
    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
    
    public Map getParams() {
        return params;
    }
    
    public void setParams(Map params) {
        this.params = params;
    }
    
    public Integer getParamIdPelicula() {
        return paramIdPelicula;
    }
    
    public void setParamIdPelicula(Integer paramIdPelicula) {
        this.paramIdPelicula = paramIdPelicula;
    }
    
    public Integer getParamIdCine() {
        return paramIdCine;
    }
    
    public void setParamIdCine(Integer paramIdCine) {
        this.paramIdCine = paramIdCine;
    }
    
}

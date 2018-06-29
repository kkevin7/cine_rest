package ues.proto.cinepolis.rest;

import java.io.IOException;
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
import ues.proto.cinepolis.definiciones.RestProyeccion;
import ues.proto.cinepolis.definiciones.RestReserva;
import ues.proto.cinepolis.definiciones.RestReservaGeneric;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

@Named(value = "reservaclient")
@ViewScoped
public class ReservaClient extends GenericURL implements Serializable {
    
    private final static String UrlResource = BASE_URI + "reserva/";
    private Client cliente;
    private RestReserva reservaEntity = new RestReserva();
    private RestReservaGeneric reservaEntityGenric = new RestReservaGeneric();
    private RestProyeccion proyeccionEntity = new RestProyeccion();
    private RestPelicula peliculaEntity = new RestPelicula();
    FrmMenssages mensaje = new FrmMenssages();
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestReserva> listaReserva;
    List<RestProyeccion> listaProyeccion;
    
    Integer idProyeccionLocal;

    /*--- variables ----*/
    private int idPelicula;
    private int idPeliculaSelect;
    private String peliculaSelect;
    
    public ReservaClient() {
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
        this.reservaEntity = new RestReserva();
        //findAll();
        llenarTabla();
        llenarReserva();
        //System.err.println("ID PELICULA reserva----->" + idPelicula);
    }
    
    public void obtenerIdPelicula(int valor) {
        try {
            setProyeccionEntity(findByIdProyeccion(valor));
            idPeliculaSelect = proyeccionEntity.getIdProyeccion();
            setPeliculaEntity(findByIdPelicula(idPeliculaSelect));
            peliculaSelect = peliculaEntity.getNombre();
            //System.err.println("id proyeccion id------->" + proyeccionEntity.getIdProyeccion());
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public RestProyeccion findByIdProyeccion(Integer id) {
        try {
            WebTarget target = cliente.target(BASE_URI + "proyeccion/").path("{idProyeccion}").resolveTemplate("idProyeccion", id);
            RestProyeccion salida = target.request(MediaType.APPLICATION_JSON).get(RestProyeccion.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
    
    public RestPelicula findByIdPelicula(Integer id) {
        try {
            WebTarget target = cliente.target(BASE_URI + "pelicula/").path("{id}").resolveTemplate("id", id);
            RestPelicula salida = target.request(MediaType.APPLICATION_JSON).get(RestPelicula.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
        
    
    public void obtenerReservaNumtelefono(String valor) {
        try {
            reservaEntity.setNumTelefonoid(valor);
            //System.err.println("id proyeccion neumerotel------->" + reservaEntity.getNumTelefonoid());
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
    }
    
    public void llenarReserva() {
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
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            listaReserva = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestReserva>>() {
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
    public List<RestReserva> findAll() {
        List<RestReserva> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestReserva>>() {
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
    
    public List<RestReserva> findRange(int first, int pageSize) {
        List<RestReserva> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestReserva>>() {
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
    
    public RestReserva findById(Integer id) {
        try {
            WebTarget target = cliente.target(UrlResource).path("{idReserva}").resolveTemplate("idReserva", id);
            RestReserva salida = target.request(MediaType.APPLICATION_JSON).get(RestReserva.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
    
    public void crearRegistro() throws IOException {
        
        System.err.println("id proyeccion------->" + reservaEntity.getNumTelefonoid());
        System.err.println("id proyeccion------->" + idProyeccionLocal);
        reservaEntity.setIdProyeccionid(idProyeccionLocal);
        
        System.err.println("Estado reserva------->" + reservaEntity.getEstado());
        System.err.println("idproyeccion reserva------->" + reservaEntity.getIdProyeccionid());
        System.err.println("numtelefono------->" + reservaEntity.getNumTelefonoid());
        System.err.println("columna------->" + reservaEntity.getColumna());
        System.err.println("fila------->" + reservaEntity.getFila());
        
        try {
            String payload = "{" +
                "\"estado\": \""+reservaEntity.getEstado()+"\", " +
                "\"fila\": "+reservaEntity.getFila()+", " +
                "\"columna\": "+reservaEntity.getFila()+"," +
                "\"idProyeccion\": "+reservaEntity.getIdProyeccionid()+"," +
                "\"numTelefono\": \""+reservaEntity.getNumTelefonoid()+"\"" +
                "}";
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(UrlResource);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
        
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
        
        
        
        /*
        reservaEntityGenric.setEstado("activo");
        reservaEntityGenric.setIdProyeccionid(2);
        reservaEntityGenric.setNumTelefonoid("76566551");
        reservaEntityGenric.setFila(1);
        reservaEntityGenric.setColumna(1);
        
        System.err.println("Estado reserva------->" + reservaEntityGenric.getEstado());
        System.err.println("idproyeccion reserva------->" + reservaEntityGenric.getIdProyeccionid());
        System.err.println("numtelefono------->" + reservaEntityGenric.getNumTelefonoid());
        System.err.println("columna------->" + reservaEntityGenric.getColumna());
        System.err.println("fila------->" + reservaEntityGenric.getFila());
        
        System.out.println("salida------->" + reservaEntityGenric);
        
        
        
        if (reservaEntityGenric != null) {
            if (reservaEntityGenric.getFila() > 0 && reservaEntityGenric.getColumna() > 0) {
                if (reservaEntityGenric.getNumTelefonoid() != null && reservaEntityGenric.getIdProyeccionid() != null) {
                    if (reservaEntityGenric.getEstado() != null) {
                        System.out.println("salida------->" + Entity.entity(reservaEntityGenric, MediaType.APPLICATION_JSON));
                        System.out.println("salida------->" + Entity.entity(reservaEntity, MediaType.APPLICATION_JSON));
//                        try {
                            RestReservaGeneric salida = cliente.target(UrlResource)
                                    .request(MediaType.APPLICATION_JSON)
                                    .post(Entity.entity(reservaEntityGenric, MediaType.APPLICATION_JSON), RestReservaGeneric.class);
                            
                            if (salida != null && salida.getEstado() != null) {
                                mensaje.msgCreadoExito();
                                return salida;
                            }
                            System.out.println("salida------->" + salida);
//                        } catch (Exception e) {
//                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
//                        }
                    } else {
                        mensaje.msgErrorEstado();
                    }
                } else {
                    mensaje.msgErrorCliente();
                }
            } else {
                mensaje.msgFilaColumna();
            }
        } else {
            mensaje.msgErrorEntity();
        }
        return null;*/
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
    
    public RestReserva getReservaEntity() {
        return reservaEntity;
    }
    
    public void setReservaEntity(RestReserva reservaEntity) {
        this.reservaEntity = reservaEntity;
    }
    
    public List<RestReserva> getListaReserva() {
        return listaReserva;
    }
    
    public void setListaReserva(List<RestReserva> listaReserva) {
        this.listaReserva = listaReserva;
    }
    
    public int getIdPelicula() {
        return idPelicula;
    }
    
    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    public FrmMenssages getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(FrmMenssages mensaje) {
        this.mensaje = mensaje;
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
    
    public Integer getIdProyeccionLocal() {
        return idProyeccionLocal;
    }
    
    public void setIdProyeccionLocal(Integer idProyeccionLocal) {
        this.idProyeccionLocal = idProyeccionLocal;
    }

    public int getIdPeliculaSelect() {
        return idPeliculaSelect;
    }

    public void setIdPeliculaSelect(int idPeliculaSelect) {
        this.idPeliculaSelect = idPeliculaSelect;
    }

    public String getPeliculaSelect() {
        return peliculaSelect;
    }

    public void setPeliculaSelect(String peliculaSelect) {
        this.peliculaSelect = peliculaSelect;
    }

    public RestPelicula getPeliculaEntity() {
        return peliculaEntity;
    }

    public void setPeliculaEntity(RestPelicula peliculaEntity) {
        this.peliculaEntity = peliculaEntity;
    }

    public RestReservaGeneric getReservaEntityGenric() {
        return reservaEntityGenric;
    }

    public void setReservaEntityGenric(RestReservaGeneric reservaEntityGenric) {
        this.reservaEntityGenric = reservaEntityGenric;
    }
    
}

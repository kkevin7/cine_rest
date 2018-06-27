
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
import ues.proto.cinepolis.definiciones.RestCliente;
import static ues.proto.cinepolis.rest.GenericURL.BASE_URI;

@Named(value = "clienteclient")
@ViewScoped
public class ClienteClient extends GenericURL implements Serializable {
    
    private final static String UrlResource = BASE_URI + "cliente/";
    private Client cliente;
    private RestCliente clienteEntity;
    FrmMenssages mensaje = new FrmMenssages();
    
    /*--- LLENAR UNA TABLA A UTILIZAR --*/
    List<RestCliente> lista;

    public ClienteClient() {
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
        this.clienteEntity = new RestCliente();
        //findAll();
        llenarTabla();
    }
    
    public void llenarTabla() {
        //reset(); DEBERIA POR VOLVER A LLENAR
        try {
            lista = cliente
                    .target(UrlResource)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<RestCliente>>() {
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
    public List<RestCliente> findAll() {
        List<RestCliente> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestCliente>>() {
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

    public List<RestCliente> findRange(int first, int pageSize) {
        List<RestCliente> salida = null;
        try {
            WebTarget target = cliente.target(UrlResource).queryParam("first", first)
                    .queryParam("pagezise", pageSize);
            salida = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<RestCliente>>() {
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
    
    public RestCliente findById(Integer id){
        try {
            WebTarget target = cliente.target(UrlResource).path("{numTelefono}").resolveTemplate("numTelefono", id);
            RestCliente salida = target.request(MediaType.APPLICATION_JSON).get(RestCliente.class);
            return salida;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    public RestCliente crearRegistro() {
        System.out.println("nombre cliente: "+clienteEntity.getNombre());
        System.out.println("apellido cliente: "+clienteEntity.getApellido());
        System.out.println("numero cliente: "+clienteEntity.getNumTelefono());
        System.out.println("correo cliente: "+clienteEntity.getCorreo());
        System.out.println("fecha cliente: "+clienteEntity.getFechaNacimiento());
        if (clienteEntity != null && clienteEntity.getNombre() != null )  {
            try {
                RestCliente salida = cliente.target(UrlResource)
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(clienteEntity, MediaType.APPLICATION_JSON), RestCliente.class);
                if (salida != null && salida.getNumTelefono() != null ) {
                    mensaje.msgCreadoExito();
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

    public RestCliente getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(RestCliente clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public List<RestCliente> getLista() {
        return lista;
    }

    public void setLista(List<RestCliente> lista) {
        this.lista = lista;
    }
    
    
    
    
}

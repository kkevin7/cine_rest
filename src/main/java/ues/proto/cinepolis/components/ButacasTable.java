package ues.proto.cinepolis.components;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ues.proto.cinepolis.rest.GenericURL;

@Named(value = "butacasTable")
@ViewScoped
public class ButacasTable extends GenericURL implements Serializable{

    public ButacasTable() {
    }
    
     private List<Butacas> cars;
     
    @ManagedProperty("#{carService}")
    private ButacasService service;
 
    @PostConstruct
    public void init() {
        cars = service.createCars(12);
    }
     
    public List<Butacas> getCars() {
        return cars;
    }
 
    public void setService(ButacasService service) {
        this.service = service;
    }
    
}

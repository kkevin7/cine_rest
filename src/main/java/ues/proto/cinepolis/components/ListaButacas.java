/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.proto.cinepolis.components;

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author kevinmanjaro
 */
@Named(value = "listaButacas")
@Dependent
public class ListaButacas implements Serializable{

    
    private ArrayList<String> al= new ArrayList<String>();
    
    public ListaButacas() {
        
        //add method for String ArrayList
        al.add("Ram");
        al.add("Shyam");
        al.add("CPS");
        al.add("John");
        al.add("Steve");
        System.out.println("Elements of ArrayList of String Type: "+al);
    }

    public ArrayList<String> getAl() {
        return al;
    }

    public void setAl(ArrayList<String> al) {
        this.al = al;
    }
    
    
    
    
}

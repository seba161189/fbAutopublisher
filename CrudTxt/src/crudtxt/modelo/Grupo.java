/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudtxt.modelo;

/**
 *
 * @author seba1
 */
public class Grupo {
    private      String descripcion;
    private      int    cantidad; 
    
  

    public Grupo( String descripcion, int cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
    


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Dell
 */
public class Precio {
    private int idPrecio;
    private String tipo;
    private int precio;

    public Precio(int idPrecio, String tipo, int precio) {
        this.idPrecio = idPrecio;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Precio(String tipo, int precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public Precio() {
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Precio{" + "idPrecio=" + idPrecio + ", tipo=" + tipo + ", precio=" + precio + '}';
    }
    
    
    
}

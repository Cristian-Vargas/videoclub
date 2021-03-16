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
    private int precioEstreno;
    private int precioComun;

    public Precio() {
    }

    public Precio(int precioEstreno, int precioComun) {
        this.precioEstreno = precioEstreno;
        this.precioComun = precioComun;
    }
    
    public Precio(int idPrecio, int precioEstreno, int precioComun) {
        this.idPrecio = idPrecio;
        this.precioEstreno = precioEstreno;
        this.precioComun = precioComun;
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public int getPrecioEstreno() {
        return precioEstreno;
    }

    public void setPrecioEstreno(int precioEstreno) {
        this.precioEstreno = precioEstreno;
    }

    public int getPrecioComun() {
        return precioComun;
    }

    public void setPrecioComun(int precioComun) {
        this.precioComun = precioComun;
    }

    @Override
    public String toString() {
        return "Precio{" + "idPrecio=" + idPrecio + ", precioEstreno=" + precioEstreno + ", precioComun=" + precioComun + '}';
    }
}

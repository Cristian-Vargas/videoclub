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
public class DetalleAlquiler {
    private int idDetalleAlquiler;
    private Alquiler alquiler;
    private PrecioXPelicula precioXPelicula;
    private int subtotal;

    public DetalleAlquiler(int idDetalleAlquiler, Alquiler alquiler, PrecioXPelicula precioXPelicula, int subtotal) {
        this.idDetalleAlquiler = idDetalleAlquiler;
        this.alquiler = alquiler;
        this.precioXPelicula = precioXPelicula;
        this.subtotal = subtotal;
    }

    public DetalleAlquiler(Alquiler alquiler, PrecioXPelicula precioXPelicula, int subtotal) {
        this.alquiler = alquiler;
        this.precioXPelicula = precioXPelicula;
        this.subtotal = subtotal;
    }

    public DetalleAlquiler() {
    }

    public int getIdDetalleAlquiler() {
        return idDetalleAlquiler;
    }

    public void setIdDetalleAlquiler(int idDetalleAlquiler) {
        this.idDetalleAlquiler = idDetalleAlquiler;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public PrecioXPelicula getPrecioXPelicula() {
        return precioXPelicula;
    }

    public void setPrecioXPelicula(PrecioXPelicula precioXPelicula) {
        this.precioXPelicula = precioXPelicula;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleAlquiler{" + "idDetalleAlquiler=" + idDetalleAlquiler + ", alquiler=" + alquiler + ", precioXPelicula=" + precioXPelicula + ", subtotal=" + subtotal + '}';
    }
    
    
}

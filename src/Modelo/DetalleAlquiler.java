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
    private Pelicula pelicula;
    private Precio precio;
    private String estado;

    public DetalleAlquiler(int idDetalleAlquiler, Alquiler alquiler, Pelicula pelicula, Precio precio, String estado) {
        this.idDetalleAlquiler = idDetalleAlquiler;
        this.alquiler = alquiler;
        this.pelicula = pelicula;
        this.precio = precio;
        this.estado = estado;
    }

    public DetalleAlquiler(Alquiler alquiler, Pelicula pelicula, Precio precio, String estado) {
        this.alquiler = alquiler;
        this.pelicula = pelicula;
        this.precio = precio;
        this.estado = estado;
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

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Precio getPrecio() {
        return precio;
    }

    public void setPrecio(Precio precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DetalleAlquiler{" + "idDetalleAlquiler=" + idDetalleAlquiler + ", alquiler=" + alquiler + ", pelicula=" + pelicula + ", precio=" + precio + ", estado=" + estado + '}';
    }

    
    
}

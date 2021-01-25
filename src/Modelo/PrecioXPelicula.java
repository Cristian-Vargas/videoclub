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
public class PrecioXPelicula {
    private int idPrecioXPelicula;
    private Pelicula pelicula;
    private Precio precio;

    public PrecioXPelicula(int idPrecioXPelicula, Pelicula pelicula, Precio precio) {
        this.idPrecioXPelicula = idPrecioXPelicula;
        this.pelicula = pelicula;
        this.precio = precio;
    }

    public PrecioXPelicula(Pelicula pelicula, Precio precio) {
        this.pelicula = pelicula;
        this.precio = precio;
    }

    public PrecioXPelicula() {
    }

    public int getIdPrecioXPelicula() {
        return idPrecioXPelicula;
    }

    public void setIdPrecioXPelicula(int idPrecioXPelicula) {
        this.idPrecioXPelicula = idPrecioXPelicula;
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

    @Override
    public String toString() {
        return "PrecioXPelicula{" + "idPrecioXPelicula=" + idPrecioXPelicula + ", pelicula=" + pelicula + ", precio=" + precio + '}';
    }
    
    
}

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
public class Pelicula {
    private int idPelicula;
    private String titulo;
    private String director;
    private int añoEstreno;
    private int cantidad;
    private Genero genero;
    private String tipo;
    

    

    public Pelicula(int idPelicula, String titulo, String director, int añoEstreno, int cantidad, Genero genero, String tipo) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.director = director;
        this.añoEstreno = añoEstreno;
        this.cantidad = cantidad;
        this.genero = genero;
        this.tipo = tipo;
        
    }

    public Pelicula(String titulo, String director, int añoEstreno, int cantidad, Genero genero, String tipo) {
        this.titulo = titulo;
        this.director = director;
        this.añoEstreno = añoEstreno;
        this.cantidad = cantidad;
        this.genero = genero;
        this.tipo = tipo;
        
    }

    public Pelicula() {
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAñoEstreno() {
        return añoEstreno;
    }

    public void setAñoEstreno(int añoEstreno) {
        this.añoEstreno = añoEstreno;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "idPelicula=" + idPelicula + ", titulo=" + titulo + ", director=" + director + ", a\u00f1oEstreno=" + añoEstreno + ", cantidad=" + cantidad + ", genero=" + genero + ", tipo=" + tipo + '}';
    }

   
    


   
    
}

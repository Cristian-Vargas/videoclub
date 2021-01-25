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
public class Genero {
    private int idGenero;
    private String genero;

    public Genero(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    public Genero(String genero) {
        this.genero = genero;
    }

    public Genero() {
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Genero{" + "idGenero=" + idGenero + ", genero=" + genero + '}';
    }

    
}

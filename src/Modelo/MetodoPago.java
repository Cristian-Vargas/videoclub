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
public class MetodoPago {
    private int idMetodoPago;
    private String metodoPago;

    public MetodoPago(int idMetodoPago, String metodoPago) {
        this.idMetodoPago = idMetodoPago;
        this.metodoPago = metodoPago;
    }

    public MetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    

    public MetodoPago() {
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return metodoPago;
    }
    
    
}

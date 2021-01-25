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
public class Alquiler {
    private int idAlquiler;
    private Socio socio;
    private String fecha;
    private int total;
    private MetodoPago metodoPago;
    private int montoPagado;
    private int vuelto;

    public Alquiler(int idAlquiler, Socio socio, String fecha, int total, MetodoPago metodoPago, int montoPagado, int vuelto) {
        this.idAlquiler = idAlquiler;
        this.socio = socio;
        this.fecha = fecha;
        this.total = total;
        this.metodoPago = metodoPago;
        this.montoPagado = montoPagado;
        this.vuelto = vuelto;
    }

    public Alquiler(Socio socio, String fecha, int total, MetodoPago metodoPago, int montoPagado, int vuelto) {
        this.socio = socio;
        this.fecha = fecha;
        this.total = total;
        this.metodoPago = metodoPago;
        this.montoPagado = montoPagado;
        this.vuelto = vuelto;
    }

    public Alquiler() {
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(int montoPagado) {
        this.montoPagado = montoPagado;
    }

    public int getVuelto() {
        return vuelto;
    }

    public void setVuelto(int vuelto) {
        this.vuelto = vuelto;
    }

    @Override
    public String toString() {
        return "Alquiler{" + "idAlquiler=" + idAlquiler + ", socio=" + socio + ", fecha=" + fecha + ", total=" + total + ", metodoPago=" + metodoPago + ", montoPagado=" + montoPagado + ", vuelto=" + vuelto + '}';
    }
    
    
    
}

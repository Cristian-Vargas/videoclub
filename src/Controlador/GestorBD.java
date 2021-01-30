/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.*;
import Modelo.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class GestorBD {

    private Connection conexion;
    private String consultaSQL;

    //Metodo: abrir conexion a la base de datos
    public void conectarBD() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlserver://DELL-PC:1433;databaseName=videoclub", "sa", "cristian123");
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo: cerrar conexion a la base de datos
    public void desconectarBD() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //SOCIOS-------------------------------------------------------------
    //METODO: listar socios
    public ArrayList<Socio> listaSocios() {
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        consultaSQL = "select * from Socios";

        try {
            conectarBD();
            Statement comando = conexion.createStatement();
            ResultSet lector = comando.executeQuery(consultaSQL);
            while (lector.next()) {
                int idSocio = lector.getInt("idSocio");
                String nombre = lector.getString("nombre");
                String apellido = lector.getString("apellido");
                int dni = lector.getInt("dni");
                String telefono = lector.getString("telefono");
                String email = lector.getString("email");
                Socio socio = new Socio(idSocio, nombre, apellido, dni, telefono, email);
                listaSocios.add(socio);

            }
            lector.close();

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectarBD();

        }
        return listaSocios;
    }

    //METODO: Insertar socio.
    public void insertarSocio(Socio socio) {
        consultaSQL = "insert into Socios (nombre, apellido, dni, telefono, email) values (?,?,?,?,?)";
        try {
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(consultaSQL);
            comando.setString(1, socio.getNombre());
            comando.setString(2, socio.getApellido());
            comando.setInt(3, socio.getDni());
            comando.setString(4, socio.getTelefono());
            comando.setString(5, socio.getEmail());
            comando.execute();

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectarBD();
        }
    }

    //METODO: Modificar Socio.
    public void modificarSocio(Socio socio) {
        consultaSQL = "update socios set nombre = (?), apellido= (?), dni= (?), telefono= (?),email= (?) where idSocio= (?)";

        try {
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(consultaSQL);
            comando.setString(1, socio.getNombre());
            comando.setString(2, socio.getApellido());
            comando.setInt(3, socio.getDni());
            comando.setString(4, socio.getTelefono());
            comando.setString(5, socio.getEmail());
            comando.setInt(6, socio.getIdSocio());
            comando.execute();

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectarBD();
        }
    }

    //METODO: Eliminar un socio
    public void eliminarSocio(int id) {
        consultaSQL = "Delete from Socios where idSocio = " + id;

        try {
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(consultaSQL);
            comando.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectarBD();
        }

    }

    //METODO: Buscar Socio por dni o numero de socio
    public ArrayList<Socio> buscarSocio(int indice, int dato) {
        ArrayList<Socio> listaSocio = new ArrayList<Socio>();

        if (indice == 0) {
            consultaSQL = "Select * from Socios where idSocio = " + dato;
        } else {
            consultaSQL = "Select * from Socios where dni = " + dato;
        }

        try {
            conectarBD();
            Statement comando = conexion.createStatement();
            ResultSet lector = comando.executeQuery(consultaSQL);
            while (lector.next()) {
                int idSocio = lector.getInt("idSocio");
                String nombre = lector.getString("nombre");
                String apellido = lector.getString("apellido");
                int dni = lector.getInt("dni");
                String telefono = lector.getString("telefono");
                String email = lector.getString("email");
                Socio socio = new Socio(idSocio, nombre, apellido, dni, telefono, email);
                listaSocio.add(socio);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectarBD();
        }
        return listaSocio;

    }

}

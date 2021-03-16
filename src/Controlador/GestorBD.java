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
    private String sentenciaSQL;

    //Metodo: abrir conexion a la base de datos
    public void conectarBD() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-A5CSLIK:1433;databaseName=videoclub;integratedSecurity=false;", "sa", "cristian123");
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
    //--------------------------------------------------------------------------
    //SOCIOS:
    // 1 - METODO: listar socios
    public ArrayList<Socio> listaSocios() {
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        sentenciaSQL = "select * from Socios";

        try {
            conectarBD();
            Statement comando = conexion.createStatement();
            ResultSet lector = comando.executeQuery(sentenciaSQL);
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

    // 2 - METODO: Insertar socio.
    public void insertarSocio(Socio socio) {
        sentenciaSQL = "insert into Socios (nombre, apellido, dni, telefono, email) "
                        + "values (?,?,?,?,?)";
        try {
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
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

    // 3 - METODO: Modificar Socio.
    public void modificarSocio(Socio socio) {
        sentenciaSQL = "update socios "
                     + "set nombre = (?), apellido= (?), dni= (?), telefono= (?),email= (?) "
                     + "where idSocio= (?)";

        try {
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
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

    // 4 - METODO: Eliminar un socio
    public void eliminarSocio(int id) {
        sentenciaSQL = "Delete from Socios where idSocio = " + id;

        try {
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
            comando.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            desconectarBD();
        }

    }

    // 5 - METODO: Buscar Socio por dni o numero de socio
    public ArrayList<Socio> buscarSocio(int indice, int dato) {
        ArrayList<Socio> listaSocio = new ArrayList<Socio>();

        if (indice == 0) {
            sentenciaSQL = "Select * from Socios where idSocio = " + dato;
        } else {
            sentenciaSQL = "Select * from Socios where dni = " + dato;
        }

        try {
            conectarBD();
            Statement comando = conexion.createStatement();
            ResultSet lector = comando.executeQuery(sentenciaSQL);
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
    
    //-----------------------------------------------------------------------------
    // GENEROS:
    // 1 - Metodo: Listar generos
    
    public ArrayList<Genero> listaGeneros()
    { 
         ArrayList<Genero> listaGeneros = new ArrayList<Genero>();
         sentenciaSQL = "select * from Generos"; 
        try{
        conectarBD();
        Statement comando = conexion.createStatement();
        ResultSet lector = comando.executeQuery(sentenciaSQL);
        while(lector.next())
        {
            int idGenero = lector.getInt("idGenero");
            String genero = lector.getString("genero");
            
            Genero g = new Genero(idGenero, genero);
            listaGeneros.add(g);
        
        }
       } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            desconectarBD();
        }
       
       return listaGeneros;
    }
    //-----------------------------------------------------------------------------
    //METODOS DE PAGOS
    public ArrayList<MetodoPago> listaMetPago()
    { 
         ArrayList<MetodoPago> listaMetPago = new ArrayList<MetodoPago>();
         sentenciaSQL = "select * from MetodosPagos"; 
        try{
        conectarBD();
        Statement comando = conexion.createStatement();
        ResultSet lector = comando.executeQuery(sentenciaSQL);
        while(lector.next())
        {
            int idMetPago = lector.getInt("idMetodoPago");
            String metPago = lector.getString("metodoPago");
            
            MetodoPago mp = new MetodoPago(idMetPago, metPago);
            listaMetPago.add(mp);
        
        }
       } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            desconectarBD();
        }
       
       return listaMetPago;
    }
    //---------------------------------------------------------------------------
    //PELICULAS
    // 1 - METODO: Listar peliculas
    public ArrayList<Pelicula> listaPeliculas()
    {   
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
        sentenciaSQL= "select p.*, g.genero\n" +
                      "from Peliculas p join Generos g\n" +
                      "on p.idGenero = g.idGenero";
        try{
        conectarBD();
        Statement comando = conexion.createStatement();
        ResultSet lector = comando.executeQuery(sentenciaSQL);
        while(lector.next())
        {
            int idPelicula = lector.getInt("idPelicula");
            String titulo = lector.getString("titulo");
            String director = lector.getString("director");
            int añoEstreno= lector.getInt("añoEstreno");
            int cantidad = lector.getInt("cantidadEjemplares");
            String tipo = lector.getString("tipo");
            
            int idGenero = lector.getInt("idGenero");
            String genero = lector.getString("genero");
            
            Genero g = new Genero(idGenero,genero);
            Pelicula p = new Pelicula(idPelicula,titulo,director,añoEstreno,cantidad,g,tipo);
            
            listaPeliculas.add(p);  
        }
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            desconectarBD();
        }
        
    
        return listaPeliculas;
    }



    // 2 - METODO: Insertar pelicula
    
    public void insertarPelicula(Pelicula p)
    {   
        sentenciaSQL = "insert into Peliculas (titulo, director, añoEstreno, cantidadEjemplares, idGenero, tipo) "
                        + "values (?,?,?,?,?,?)";
        try{
            conectarBD();
            PreparedStatement comando = conexion.prepareCall(sentenciaSQL);
            comando.setString(1, p.getTitulo());
            comando.setString(2, p.getDirector());
            comando.setInt(3, p.getAñoEstreno());
            comando.setInt(4, p.getCantidad());
            comando.setInt(5, p.getGenero().getIdGenero());
            comando.setString(6, p.getTipo());
            
            comando.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            desconectarBD();
        }
    
    }
    
    // 3 - METODO: Modificar pelicula
    public void modificarPelicula(Pelicula p)
    {
        sentenciaSQL = "Update peliculas "
                     + "set titulo =(?), director=(?), añoEstreno=(?), cantidadEjemplares=(?), idGenero=(?), tipo= (?) "
                     + "where idPelicula= (?)";
        
        try{
        conectarBD();
        PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        comando.setString(1, p.getTitulo());
        comando.setString(2, p.getDirector());
        comando.setInt(3, p.getAñoEstreno());
        comando.setInt(4, p.getCantidad());
        comando.setInt(5, p.getGenero().getIdGenero());
        comando.setString(6, p.getTipo());
        comando.setInt(7, p.getIdPelicula());
        
        comando.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally{
        desconectarBD();
        }
    }
    
    // 4 - METODO: Eliminar pelicula
    public void elminarPelicula(int idPelicula)
    {
        sentenciaSQL = "delete from Peliculas\n" +
                        "where idPelicula = " + idPelicula;
        
        try{
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
            comando.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }
        finally {
        desconectarBD();
        }
    }
    
    //METODO: Buscar pelicula por id o titulo
    public ArrayList<Pelicula> buscarPelicula(int idP, String tit)
    {   
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
        sentenciaSQL= "select p.*, g.genero\n" +
                       "from Peliculas p join Generos g\n" +
                        "on p.idGenero =g.idGenero\n" +
                        "where titulo like'%"+tit+"%'\n" +
                        "or idPelicula =" + idP;
        try{
        conectarBD();
        Statement comando = conexion.createStatement();
        ResultSet lector = comando.executeQuery(sentenciaSQL);
        while(lector.next())
        {
            int idPelicula = lector.getInt("idPelicula");
            String titulo = lector.getString("titulo");
            String director = lector.getString("director");
            int añoEstreno= lector.getInt("añoEstreno");
            int cantidad = lector.getInt("cantidadEjemplares");
            String tipo = lector.getString("tipo");
            
            int idGenero = lector.getInt("idGenero");
            String genero = lector.getString("genero");
            
            Genero g = new Genero(idGenero,genero);
            Pelicula p = new Pelicula(idPelicula,titulo,director,añoEstreno,cantidad,g,tipo);
            
            listaPeliculas.add(p);  
        }
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            desconectarBD();
        }
        
    
        return listaPeliculas;
    }
    
    
   //----------------------------------------------------------------------------------
    //PRECIOS
    // 1 - METODO: Listar ultimo precio ingresado
    
    public Precio consultarPrecio()
    {
       sentenciaSQL = "select top 1 * from Precios order by idPrecios desc";
       Precio precio = new Precio();
       try{
       conectarBD();
       Statement comando = conexion.createStatement();
       ResultSet lector = comando.executeQuery(sentenciaSQL);
       while(lector.next())
       {
           int idPrecio = lector.getInt("idPrecios");
           int precioEstreno = lector.getInt("precioEstreno");
           int precioComun= lector.getInt("precioComun");
           
           precio = new Precio(idPrecio,precioEstreno,precioComun);
       }
       } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally
       {
       desconectarBD();
       }
       
    return precio;
    }

    // 2 - METODO: Insertar nuevos precios
    
    public void insertarPrecios(Precio p)
    {
        sentenciaSQL= "insert into Precios (precioEstreno, precioComun) values(?, ?)";
        
        try{
            conectarBD();
            PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
            comando.setInt(1, p.getPrecioEstreno());
            comando.setInt(2, p.getPrecioComun());
            comando.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            desconectarBD();
        } 
    }
//--------------------------------------------------------------------
//ALQUILERES
//metodo : lista de peliculas alquiladas por socio
    public ArrayList<DetalleAlquiler> listaAlqPorSoc(int indice, int dato)
    {   ArrayList<DetalleAlquiler> listaAlqPorSoc = new ArrayList<DetalleAlquiler>();
        
        if(indice==0)
        {
        sentenciaSQL =  "select s.idSocio, s.nombre,  s.apellido, s.dni, a.fecha, p.titulo, p.tipo, da.estado, da.idDetalleAalquiler\n" +
                        "from Alquiler a join DetalleAlquiler da\n" +
                        "on a.idAlquiler = da.idAlquiler \n" +
                        "join Peliculas p on da.idPelicula = p.idPelicula\n" +
                        "join Socios s on s.idSocio = a.idSocio\n" +
                        "where a.idSocio =" + dato +
                        "order by a.fecha desc, da.idDetalleAalquiler desc";
        }
        else
        {
            sentenciaSQL = "select s.idSocio, s.nombre,  s.apellido, s.dni, a.fecha, p.titulo, p.tipo, da.estado, da.idDetalleAalquiler\n" +
                        "from Alquiler a join DetalleAlquiler da\n" +
                        "on a.idAlquiler = da.idAlquiler \n" +
                        "join Peliculas p on da.idPelicula = p.idPelicula\n" +
                        "join Socios s on s.idSocio = a.idSocio\n" +
                        "where s.dni =" + dato +
                        "order by a.fecha desc";
        }
        
        
        try
        {   conectarBD();
            Statement comando = conexion.createStatement();
            ResultSet lector = comando.executeQuery(sentenciaSQL);
            while(lector.next())
            {  
                int idSocio = lector.getInt("idSocio");
                String nombre = lector.getString("nombre");
                String apellido = lector.getString("apellido");
                int dni= lector.getInt("dni");
                String fecha = lector.getDate("fecha").toString();
                String titulo = lector.getString("titulo");
                String tipo = lector.getString("tipo");
                String estado = lector.getString("estado");
                int idDetalleAlquiler = lector.getInt("idDetalleAalquiler");
                
                Socio s = new Socio();
                s.setIdSocio(idSocio);
                s.setNombre(nombre);
                s.setApellido(apellido);
                s.setDni(dni);
                Alquiler a = new Alquiler();
                a.setFecha(fecha);
                a.setSocio(s);
                Pelicula p = new Pelicula();
                p.setTitulo(titulo);
                p.setTipo(tipo);
                
                DetalleAlquiler da = new DetalleAlquiler();
                da.setEstado(estado);
                da.setPelicula(p);
                da.setAlquiler(a);
                da.setIdDetalleAlquiler(idDetalleAlquiler);
                
                listaAlqPorSoc.add(da);
                
            }
            lector.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            desconectarBD();
        }
        
        return listaAlqPorSoc;
    }


 //metodo: Insertar un alquiler
    public void insertarAlquiler(Alquiler a)
    {
        sentenciaSQL = "insert into Alquiler(idSocio,fecha,total, idMetodoPago) values (?,convert(date,GETDATE()),?,?)";
        try{
        conectarBD();
        PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        comando.setInt(1,a.getSocio().getIdSocio());
        comando.setInt(2, a.getTotal());
        comando.setInt(3, a.getMetodoPago().getIdMetodoPago());
        comando.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{desconectarBD();}
    }
  //metodo mostrar ultimo alquiler
    public Alquiler ultimoAlquiler()
    {
       sentenciaSQL = "select TOP 1 a.* from Alquiler a order by a.idAlquiler desc";
       Alquiler a = new Alquiler();
       try{
       conectarBD();
       Statement comando = conexion.createStatement();
       ResultSet lector = comando.executeQuery(sentenciaSQL);
       while(lector.next())
       {
          int idAlquiler = lector.getInt("idAlquiler");
          int idSocio = lector.getInt("idSocio");
          String fecha = lector.getDate("fecha").toString();
          int total = lector.getInt("total");
          int idMetodoPago =lector.getInt("idMetodoPago");
          
          Socio s = new Socio();
          s.setIdSocio(idSocio);
          
          MetodoPago mp = new MetodoPago();
          mp.setIdMetodoPago(idMetodoPago);
          
          a = new Alquiler(idAlquiler, s,fecha,total,mp);
       }
       lector.close();
       } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{
       desconectarBD();
       }
       return a;
    }
 //METODO:insertar un detalle de alquiler
    public void insertarDetalleAlquiler(DetalleAlquiler da)
    {
        sentenciaSQL = "insert into DetalleAlquiler(idAlquiler, idPelicula,estado,idPrecio) values (?,?,?,?)";
        try{
        conectarBD();
        PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
        comando.setInt(1,da.getAlquiler().getIdAlquiler());
        comando.setInt(2, da.getPelicula().getIdPelicula());
        comando.setString(3, da.getEstado());
        comando.setInt(4, da.getPrecio().getIdPrecio());
        comando.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{desconectarBD();}
    }

    //modificar detalle alquiler
    public void modificarDetalleAlquiler(DetalleAlquiler da)
    {
    sentenciaSQL =  "update DetalleAlquiler \n" +
                    "set estado = 'Devuelto'\n" +
                    "where idDetalleAalquiler = " + da.getIdDetalleAlquiler();
    
      try{
      conectarBD();
      PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);
      comando.execute();
      
      } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
      finally{desconectarBD();}
        }
}

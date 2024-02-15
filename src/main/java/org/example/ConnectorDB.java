package org.example;

import java.sql.*;
import java.util.LinkedList;

public class ConnectorDB {
    private String nombreDB;
    private String host;
    private String puerto;
    private String urlConnection;
    private String usuario;
    private String contrasenia;

    public ConnectorDB(){
        this.nombreDB = "bar";
        this.host = "127.0.0.1";
        this.puerto = "3306";
        this.urlConnection = "jdbc:mysql://" + this.host + ":" + this.puerto + "/" + this.nombreDB;
        this.usuario = "root";
        this.contrasenia = "";
    }

    public Connection devolverConexion(){
        try {
            return DriverManager.getConnection(urlConnection, usuario, contrasenia);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public LinkedList<Producto> listadoProducto(){
        LinkedList<Producto> productos = new LinkedList<>();
        Connection connection = devolverConexion();
        try {
            Statement estado = connection.createStatement();
            ResultSet rs = estado.executeQuery("SELECT  * FROM producto;");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String marca = rs.getString(3);
                String categoria = rs.getString(4);
                double precio = rs.getDouble(5);
                double impuesto = rs.getDouble(6);
                double coste = rs.getDouble(7);
                Producto producto = new Producto(id,nombre,marca,categoria,precio,impuesto,coste);
                productos.add(producto);

            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }
    public void insertarLibro(String titulo, String autor, String genero, boolean prestado){
        Connection connection = devolverConexion();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO libros (titulo, autor, genero, prestado) VALUES (?, ?, ?, ?);");
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setString(3, genero);
            ps.setBoolean(4, prestado);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarLibro(String titulo, boolean prestado){
        Connection connection = devolverConexion();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE libros SET prestado = ? WHERE titulo = ?;");
            ps.setBoolean(1, prestado);
            ps.setString(2, titulo);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarLibro(String titulo){
        Connection connection = devolverConexion();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM libros WHERE titulo = ?;");
            ps.setString(1, titulo);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

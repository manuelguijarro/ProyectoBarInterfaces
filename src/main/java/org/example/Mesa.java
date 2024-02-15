package org.example;

import java.util.LinkedList;
import java.util.List;

public class Mesa {
    private int id;
    private String nombre;
    private boolean estado;
    private double total;
    private List<Producto> productos;


    public Mesa(int id,String nombre, boolean estado, double total) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.total = total;
        this.productos = new LinkedList<Producto>();
    }

    public Mesa() {

    }

    public boolean comprobarProductos(){
        return productos.isEmpty();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}

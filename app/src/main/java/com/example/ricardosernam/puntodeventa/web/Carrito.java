package com.example.ricardosernam.puntodeventa.web;

/**
 * Esta clase representa un gasto individual de cada registro de la base de datos
 */
public class Carrito {
    public String idcarrito;
    public String descripcion;
    public String ubicacion;
    public int disponible;



    public Carrito(String idcarrito, String descripcion,String ubicacion,int disponible) {
        this.idcarrito = idcarrito;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.disponible =disponible;
    }
}

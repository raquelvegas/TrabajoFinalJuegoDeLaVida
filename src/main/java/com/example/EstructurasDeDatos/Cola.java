package com.example.EstructurasDeDatos;


import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.google.gson.annotations.Expose;

public class Cola<TipoDato> {
    @Expose
    private ListaEnlazada<TipoDato> datos;

    public Cola() {
        datos= new ListaEnlazada<>();
    }

    public Cola<TipoDato> copiaCola(){
        ListaEnlazada<TipoDato> datosCopiados = datos.copiaLista();
        Cola<TipoDato> nuevaCola = new Cola<>();
        nuevaCola.setDatos(datosCopiados);
        return nuevaCola;
    }

    public boolean isVacia() {
        return datos.isVacia();
    }

    public void push(TipoDato dato) {
        datos.add(dato);
    }

    public TipoDato pop() {
        TipoDato borrado = datos.getElemento(0).getData();
        datos.del(0);
        return borrado;
    }

    public ListaEnlazada<TipoDato> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<TipoDato> datos) {
        this.datos = datos;
    }
}

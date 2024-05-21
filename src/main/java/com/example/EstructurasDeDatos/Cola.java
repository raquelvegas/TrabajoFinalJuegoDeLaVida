package com.example.EstructurasDeDatos;


import com.example.EstructurasDeDatos.Listas.ListaEnlazada;

public class Cola<TipoDato> {
    private ListaEnlazada<TipoDato> datos;

    public Cola() {
        datos= new ListaEnlazada<>();
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

}

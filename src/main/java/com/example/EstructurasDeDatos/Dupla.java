package com.example.EstructurasDeDatos;

public class Dupla<T,E> {
    private T clave;
    private E dato;

    public Dupla(T clave, E dato) {
        this.clave = clave;
        this.dato = dato;
    }

    public T getClave() {
        return clave;
    }

    public void setClave(T clave) {
        this.clave = clave;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }
}

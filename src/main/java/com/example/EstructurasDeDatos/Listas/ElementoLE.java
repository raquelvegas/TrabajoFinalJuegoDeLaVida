package com.example.EstructurasDeDatos.Listas;

public class ElementoLE<TipoDato> {
    private TipoDato data;
    private ElementoLE<TipoDato> siguiente;

    ///////////////

    public ElementoLE(TipoDato s) {
        this.setData(s);
    }
    ///////////////

    public ElementoLE<TipoDato> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(ElementoLE<TipoDato> siguiente) {
        this.siguiente=siguiente;
    }

    public TipoDato getData() {
        return this.data;
    }

    public void setData(TipoDato data) {
        this.data = data;
    }
}

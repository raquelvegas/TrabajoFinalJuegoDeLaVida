package com.example.EstructurasDeDatos.Listas;

import com.google.gson.annotations.Expose;

public class ElementoLE<TipoDato> {
    @Expose
    private TipoDato data;

    @Expose
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

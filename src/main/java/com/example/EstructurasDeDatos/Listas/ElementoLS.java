package com.example.EstructurasDeDatos.Listas;

import com.google.gson.annotations.Expose;

public class ElementoLS<TipoDato> {
    @Expose
    TipoDato data;

    public ElementoLS(TipoDato dato) {
        this.setData(dato);
    }

    /// Métodos

    public TipoDato getData() {
        return data;
    }

    public void setData(TipoDato o) {
        this.data = o;
    }
}

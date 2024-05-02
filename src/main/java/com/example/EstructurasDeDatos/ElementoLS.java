package com.example.EstructurasDeDatos;

public class ElementoLS<TipoDato> {
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

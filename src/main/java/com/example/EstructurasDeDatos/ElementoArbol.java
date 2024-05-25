package com.example.EstructurasDeDatos;

import com.google.gson.annotations.Expose;

public class ElementoArbol<TipoDato> {
    @Expose
    private TipoDato dato;

    @Expose
    private ElementoArbol<TipoDato> nodoIzq;

    @Expose
    private ElementoArbol<TipoDato> nodoDch;

    public ElementoArbol(TipoDato dato) {
        this.dato = dato;
    }

    public ElementoArbol() {

    }

    public void setDatos(ElementoArbol<TipoDato> elemento) {
        this.dato = elemento.getDato();
        this.nodoDch = elemento.getNodoDch();
        this.nodoIzq = elemento.getNodoIzq();
    }

    public ElementoArbol<TipoDato> getNodoIzq() {
        return nodoIzq;
    }

    public void setNodoIzq(ElementoArbol<TipoDato> nodoIzq) {
        this.nodoIzq = nodoIzq;
    }

    public ElementoArbol<TipoDato> getNodoDch() {
        return nodoDch;
    }

    public void setNodoDch(ElementoArbol<TipoDato> nodoDch) {
        this.nodoDch = nodoDch;
    }

    public TipoDato getDato() {
        return dato;
    }

    private Integer compararDatos(TipoDato a, TipoDato b) {
        Comparable dato1 = (Comparable) a;
        Comparable dato2 = (Comparable) b;
        return dato1.compareTo(dato2);
    }

    public void add(ElementoArbol<TipoDato> nodo, TipoDato dato) {
        Integer comparacion = this.compararDatos(dato, nodo.getDato());
        if (comparacion == -1) {
            if (nodo.getNodoIzq() != null) {
                add(nodo.getNodoIzq(), dato);
            } else {
                nodo.setNodoIzq(new ElementoArbol<>(dato));
            }
        } else {
            if (nodo.getNodoDch() != null) {
                add(nodo.getNodoDch(), dato);
            } else {
                nodo.setNodoDch(new ElementoArbol<>(dato));
            }
        }
    }

}

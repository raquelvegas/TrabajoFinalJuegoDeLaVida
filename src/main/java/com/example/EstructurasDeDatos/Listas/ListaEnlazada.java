package com.example.EstructurasDeDatos.Listas;

import com.google.gson.annotations.Expose;

public class ListaEnlazada<T> {
    @Expose
    private ElementoLE<T> primero;

    @Expose
    private int numElem;

    ///////////////

    public ListaEnlazada() {
    }

    ///////////////

    public boolean isVacia() {
        return getNumeroElementos() == 0;
    }

    public ListaEnlazada<T> copiaLista() {
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<>();
        for (int i = 0; i < numElem; i++) {
            nuevaLista.add(this.getElemento(i).getData());
        }
        return nuevaLista;
    }

    public void vaciar() {
        primero = null;
        numElem = 0;
    }

    private void add(ElementoLE<T> el) {
        if (!isVacia()) {
            ElementoLE<T> inicial = primero;
            while (inicial.getSiguiente() != null) {
                inicial = inicial.getSiguiente();
            }
            inicial.setSiguiente(el);
        } else {
            primero = el;
        }
        numElem++;
    }

    public void add(T dato) {
        ElementoLE<T> sLE = new ElementoLE<>(dato);
        add(sLE);
    }

    public void del(int pos) {
        ElementoLE<T> inicial = primero;
        int posicionPedida = pos;
        if (pos >= 0 && pos < getNumeroElementos()) {
            if (pos == 0) {
                primero = primero.getSiguiente();
            } else {
                pos--;
                while (pos > 0) {
                    inicial = inicial.getSiguiente();
                    pos--;
                }
                if (posicionPedida == (getNumeroElementos() - 1)) {
                    inicial.setSiguiente(null);
                } else {
                    inicial.setSiguiente(inicial.getSiguiente().getSiguiente());
                }
            }
        }
        numElem--;
    }

    public int getNumeroElementos() {
        return numElem;
    }

    public ElementoLE<T> getPrimero() {
        return primero;
    }

    public ElementoLE<T> getUltimo() {
        if (!isVacia()) {
            ElementoLE<T> inicial = primero;
            while (inicial.getSiguiente() != null) {
                inicial = inicial.getSiguiente();
            }
            return inicial;
        } else { // Si es una lista vacía devuelve null
            return null;
        }
    }

    public ElementoLE<T> getElemento(int pos) {
        if (pos < 0 || pos >= (getNumeroElementos())) {
            return null;
        } else {
            ElementoLE<T> elemento = primero;
            while (pos > 0) {
                elemento = elemento.getSiguiente();
                pos--;
            }
            return elemento;
        }
    }
}

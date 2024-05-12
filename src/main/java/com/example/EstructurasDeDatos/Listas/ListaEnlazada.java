package com.example.EstructurasDeDatos.Listas;

public class ListaEnlazada<T> {
    private ElementoLE<T> primero;

    private int numElem;

    ///////////////

    public ListaEnlazada(ElementoLE<T> primero) {
        this.primero = primero;
    }

    public ListaEnlazada() {
    }

    ///////////////

    public boolean isVacia() {
        return getNumeroElementos() == 0;
    }

    public void vaciar() {
        primero = null;
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

    public Integer getPosicion(ElementoLE<T> el) { // Devuelve null si no existe ese elemento
        ElementoLE<T> inicial = primero;
        Integer pos = null;
        int contador = 0;
        while (inicial != null) {
            if (inicial.getData() == el.getData()) {
                pos = contador;
            }
            contador++;
            inicial = inicial.getSiguiente();
        }
        return pos;
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

    public ElementoLE<T> getSiguiente(ElementoLE<T> el) {
        ElementoLE<T> inicial = primero;
        if (isVacia() || getNumeroElementos() == 1 || getPosicion(el) == null) {
            return null;
        } else {
            while (inicial.getData() != el.getData()) {
                inicial = inicial.getSiguiente();
            }
            return inicial.getSiguiente();
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

package com.example.EstructurasDeDatos;


import java.util.Objects;

public class ListaSimple<Tipodato> {
    private ElementoLS<Tipodato>[] datos;

    private Integer maximo = 500;

    //////////////

    public ListaSimple() {
        this.datos = new ElementoLS[maximo];
    }

    ////////////////

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

    public boolean isVacia() {
        return getNumeroElementos() == 0;
    }

    public void vaciar() {
        while (!this.isVacia()) {
            datos[0] = null;
        }
    }

    public void add(Tipodato el) {
        int i = 0;
        while (i < maximo && datos[i] != null) {
            i++;
        }
        datos[i] = new ElementoLS<>(el);
    }

    public void insert(Tipodato el, Integer pos) {
        if (pos >= 0 && pos <= getNumeroElementos()) {  // Comprobar que la posición sea válida
            if (pos.equals(getNumeroElementos())) {
                add(el);
            } else {
                Integer elementosIniciales = getNumeroElementos();
                ElementoLS<Tipodato> SiguienteElemento = datos[pos];
                datos[pos] = new ElementoLS<>(el);
                pos++;
                while (Objects.equals(elementosIniciales, getNumeroElementos())) {
                    ElementoLS<Tipodato> nextEl = datos[pos];
                    datos[pos] = SiguienteElemento;
                    SiguienteElemento = nextEl;
                    pos++;
                }
            }
        }
    }

    public void del(int pos) {
        Integer num_elem = getNumeroElementos();
        if (pos < num_elem && pos >= 0) {
            datos[pos] = null;
                datos[pos] = datos[num_elem - 1];
                datos[num_elem - 1] = null;
        }
    }

    public Integer getPosicion(Tipodato data) {
        int cont = 0;
        Integer posicion = null;
        while (cont < maximo && datos[cont] != null && posicion == null) {
            if (datos[cont].getData() == data) {
                posicion = cont;
            }
            cont++;
        }
        return posicion;
    }

    public Tipodato getPrimero() {
        if (datos[0] == null) {
            return null;
        } else {
            return datos[0].getData();
        }
    }

    public Tipodato getUltimo() {
        int numElem = this.getNumeroElementos();
        if (numElem == 0) {
            return null;
        } else {
            return datos[numElem - 1].getData();
        }
    }

    private ElementoLS<Tipodato> getSiguiente(ElementoLS<Tipodato> el) {
        ElementoLS<Tipodato> devolver = null;
        for (int i = 0; i < maximo; i++) {
            if (datos[i] != null && datos[i + 1] != null && datos[i].getData() == el.getData())
                devolver = datos[i + 1];
        }
        return devolver;
    }

    public Tipodato getDato(int pos) {
        if (pos < maximo && pos >= 0) {
            if (datos[pos] == null) {
                return null;
            } else {
                return datos[pos].getData();
            }
        } else {
            return null;
        }
    }

    public Integer getNumeroElementos() {
        if (datos[0] == null) {
            return 0;
        } else {
            int contador = 1;
            ElementoLS<Tipodato> el = datos[contador - 1];
            while (this.getSiguiente(el) != null) {
                el=this.getSiguiente(el);
                contador++;
            }
            return contador;
        }
    }
}

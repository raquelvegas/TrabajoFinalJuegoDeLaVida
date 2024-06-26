package com.example.EstructurasDeDatos.Listas;


import com.google.gson.annotations.Expose;

public class ListaSimple<Tipodato> {
    @Expose
    private ElementoLS<Tipodato>[] datos;

    @Expose
    private Integer maximo = 5000;

    @Expose
    private Integer numElem;

    //////////////

    public ListaSimple() {
        this.datos = new ElementoLS[maximo];
        this.numElem=0;
    }

    public ListaSimple(int max) {
        this.datos = new ElementoLS[max];
        this.maximo = max;
        this.numElem=0;
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

    public void add(Tipodato el) {
        datos[numElem] = new ElementoLS<>(el);
        numElem++;
    }

    public void del(int pos) {
        Integer num_elem = getNumeroElementos();
        if (pos < num_elem && pos >= 0) {
            datos[pos] = null;
                datos[pos] = datos[num_elem - 1];
                datos[num_elem - 1] = null;
        }
        numElem--;
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
        return numElem;
    }

    public ElementoLS<Tipodato> getElemento(int pos) {
        if (pos < maximo && pos >= 0) {
            return datos[pos];
        } else {
            return null;
        }
    }

    public ListaSimple<Tipodato> voltear() {
        ListaSimple<Tipodato> nuevaLista = new ListaSimple<>();
        Integer contador=this.getNumeroElementos()-1;
        while (contador>=0){
            nuevaLista.add(this.getElemento(contador).getData());
            contador--;
        }
        return nuevaLista;
    }

    public ListaSimple<Tipodato> copiaLista() {
        ListaSimple<Tipodato> nuevaLista = new ListaSimple<>();
        Integer contador=0;
        while (contador<this.getNumeroElementos()){
            nuevaLista.add(datos[contador].getData());
            contador++;
        }
        nuevaLista.setMaximo(this.maximo);
        return nuevaLista;
    }

    public void setDatos(ElementoLS<Tipodato>[] datos) {
        this.datos = datos;
    }

    public ElementoLS<Tipodato>[] getDatos() {
        return datos;
    }

    public void setNumElem(Integer numElem) {
        this.numElem = numElem;
    }
}

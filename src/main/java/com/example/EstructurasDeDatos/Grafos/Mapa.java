package com.example.EstructurasDeDatos.Grafos;


import com.example.EstructurasDeDatos.Dupla;
import com.example.EstructurasDeDatos.Listas.ListaSimple;

public class Mapa<T, E> {
    ListaSimple<Dupla<T, E>> datos;

    public Mapa() {
        datos = new ListaSimple<>();
    }
    public boolean isVacio() {
        return this.datos.getPrimero()==null;
    }

    public void add(T clave, E dato) {
        if (this.isVacio()){
            datos.add(new Dupla<>(clave,dato));
        }
        int contador = 0;
        boolean existentIndex = false;
        while ((contador < datos.getNumeroElementos()) && (!existentIndex)) {
            if (datos.getElemento(contador).getData().getClave() == clave) {
                existentIndex = true;
            } else {
                contador++;
            }
        }
        if (!existentIndex) {
            datos.add(new Dupla<>(clave, dato));
        } else {
            datos.getElemento(contador).getData().setDato(dato);
        }
    }

    public E get(T indice) {
        Integer contador=0;
        while (contador<datos.getNumeroElementos()){
            if (datos.getElemento(contador).getData().getClave()==indice){
                return datos.getElemento(contador).getData().getDato();
            }
            contador++;
        }
        return null;
    }

    public ListaSimple<T> getIndices(){
        ListaSimple<T> listaIndices = new ListaSimple<>();
        Integer contador=0;
        while (contador<datos.getNumeroElementos()){
            listaIndices.add(datos.getElemento(contador).getData().getClave());
            contador++;
        }
        return listaIndices;
    }


}

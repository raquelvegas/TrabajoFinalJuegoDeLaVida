package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.ListaEnlazada;
import com.example.NewInterfaz.Square;

public class IndNormal extends Individuo{
    private ListaEnlazada<Square> recorrido = new ListaEnlazada<>();
    public IndNormal(ArbolBinario<Individuo> arbolGenealogico) {
        super(arbolGenealogico);
    }

    @Override
    public int getTipo() {
        return 1;
    }

    public ListaEnlazada<Square> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(ListaEnlazada<Square> recorrido) {
        this.recorrido = recorrido;
    }
}

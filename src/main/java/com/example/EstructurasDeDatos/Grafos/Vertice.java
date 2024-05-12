package com.example.EstructurasDeDatos.Grafos;


import com.example.EstructurasDeDatos.Listas.ListaSimple;

public class Vertice<TipoDato> {
    private Integer ID;

    private TipoDato dato;
    private ListaSimple<Arista> aristasVAntecesores;
    private ListaSimple<Arista> aristasVHijos;

    public Vertice(Integer ID,TipoDato dato) {
        this.ID=ID;
        this.dato = dato;
        this.aristasVHijos = new ListaSimple<>();
        this.aristasVAntecesores = new ListaSimple<>();
    }

    public void addAristaVAntecesor(Arista arista){
        this.aristasVAntecesores.add(arista);
    }

    public void addAristaVHijo(Arista arista){
        this.aristasVHijos.add(arista);
    }

    public TipoDato getDato() {
        return dato;
    }

    public void setDato(TipoDato dato) {
        this.dato = dato;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public ListaSimple<Arista> getAristasVAntecesores() {
        return aristasVAntecesores;
    }

    public void setAristasVAntecesores(ListaSimple<Arista> aristas) {
        this.aristasVAntecesores = aristas;
    }

    public ListaSimple<Arista> getAristasVHijos() {
        return aristasVHijos;
    }

    public void setAristasVHijos(ListaSimple<Arista> aristas) {
        this.aristasVHijos = aristas;
    }
}

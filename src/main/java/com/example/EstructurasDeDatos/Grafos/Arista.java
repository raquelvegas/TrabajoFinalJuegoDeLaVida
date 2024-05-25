package com.example.EstructurasDeDatos.Grafos;

public class Arista {
    private Vertice verticeIni;
    private Vertice verticeFin;
    private String anotacion;
    private Integer peso;

    public Arista(Vertice verticeIni, Vertice verticeFin, String anotacion, Integer peso){
        this.verticeIni = verticeIni;
        this.verticeFin = verticeFin;
        this.anotacion = anotacion;
        this.peso = peso;
    }

    public Arista(Vertice verticeIni, Vertice verticeFin, String anotacion) {
        this.verticeIni = verticeIni;
        this.verticeFin = verticeFin;
        this.anotacion = anotacion;
        this.peso=1;
    }

    public Arista(Vertice verticeIni, Vertice verticeFin, Integer peso) {
        this.verticeIni = verticeIni;
        this.verticeFin = verticeFin;
        this.peso = peso;
    }

    public Arista(Vertice verticeIni, Vertice verticeFin) {
        this.verticeIni = verticeIni;
        this.verticeFin = verticeFin;
        this.peso=1;
    }

    public Vertice getVerticeIni() {
        return verticeIni;
    }

    public void setVerticeIni(Vertice verticeIni) {
        this.verticeIni = verticeIni;
    }

    public Vertice getVerticeFin() {
        return verticeFin;
    }

    public void setVerticeFin(Vertice verticeFin) {
        this.verticeFin = verticeFin;
    }

    public String getAnotacion() {
        return anotacion;
    }

    public void setAnotacion(String anotacion) {
        this.anotacion = anotacion;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}

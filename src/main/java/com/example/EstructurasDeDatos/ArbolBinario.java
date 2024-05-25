package com.example.EstructurasDeDatos;

public class ArbolBinario<TipoDato> {
    private ElementoArbol<TipoDato> raiz;

    public ArbolBinario() {
    }

    public ElementoArbol<TipoDato> getRaiz() {
        return raiz;
    }

    public void setRaiz(TipoDato raiz) {
        ElementoArbol<TipoDato> elem = new ElementoArbol<>(raiz);
        this.raiz = elem;
    }

    public void setRaizEl(ElementoArbol<TipoDato> raiz) {
        this.raiz = raiz;
    }

    public ArbolBinario(TipoDato raiz) {
        this.raiz = new ElementoArbol<>(raiz);
        this.raiz.setNodoIzq(null);
        this.raiz.setNodoDch(null);
    }

    public ArbolBinario(TipoDato datoRaiz, ElementoArbol<TipoDato> padre, ElementoArbol<TipoDato> madre){
        this.raiz=new ElementoArbol<>(datoRaiz);
        this.raiz.setNodoDch(padre);
        this.raiz.setNodoIzq(madre);
    }

    public void add(TipoDato a){
        if (this.raiz == null) {
            this.raiz = new ElementoArbol<>(a);
        } else {
            raiz.add(raiz, a);
        }
    }

    @Override
    public String toString() {
        return "ArbolBinario{" +
                "raiz=" + raiz +
                '}';
    }
}

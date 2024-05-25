package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.NewInterfaz.Individuos.IndAvanzado;
import com.example.NewInterfaz.Individuos.Individuo;

public class Main {
    public static void main(String[] args){
        GrafoConocimiento g1 = new GrafoConocimiento();
        g1.addVertices(34);
        g1.addVertices("hola");
        Individuo ind1 = new IndAvanzado(new ArbolBinario<>());
        g1.addVertices(ind1.getID());
        g1.addArista(34,ind1.getID(),"Hola");
        System.out.println(g1.getGrafoConocimiento().getVertices().getNumeroElementos());
        System.out.println(g1.getGrafoConocimiento().getAristas());
        System.out.println(g1.getGrafoConocimiento().printCodigoGrafo());
    }
}

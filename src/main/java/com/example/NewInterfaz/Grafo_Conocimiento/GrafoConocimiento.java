package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Grafos.Grafo;
import com.example.EstructurasDeDatos.Grafos.Vertice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GrafoConocimiento {
    private static Grafo grafoConocimiento = new Grafo<>();
    private static final Logger log = LogManager.getLogger(GrafoConocimiento.class);

    public static void addVertices(Object v1) {
        grafoConocimiento.addVertice(new Vertice(v1));
    }

    public void addArista(Object v1, Object v2, String anotacion) {
        grafoConocimiento.addArista(v1, v2, anotacion);
    }

    public void traducirColaAcciones(Cola<Accion> colaAcciones){
        while (!colaAcciones.isVacia()){

        }
    }

    public Grafo getGrafoConocimiento() {
        return grafoConocimiento;
    }

    public void traducirCola(Cola<Accion> acciones) {

    }

    public void setGrafoConocimiento(Grafo grafoConocimiento) {
        this.grafoConocimiento = grafoConocimiento;
    }
}

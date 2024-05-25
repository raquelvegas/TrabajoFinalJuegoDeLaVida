package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Grafos.Grafo;
import com.example.EstructurasDeDatos.Grafos.Vertice;
import com.example.NewInterfaz.Recurso;
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

    public void traducirColaAcciones(Integer id, Cola<Accion> colaAcciones) {
        while (!colaAcciones.isVacia()){
            Accion accion = colaAcciones.pop();
            if (accion.getTipo() == 1) { // Consumición
                Consumición cons = (Consumición) accion;
                Recurso recursoConsumido = cons.getRec();
                addArista(id, recursoConsumido, "Consume");
            } else if (accion.getTipo() == 2) { // Reproducción
                Reproducción repr = (Reproducción) accion;
                Integer idPareja = repr.getIdPareja();
                addArista(id, idPareja, "Se reproduce con");
                Integer idNuevoInd = repr.getIdNuevoIndividuo();
                addArista(id, idNuevoInd, "Hijo con " + idPareja);
            } else if (accion.getTipo() == 3) {
                Clonación clon = (Clonación) accion;
                Integer idNuevoInd = clon.getIdNuevoIndividuo();
                addArista(id, idNuevoInd, "Se clona para generar el individuo");
            }
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

package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Grafos.Arista;
import com.example.EstructurasDeDatos.Grafos.Grafo;
import com.example.EstructurasDeDatos.Grafos.Vertice;
import com.example.NewInterfaz.Recurso;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;


public class GrafoConocimiento {
    private static Grafo grafoConocimiento = new Grafo<>();
    private static final Logger log = LogManager.getLogger(GrafoConocimiento.class);

    public static void addVertices(Object v1) {
        grafoConocimiento.addVertice(new Vertice(v1));
    }

    public static void addArista(Object v1, Object v2, String anotacion) {
        grafoConocimiento.addArista(v1, v2, anotacion);
    }

    public static void traducirColaAcciones(Integer id, Cola<Accion> colaAcciones) {
        Cola<Accion> copiaCola = colaAcciones.copiaCola();
        while (!copiaCola.isVacia()) {
            Accion accion = copiaCola.pop();
            if (accion.getTipo() == 1) { // Consumición
                Consumición cons = (Consumición) accion;
                Recurso recursoConsumido = cons.getRecursoConsumido();
                addArista(id, recursoConsumido, "Consume");
                log.info("Arista de consumición añadida al Grafo de Conocimiento");
            } else if (accion.getTipo() == 2) { // Reproducción
                Reproducción repr = (Reproducción) accion;
                Integer idPareja = repr.getIdPareja();
                addArista(id, idPareja, "Se reproduce con");
                Integer idNuevoInd = repr.getIdNuevoIndividuo();
                addArista(id, idNuevoInd, "Hijo con " + idPareja);
                log.info("Arista de consumición añadida al Grafo de Conocimiento");
            } else if (accion.getTipo() == 3) {
                Clonación clon = (Clonación) accion;
                Integer idNuevoInd = clon.getIdNuevoIndividuo();
                addArista(id, idNuevoInd, "Se clona para generar el individuo");
                log.info("Arista de clonación añadida al Grafo de Conocimiento");
            }
        }
    }

    public Grafo getGrafoConocimiento() {
        return grafoConocimiento;
    }

    public static Integer getNumeroReproducciones() {
        Integer contador = 0;
        for (int i = 0; i < grafoConocimiento.getAristas().getNumeroElementos(); i++) {
            Arista arista = (Arista) grafoConocimiento.getAristas().getDato(i);
            if (Objects.equals(arista.getAnotacion(), "Se reproduce con")) {
                contador++;
            }
        }
        return contador / 2; // Divido entre dos porque para cada reproducción se generan dos aristas entre las parejas
    }

    public static Integer getNumeroClonaciones() {
        Integer contador = 0;
        for (int i = 0; i < grafoConocimiento.getAristas().getNumeroElementos(); i++) {
            Arista arista = (Arista) grafoConocimiento.getAristas().getDato(i);
            if (Objects.equals(arista.getAnotacion(), "Se clona para generar el individuo")) {
                contador++;
            }
        }
        return contador;
    }

    public void setGrafoConocimiento(Grafo grafoConocimiento) {
        this.grafoConocimiento = grafoConocimiento;
    }
}

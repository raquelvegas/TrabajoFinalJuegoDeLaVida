package com.example.EstructurasDeDatos.Grafos;


import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.Excepciones.NonValidLink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/***
 * Implementación de un Grafo Simple en el que no se permiten aristas que vayan de un vértice a uno mismo, ni la existencia simultanea de dos aristas que unan los mismos nodos en el mismo sentido
 */
public class Grafo<TipoDato> {
    private ListaSimple<Vertice<TipoDato>> vertices = new ListaSimple<>();

    private ListaSimple<Arista> aristas = new ListaSimple<>();

    private static final Logger log = LogManager.getLogger(Grafo.class);


    // Constructores

    public Grafo() {
    }

    // Métodos para la modificacion de los elementos del grafo

    public void addVertice(Vertice<TipoDato> v) {
        this.vertices.add(v);
    }

    private void addArista(Arista a) throws NonValidLink {
        if (Objects.equals(a.getVerticeFin().getDato(), a.getVerticeIni().getDato())) {
            throw (new NonValidLink("ERROR. Una arista no puede unir un vértice consigo mismo."));
        } else if (validarArista(a)) {
            this.aristas.add(a);
            a.getVerticeIni().addAristaVHijo(a);
            a.getVerticeFin().addAristaVAntecesor(a);
        }
    }

    public void addArista(TipoDato a, TipoDato b, String anotacion) {
        Vertice<TipoDato> vIni = null;
        Vertice<TipoDato> vFin = null;
        for (int i = 0; i < vertices.getNumeroElementos(); i++) {
            if (vertices.getDato(i).getDato() == a) {
                vIni = vertices.getDato(i);
            }
            if (vertices.getDato(i).getDato() == b) {
                vFin = vertices.getDato(i);
            }
        }
        if (vIni != null && vFin != null) {
            try {
                addArista(new Arista(vIni, vFin, anotacion));
            } catch (NonValidLink ex) {
                ex.printStackTrace();
            }
        } else {
            log.error("Error en la introducción de los datos de los vértices que quieres unir");
        }
    }
    public void addArista(TipoDato a, TipoDato b, int peso) {
        Vertice<TipoDato> vIni = null;
        Vertice<TipoDato> vFin = null;
        for (int i = 0; i < vertices.getNumeroElementos(); i++) {
            if (vertices.getDato(i).getDato() == a) {
                vIni = vertices.getDato(i);
            }
            if (vertices.getDato(i).getDato() == b) {
                vFin = vertices.getDato(i);
            }
        }
        if (vIni != null && vFin != null) {
            try {
                addArista(new Arista(vIni, vFin, peso));
            } catch (NonValidLink ex) {
                ex.printStackTrace();
            }
        } else {
            log.error("Error en la introducción de los datos de los vértices que quieres unir");
        }
    }

    private boolean validarArista(Arista a) throws NonValidLink {
        Vertice vertice1 = a.getVerticeIni();
        Vertice vertice2 = a.getVerticeFin();
        boolean isVertice1 = false;
        boolean isVertice2 = false;
        Integer contador = 0;
        while (contador < vertices.getNumeroElementos()) {
            if (vertices.getElemento(contador).getData() == vertice1) {
                isVertice1 = true;
            }
            if (vertices.getElemento(contador).getData() == vertice2) {
                isVertice2 = true;
            }
            contador++;
        }
        if (isVertice1 && isVertice2) {
            Integer contador2 = 0;
            while (contador2 < aristas.getNumeroElementos()) {
                if ((Objects.equals(aristas.getElemento(contador2).getData().getVerticeIni(), vertice1)) && (Objects.equals(aristas.getElemento(contador2).getData().getVerticeFin(), vertice2))) {
                    throw (new NonValidLink("ERROR. Ya existe una arista uniendo estos dos vertices"));
                }
                contador2++;
            }
            return true;
        } else {
            throw (new NonValidLink("ERROR. Alguno de los vértices que une la arista no se encuentra en el grafo."));
        }
    }
    // Métodos para sacar los caminos mínimos


    private Vertice<TipoDato> getVertice(TipoDato a) {
        Vertice<TipoDato> vertice = null;
        for (int i = 0; i < vertices.getNumeroElementos(); i++) {
            if (vertices.getDato(i).getDato() == a) {
                vertice=vertices.getDato(i);
            }
        }
        return vertice;
    }

    public String printCodigoGrafo() {
        char com = '"';
        String codigo = "digraph regexp {\nfontname=" + com + "Helvetica,Arial,sans-serif" + com + "\nnode [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]\nedge [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]";
        Integer contadorV = 0;
        while (contadorV < vertices.getNumeroElementos()) {
            codigo += "\nn" + vertices.getElemento(contadorV).getData().getDato() + " [label=" + com + vertices.getElemento(contadorV).getData().getDato() + com + "];";
            contadorV++;
        }
        Integer contadorA = 0;
        while (contadorA < aristas.getNumeroElementos()) {
            codigo += "\nn" + aristas.getElemento(contadorA).getData().getVerticeIni().getDato() + " -> n" + aristas.getElemento(contadorA).getData().getVerticeFin().getDato() + " [label=" + com + aristas.getElemento(contadorA).getData().getAnotacion() + com + "];";
            contadorA++;
        }
        codigo += "\n}";
        return codigo;
    }

    public Camino getCaminoMinimo(TipoDato origen, TipoDato fin) {
        Vertice<TipoDato> Vorigen = this.getVertice(origen);
        Vertice<TipoDato> Vfin = this.getVertice(fin);

        return getCaminoMinimo(Vorigen, Vfin);
    }

    private Camino getCaminoMinimo(Vertice origen, Vertice fin) {

        Mapa<Vertice<TipoDato>, Camino> caminos = this.dijkstra(origen);
        Camino caminoCompleto = caminos.get(fin);
        return caminoCompleto;
    }

    private Mapa<Vertice<TipoDato>, Camino> dijkstra(Vertice origen) {
        Mapa<Vertice, Double> distancias = new Mapa<>();
        Cola<Vertice> colaPendientes = new Cola<>();
        Mapa<Vertice, Vertice> verticesAnteriores = new Mapa<>();

        this.dijkstra_inicializar(origen, distancias, colaPendientes);
        this.dijkstra_ejecucion(distancias, colaPendientes, verticesAnteriores);
        return this.dijkstra_resultados(distancias, verticesAnteriores);
    }

    private void dijkstra_inicializar(Vertice origen, Mapa<Vertice, Double> distancias, Cola<Vertice> colaPendientes) {
        int contador = 0;
        while (contador < this.vertices.getNumeroElementos()) {
            distancias.add(this.vertices.getElemento(contador).getData(), Double.MAX_VALUE);
            contador++;
        }
        distancias.add(origen, 0.0);
        colaPendientes.push(origen);
    }

    private void dijkstra_ejecucion(Mapa<Vertice, Double> distancias, Cola<Vertice> colaPendientes, Mapa<Vertice, Vertice> verticesAnteriores) {
        while (!colaPendientes.isVacia()) {
            Vertice verticeActual = colaPendientes.pop();

            Integer contador = 0;
            while (contador < verticeActual.getAristasVHijos().getNumeroElementos()) {
                Arista aristaDestino = (Arista) verticeActual.getAristasVHijos().getElemento(contador).getData();
                Vertice verticeDestino = aristaDestino.getVerticeFin();
                double nuevaDistancia = distancias.get(verticeActual) + aristaDestino.getPeso();

                if (nuevaDistancia < distancias.get(verticeDestino)) {
                    distancias.add(verticeDestino, nuevaDistancia);
                    verticesAnteriores.add(verticeDestino, verticeActual);
                    colaPendientes.push(verticeDestino);
                }
                contador++;
            }
        }
    }

    private Mapa<Vertice<TipoDato>, Camino> dijkstra_resultados(Mapa<Vertice, Double> distancias, Mapa<Vertice, Vertice> verticesAnteriores) {
        Mapa<Vertice<TipoDato>, Camino> caminos = new Mapa<>();

        Integer contador = 0;
        while (contador < verticesAnteriores.getIndices().getNumeroElementos()) {
            Vertice verticeDestino = verticesAnteriores.getIndices().getElemento(contador).getData();
            ListaSimple<Vertice> caminoCalculado = new ListaSimple<>();
            Vertice v = verticeDestino;
            while (v != null) {
                caminoCalculado.add(v);
                v = verticesAnteriores.get(v);
            }
            caminoCalculado = caminoCalculado.voltear();
            Camino camino = new Camino(caminoCalculado, distancias.get(verticeDestino));
            caminos.add(verticeDestino, camino);
            contador++;
        }
        return caminos;
    }

    public ListaSimple<Vertice<TipoDato>> getVertices() {
        return vertices;
    }

    public ListaSimple<Arista> getAristas() {
        return aristas;
    }

    public void setVertices(ListaSimple<Vertice<TipoDato>> vertices) {
        this.vertices = vertices;
    }

    public void setAristas(ListaSimple<Arista> aristas) {
        this.aristas = aristas;
    }

}

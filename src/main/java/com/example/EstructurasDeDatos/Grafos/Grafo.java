package com.example.EstructurasDeDatos.Grafos;


import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Listas.ListaSimple;

/***
 * Implementación de un Grafo Simple en el que no se permiten aristas que vayan de un vértice a uno mismo, ni la existencia simultanea de dos aristas que unan los mismos nodos en el mismo sentido
 */
public class Grafo<TipoDato> {
    private ListaSimple<Vertice<TipoDato>> vertices = new ListaSimple<>();

    private ListaSimple<Arista> aristas = new ListaSimple<>();

    // Constructores

    public Grafo() {
    }

    // Métodos para la modificacion de los elementos del grafo

    public void addVertice(Vertice v) {
        this.vertices.add(v);
    }

    public void addArista(Arista a) {
        this.aristas.add(a);
        a.getVerticeIni().addAristaVHijo(a);
        a.getVerticeFin().addAristaVAntecesor(a);

    }

    // Metodos para sacar por la terminal datos legibles

    public String getStringVertices() {
        int contador = 0;
        String lista = "[";
        while (contador < this.vertices.getNumeroElementos()) {
            if (this.vertices.getElemento(contador + 1) != null) {
                lista += this.vertices.getElemento(contador).getData().getDato() + ", ";
            } else {
                lista += this.vertices.getElemento(contador).getData().getDato() + "]";
            }
            contador++;
        }
        return lista;
    }

    public String getStringAristas() {
        int contador = 0;
        String lista = "[";
        while (contador < this.aristas.getNumeroElementos()) {
            if (this.aristas.getElemento(contador + 1) != null) {
                lista += "{" + this.aristas.getElemento(contador).getData().getVerticeIni().getDato() + ", " + this.aristas.getElemento(contador).getData().getVerticeFin().getDato() + "}, ";
            } else {
                lista += "{" + this.aristas.getElemento(contador).getData().getVerticeIni().getDato() + ", " + this.aristas.getElemento(contador).getData().getVerticeFin().getDato() + "}]";
            }
            contador++;
        }
        return lista;
    }

//    public String printCodigoGrafo() {
//        char com = '"';
//        String codigo = "digraph regexp {\nfontname=" + com + "Helvetica,Arial,sans-serif" + com + "\nnode [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]\nedge [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]";
//        Integer contadorV = 0;
//        while (contadorV < vertices.getNumeroElementos()) {
//            codigo += "\nn" + vertices.getElemento(contadorV).getData().getID() + " [label=" + com + vertices.getElemento(contadorV).getData().getID() + com + "];";
//            contadorV++;
//        }
//        Integer contadorA = 0;
//        while (contadorA < aristas.getNumeroElementos()) {
//            codigo += "\nn" + aristas.getElemento(contadorA).getData().getVerticeIni().getID() + " -> n" + aristas.getElemento(contadorA).getData().getVerticeFin().getID() + " [label=" + com + aristas.getElemento(contadorA).getData().getPeso() + com + "];";
//            contadorA++;
//        }
//        codigo += "\n}";
//        return codigo;
//    }
    // Métodos para sacar los caminos mínimos


    public Camino getCaminoMinimo(Vertice origen, Vertice fin) {
        Mapa<Vertice, Camino> caminos = this.dijkstra(origen);
        Camino caminoCompleto = caminos.get(fin);
        return caminoCompleto;
    }

    private Mapa<Vertice, Camino> dijkstra(Vertice origen) {
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

    private Mapa<Vertice, Camino> dijkstra_resultados(Mapa<Vertice, Double> distancias, Mapa<Vertice, Vertice> verticesAnteriores) {
        Mapa<Vertice, Camino> caminos = new Mapa<>();

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

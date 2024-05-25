package com.example.EstructurasDeDatos.Grafos;

import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.Excepciones.NonValidLink;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    @Test
    void addVertice() {
        Grafo g1 = new Grafo();
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);

        assertDoesNotThrow(() -> g1.addVertice(v1));
        assertDoesNotThrow(() -> g1.addVertice(v2));
        assertDoesNotThrow(() -> g1.addVertice(v3));
        assertEquals(3,g1.getVertices().getNumeroElementos());
    }

    @Test
    void addArista() {
        Grafo<Integer> g1 = new Grafo<>();
        Vertice v1 = new Vertice<>(1);
        Vertice v2 = new Vertice<>(2);
        Vertice v3 = new Vertice<>( 3);
        Vertice v4 = new Vertice<>( 4);
        g1.addVertice(v1);
        g1.addVertice(v2);
        g1.addVertice(v3);
        g1.addVertice(v4);
        g1.addArista(1,2,3);
        assertEquals(1,g1.getAristas().getNumeroElementos());
        assertDoesNotThrow(() -> g1.addArista(1,1,3));
        assertDoesNotThrow(()->g1.addArista(1,5,34));
    }

    @Test
    void getCaminoMinimo() {
        Grafo g1 = new Grafo();
        Vertice<Integer> v1 = new Vertice<>(1);
        Vertice<Integer> v2 = new Vertice<>(2);
        Vertice<Integer> v3 = new Vertice<>(3);
        Vertice<Integer> v4 = new Vertice<>(4);
        Vertice<Integer> v5 = new Vertice<>( 5);
        Vertice<String> v6 = new Vertice<>("hola");
        Vertice<Integer> v7 = new Vertice<>(7);

            g1.addVertice(v1);
            g1.addVertice(v2);
            g1.addVertice(v3);
            g1.addVertice(v4);
            g1.addVertice(v6);
            g1.addVertice(v7);


            g1.addArista(1, 2, 3);
            g1.addArista(1, 3, 5);
            g1.addArista(4, 1, 1);
            g1.addArista(3, 4, 98);
            g1.addArista(3, "hola",1);
            g1.addArista(7, 2,1);

        assertDoesNotThrow(() -> g1.getCaminoMinimo(3, 2));


    }

    @Test
    void getVertices() {
        Grafo<Integer> g1 = new Grafo<>();
        Vertice v1 = new Vertice<>(1);
        Vertice v2 = new Vertice<>(2);
        Vertice v3 = new Vertice<>( 3);
        Vertice v4 = new Vertice<>( 4);
        g1.addVertice(v1);
        g1.addVertice(v2);
        g1.addVertice(v3);
        g1.addVertice(v4);
        assertEquals(4,g1.getVertices().getNumeroElementos());
    }

    @Test
    void getAristas() {
        Grafo<Integer> g1 = new Grafo<>();
        Vertice v1 = new Vertice<>(1);
        Vertice v2 = new Vertice<>(2);
        Vertice v3 = new Vertice<>( 3);
        Vertice v4 = new Vertice<>( 4);
        g1.addVertice(v1);
        g1.addVertice(v2);
        g1.addVertice(v3);
        g1.addVertice(v4);
        g1.addArista(1,2,3);
        g1.addArista(3,4,34);
        assertEquals(2,g1.getAristas().getNumeroElementos());
    }

    @Test
    void setVertices() {
        Grafo<Integer> g1 = new Grafo<>();
        Vertice v1 = new Vertice<>(1);
        Vertice v2 = new Vertice<>(2);
        Vertice v3 = new Vertice<>( 3);
        Vertice v4 = new Vertice<>( 4);
        ListaSimple vertices = new ListaSimple<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        g1.setVertices(vertices);
    }

    @Test
    void setAristas() {
        Grafo<Integer> g1 = new Grafo<>();
        Vertice v1 = new Vertice<>(1);
        Vertice v2 = new Vertice<>(2);
        Vertice v3 = new Vertice<>( 3);
        Vertice v4 = new Vertice<>( 4);
        Arista a1 = new Arista(v1,v2);
        Arista a2 = new Arista(v3,v4);
        ListaSimple aristas = new ListaSimple<>();
        aristas.add(a1);
        aristas.add(a2);
        g1.setAristas(aristas);
    }
}
package com.example.EstructurasDeDatos.Grafos;

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
    }

    @Test
    void getVertices() {
    }

    @Test
    void getAristas() {
    }

    @Test
    void setVertices() {
    }

    @Test
    void setAristas() {
    }
}
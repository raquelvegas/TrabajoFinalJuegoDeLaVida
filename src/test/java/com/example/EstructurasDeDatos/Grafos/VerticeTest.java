package com.example.EstructurasDeDatos.Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerticeTest {

    @Test
    void setDato() {
        Vertice v1 = new Vertice<>(23);
        v1.setDato(32);
        assertEquals(32,v1.getDato());
    }

    @Test
    void getAristasVAntecesores() {

    }

    @Test
    void setAristasVAntecesores() {
    }

    @Test
    void setAristasVHijos() {
    }
}
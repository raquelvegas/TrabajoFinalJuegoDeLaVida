package com.example.EstructurasDeDatos.Listas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaEnlazadaTest {

    @Test
    void isVacia() {
        ListaEnlazada lista1 = new ListaEnlazada();
        assertTrue(lista1.isVacia());
    }

    @Test
    void vaciar() {
        ListaEnlazada lista1 = new ListaEnlazada();
        lista1.add("Elemento1");
        lista1.vaciar();
        assertTrue(lista1.isVacia());
    }

    @Test
    void add() {
        ListaEnlazada lista1 = new ListaEnlazada();
        lista1.add("Elemento1");
        lista1.add("Elemento2");
        assertEquals(2,lista1.getNumeroElementos());
        assertTrue(lista1.getPrimero().getSiguiente().getData() == "Elemento2");
    }

    @Test
    void del() {
        ListaEnlazada<String> lista1 = new ListaEnlazada<>();
        lista1.add("Elemento1");
        lista1.add("Elemento2");
        lista1.add("Elemento3");
        lista1.add("Elemento4");
        lista1.del(0);
        assertEquals(3, lista1.getNumeroElementos());
        lista1.del(2);
        assertEquals(lista1.getPrimero().getData(), "Elemento2");
        assertEquals(lista1.getUltimo().getData(), "Elemento3");
        lista1.add("Elemento5");
        lista1.add("Elemento6");
        lista1.add("Elemento7");
        lista1.del(3);
    }

    @Test
    void getNumeroElementos() {
        ListaEnlazada<String> lista1 = new ListaEnlazada<>();
        lista1.add("Elemento1");
        lista1.add("Elemento2");
        lista1.add("Elemento3");
        lista1.add("Elemento4");
        lista1.del(0);
        assertEquals(3, lista1.getNumeroElementos());
    }

    @Test
    void getPrimero() {
        ListaEnlazada<String> lista1 = new ListaEnlazada<>();
        lista1.add("Elemento1");
        lista1.add("Elemento2");
        lista1.add("Elemento3");
        lista1.add("Elemento4");
        assertEquals("Elemento1", lista1.getPrimero().getData());
    }

    @Test
    void getUltimo() {
        ListaEnlazada<String> lista1 = new ListaEnlazada<>();
        assertNull(lista1.getUltimo());
        lista1.add("Elemento1");
        lista1.add("Elemento2");
        lista1.add("Elemento3");
        lista1.add("Elemento4");
        assertEquals("Elemento4",lista1.getUltimo().getData());
    }

    @Test
    void getElemento() {
        ListaEnlazada<String> lista1 = new ListaEnlazada<>();
        assertNull(lista1.getElemento(898));
        lista1.add("Elemento1");
        lista1.add("Elemento2");
        lista1.add("Elemento3");
        lista1.add("Elemento4");
        assertEquals("Elemento2",lista1.getElemento(1).getData());
    }
}
package com.example.EstructurasDeDatos.Listas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaSimpleTest {

    @Test
    void getMaximo() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        assertEquals(5000, lista1.getMaximo());
    }

    @Test
    void setMaximo() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.setMaximo(78);
        assertEquals(78, lista1.getMaximo());
    }

    @Test
    void isVacia() {
        ListaSimple<Integer> lista1 = new ListaSimple<>(12);
        assertTrue(lista1.isVacia());
        lista1.add(6);
        assertFalse(lista1.isVacia());
    }

    @Test
    void add() {
        ListaSimple<Integer> lista1 = new ListaSimple<>(12);
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(3, lista1.getNumeroElementos());
        assertEquals(2, lista1.getDato(1));
    }

    @Test
    void del() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(3, lista1.getNumeroElementos());
        lista1.del(0);
        assertEquals(2,lista1.getNumeroElementos());
    }

    @Test
    void getPrimero() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        assertNull(lista1.getPrimero());
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(1,lista1.getPrimero());
    }

    @Test
    void getUltimo() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        assertNull(lista1.getUltimo());
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(3,lista1.getUltimo());
    }

    @Test
    void getDato() {
        ListaSimple<Integer> lista1 = new ListaSimple<>(34);
        assertNull(lista1.getDato(5));
        assertNull(lista1.getDato(6876));
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(1,lista1.getDato(0));
    }

    @Test
    void getNumeroElementos() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(3,lista1.getNumeroElementos());
    }

    @Test
    void getElemento() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        assertEquals(1,lista1.getElemento(0).getData());
        assertNull(lista1.getElemento(89003));
    }

    @Test
    void voltear() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        ListaSimple<Integer> lista2 = lista1.voltear();
        assertEquals(3,lista2.getPrimero());
    }

    @Test
    void copiaLista() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.add(1);
        lista1.add(2);
        lista1.add(3);
        ListaSimple<Integer> lista2 = lista1.copiaLista();
        assertEquals(lista1.getNumeroElementos(),lista2.getNumeroElementos());
    }

    @Test
    void setDatos() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        ElementoLS[] datos = new ElementoLS[3];
        datos[0]=new ElementoLS(2);
        lista1.setDatos(datos);
        assertEquals(2,lista1.getPrimero());

    }

    @Test
    void setNumElem() {
        ListaSimple<Integer> lista1 = new ListaSimple<>();
        lista1.setNumElem(78);
        assertEquals(78,lista1.getNumeroElementos());
    }
}
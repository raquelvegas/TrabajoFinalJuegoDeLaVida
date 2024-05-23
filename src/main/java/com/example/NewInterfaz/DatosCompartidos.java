package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.NewInterfaz.Individuos.Individuo;

public class DatosCompartidos {
    private static String  altoMatriz = "0";
    private static String anchoMatriz = "0";
    private static String probReproduccion = "0";
    private static String probClonacion = "0";
    private static String vidaInicial = "0";
    private static String aguaVida = "0";
    private static String comidaVida = "0";
    private static String montanaVida = "0";
    private static String tesoroVida = "0";
    private static String bibliotecaVida = "0";
    private static String pozoVida = "0";
    private static String aguaEfecto = "0";
    private static String comidaEfecto = "0";
    private static String montanaEfecto = "0";
    private static String tesoroEfecto = "0";
    private static String bibliotecaEfecto = "0";
    private static String aguaAparicion = "0";
    private static String comidaAparicion = "0";
    private static String montanaAparicion = "0";
    private static String tesoroAparicion = "0";
    private static String bibliotecaAparicion = "0";
    private static String pozoAparicion = "0";
    private static int anadir = 0;
    private static boolean anadirTab = false;
    private static boolean contenidoCeldaTab = false;
    private static int numIndividuos = 0;
    private static int turnoJuego = 0;
    private static double velocidadJuego = 1;
    private static boolean gameIniciado = false;
    private static boolean tableroAleatorio = false;
    private static ListaEnlazada<Recurso> listaRecursos= new ListaEnlazada<>();
    private static ListaEnlazada<Individuo> listaIndividuos = new ListaEnlazada<>();
    private static Game game = null;


    public static String getAltoMatriz() {
        return altoMatriz;
    }

    public static void setAltoMatriz(String altoMatriz) {
        DatosCompartidos.altoMatriz = altoMatriz;
    }

    public static String getAnchoMatriz() {
        return anchoMatriz;
    }

    public static void setAnchoMatriz(String anchoMatriz) {
        DatosCompartidos.anchoMatriz = anchoMatriz;
    }

    public static String getProbReproduccion() {
        return probReproduccion;
    }

    public static void setProbReproduccion(String probReproduccion) {
        DatosCompartidos.probReproduccion = probReproduccion;
    }

    public static String getProbClonacion() {
        return probClonacion;
    }

    public static void setProbClonacion(String probClonacion) {
        DatosCompartidos.probClonacion = probClonacion;
    }

    public static String getVidaInicial() {
        return vidaInicial;
    }

    public static void setVidaInicial(String vidaInicial) {
        DatosCompartidos.vidaInicial = vidaInicial;
    }

    public static String getAguaVida() {
        return aguaVida;
    }

    public static void setAguaVida(String aguaVida) {
        DatosCompartidos.aguaVida = aguaVida;
    }

    public static String getComidaVida() {
        return comidaVida;
    }

    public static void setComidaVida(String comidaVida) {
        DatosCompartidos.comidaVida = comidaVida;
    }

    public static String getMontanaVida() {
        return montanaVida;
    }

    public static void setMontanaVida(String montanaVida) {
        DatosCompartidos.montanaVida = montanaVida;
    }

    public static String getTesoroVida() {
        return tesoroVida;
    }

    public static void setTesoroVida(String tesoroVida) {
        DatosCompartidos.tesoroVida = tesoroVida;
    }

    public static String getBibliotecaVida() {
        return bibliotecaVida;
    }

    public static void setBibliotecaVida(String bibliotecaVida) {
        DatosCompartidos.bibliotecaVida = bibliotecaVida;
    }

    public static String getPozoVida() {
        return pozoVida;
    }

    public static void setPozoVida(String pozoVida) {
        DatosCompartidos.pozoVida = pozoVida;
    }

    public static String getAguaEfecto() {
        return aguaEfecto;
    }

    public static void setAguaEfecto(String aguaEfecto) {
        DatosCompartidos.aguaEfecto = aguaEfecto;
    }

    public static String getComidaEfecto() {
        return comidaEfecto;
    }

    public static void setComidaEfecto(String comidaEfecto) {
        DatosCompartidos.comidaEfecto = comidaEfecto;
    }

    public static String getMontanaEfecto() {
        return montanaEfecto;
    }

    public static void setMontanaEfecto(String montanaEfecto) {
        DatosCompartidos.montanaEfecto = montanaEfecto;
    }

    public static String getTesoroEfecto() {
        return tesoroEfecto;
    }

    public static void setTesoroEfecto(String tesoroEfecto) {
        DatosCompartidos.tesoroEfecto = tesoroEfecto;
    }

    public static String getBibliotecaEfecto() {
        return bibliotecaEfecto;
    }

    public static void setBibliotecaEfecto(String bibliotecaEfecto) {
        DatosCompartidos.bibliotecaEfecto = bibliotecaEfecto;
    }

    public static String getAguaAparicion() {
        return aguaAparicion;
    }

    public static void setAguaAparicion(String aguaAparicion) {
        DatosCompartidos.aguaAparicion = aguaAparicion;
    }

    public static String getComidaAparicion() {
        return comidaAparicion;
    }

    public static void setComidaAparicion(String comidaAparicion) {
        DatosCompartidos.comidaAparicion = comidaAparicion;
    }

    public static String getMontanaAparicion() {
        return montanaAparicion;
    }

    public static void setMontanaAparicion(String montanaAparicion) {
        DatosCompartidos.montanaAparicion = montanaAparicion;
    }

    public static String getTesoroAparicion() {
        return tesoroAparicion;
    }

    public static void setTesoroAparicion(String tesoroAparicion) {
        DatosCompartidos.tesoroAparicion = tesoroAparicion;
    }

    public static String getBibliotecaAparicion() {
        return bibliotecaAparicion;
    }

    public static void setBibliotecaAparicion(String bibliotecaAparicion) {
        DatosCompartidos.bibliotecaAparicion = bibliotecaAparicion;
    }

    public static String getPozoAparicion() {
        return pozoAparicion;
    }

    public static void setPozoAparicion(String pozoAparicion) {
        DatosCompartidos.pozoAparicion = pozoAparicion;
    }

    public static int getAnadir() {
        return anadir;
    }

    public static void setAnadir(int anadir) {
        DatosCompartidos.anadir = anadir;
    }

    public static boolean isAnadirTab() {
        return anadirTab;
    }

    public static void setAnadirTab(boolean anadirTab) {
        DatosCompartidos.anadirTab = anadirTab;
    }

    public static boolean isContenidoCeldaTab() {
        return contenidoCeldaTab;
    }

    public static void setContenidoCeldaTab(boolean contenidoCeldaTab) {
        DatosCompartidos.contenidoCeldaTab = contenidoCeldaTab;
    }

    public static int getNumIndividuos() {
        return numIndividuos;
    }

    public static void setNumIndividuos(int numIndividuos) {
        DatosCompartidos.numIndividuos = numIndividuos;
    }

    public static int getTurnoJuego() {
        return turnoJuego;
    }

    public static void setTurnoJuego(int turnoJuego) {
        DatosCompartidos.turnoJuego = turnoJuego;
    }

    public static double getVelocidadJuego() {
        return velocidadJuego;
    }

    public static void setVelocidadJuego(double velocidadJuego) {
        DatosCompartidos.velocidadJuego = velocidadJuego;
    }

    public static boolean isGameIniciado() {
        return gameIniciado;
    }

    public static void setGameIniciado(boolean gameIniciado) {
        DatosCompartidos.gameIniciado = gameIniciado;
    }

    public static boolean isTableroAleatorio() {
        return tableroAleatorio;
    }

    public static void setTableroAleatorio(boolean tableroAleatorio) {
        DatosCompartidos.tableroAleatorio = tableroAleatorio;
    }

    public static ListaEnlazada<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public static void setListaRecursos(ListaEnlazada<Recurso> listaRecursos) {
        DatosCompartidos.listaRecursos = listaRecursos;
    }

    public static ListaEnlazada<Individuo> getListaIndividuos() {
        return listaIndividuos;
    }

    public static void setListaIndividuos(ListaEnlazada<Individuo> listaIndividuos) {
        DatosCompartidos.listaIndividuos = listaIndividuos;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        DatosCompartidos.game = game;
    }
}

package com.example.SaveInfo;

import com.example.EstructurasDeDatos.Cola;
import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.NewInterfaz.DatosCompartidos;
import com.example.NewInterfaz.Game;
import com.example.NewInterfaz.Grafo_Conocimiento.Acción;
import com.example.NewInterfaz.Individuos.Individuo;
import com.example.NewInterfaz.Recurso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;

public class SaveInfo {

    private static final Logger log = LogManager.getLogger(Game.class);

    @Expose
    private String altoMatriz;

    @Expose
    private String anchoMatriz;

    @Expose
    private String probReproduccion;

    @Expose
    private String probClonacion;

    @Expose
    private String vidaInicial;

    @Expose
    private String aguaVida;

    @Expose
    private String comidaVida;

    @Expose
    private String montanaVida;

    @Expose
    private String tesoroVida;

    @Expose
    private String bibliotecaVida;

    @Expose
    private String pozoVida;

    @Expose
    private String aguaEfecto;

    @Expose
    private String comidaEfecto;

    @Expose
    private String montanaEfecto;

    @Expose
    private String tesoroEfecto;

    @Expose
    private String bibliotecaEfecto;

    @Expose
    private String aguaAparicion;

    @Expose
    private String comidaAparicion;

    @Expose
    private String montanaAparicion;

    @Expose
    private String tesoroAparicion;

    @Expose
    private String bibliotecaAparicion;

    @Expose
    private String pozoAparicion;

    @Expose
    private int anadir;

    @Expose
    private boolean anadirTab;

    @Expose
    private boolean contenidoCeldaTab;

    @Expose
    private int numIndividuos;

    @Expose
    private int turnoJuego;

    @Expose
    private double velocidadJuego;

    @Expose
    private boolean gameIniciado;

    @Expose
    private ListaEnlazada<Recurso> listaRecursos;

    @Expose
    private ListaEnlazada<Individuo> listaIndividuos;

    @Expose
    private Game game;

    public SaveInfo(String altoMatriz, String anchoMatriz, String probReproduccion, String probClonacion, String vidaInicial, String aguaVida, String comidaVida, String montanaVida, String tesoroVida, String bibliotecaVida, String pozoVida, String aguaEfecto, String comidaEfecto, String montanaEfecto, String tesoroEfecto, String bibliotecaEfecto, String aguaAparicion, String comidaAparicion, String montanaAparicion, String tesoroAparicion, String bibliotecaAparicion, String pozoAparicion, int anadir, boolean anadirTab, boolean contenidoCeldaTab, int numIndividuos, int turnoJuego, double velocidadJuego, boolean gameIniciado, ListaEnlazada<Recurso> listaRecursos, ListaEnlazada<Individuo> listaIndividuos, Game game) {
        this.altoMatriz = altoMatriz;
        this.anchoMatriz = anchoMatriz;
        this.probReproduccion = probReproduccion;
        this.probClonacion = probClonacion;
        this.vidaInicial = vidaInicial;
        this.aguaVida = aguaVida;
        this.comidaVida = comidaVida;
        this.montanaVida = montanaVida;
        this.tesoroVida = tesoroVida;
        this.bibliotecaVida = bibliotecaVida;
        this.pozoVida = pozoVida;
        this.aguaEfecto = aguaEfecto;
        this.comidaEfecto = comidaEfecto;
        this.montanaEfecto = montanaEfecto;
        this.tesoroEfecto = tesoroEfecto;
        this.bibliotecaEfecto = bibliotecaEfecto;
        this.aguaAparicion = aguaAparicion;
        this.comidaAparicion = comidaAparicion;
        this.montanaAparicion = montanaAparicion;
        this.tesoroAparicion = tesoroAparicion;
        this.bibliotecaAparicion = bibliotecaAparicion;
        this.pozoAparicion = pozoAparicion;
        this.anadir = anadir;
        this.anadirTab = anadirTab;
        this.contenidoCeldaTab = contenidoCeldaTab;
        this.numIndividuos = numIndividuos;
        this.turnoJuego = turnoJuego;
        this.velocidadJuego = velocidadJuego;
        this.gameIniciado = gameIniciado;
        this.listaRecursos = listaRecursos;
        this.listaIndividuos = listaIndividuos;
        this.game = game;
    }

    public String getAltoMatriz() {
        return altoMatriz;
    }

    public void setAltoMatriz(String altoMatriz) {
        this.altoMatriz = altoMatriz;
    }

    public String getAnchoMatriz() {
        return anchoMatriz;
    }

    public void setAnchoMatriz(String anchoMatriz) {
        this.anchoMatriz = anchoMatriz;
    }

    public String getProbReproduccion() {
        return probReproduccion;
    }

    public void setProbReproduccion(String probReproduccion) {
        this.probReproduccion = probReproduccion;
    }

    public String getProbClonacion() {
        return probClonacion;
    }

    public void setProbClonacion(String probClonacion) {
        this.probClonacion = probClonacion;
    }

    public String getVidaInicial() {
        return vidaInicial;
    }

    public void setVidaInicial(String vidaInicial) {
        this.vidaInicial = vidaInicial;
    }

    public String getAguaVida() {
        return aguaVida;
    }

    public void setAguaVida(String aguaVida) {
        this.aguaVida = aguaVida;
    }

    public String getComidaVida() {
        return comidaVida;
    }

    public void setComidaVida(String comidaVida) {
        this.comidaVida = comidaVida;
    }

    public String getMontanaVida() {
        return montanaVida;
    }

    public void setMontanaVida(String montanaVida) {
        this.montanaVida = montanaVida;
    }

    public String getTesoroVida() {
        return tesoroVida;
    }

    public void setTesoroVida(String tesoroVida) {
        this.tesoroVida = tesoroVida;
    }

    public String getBibliotecaVida() {
        return bibliotecaVida;
    }

    public void setBibliotecaVida(String bibliotecaVida) {
        this.bibliotecaVida = bibliotecaVida;
    }

    public String getPozoVida() {
        return pozoVida;
    }

    public void setPozoVida(String pozoVida) {
        this.pozoVida = pozoVida;
    }

    public String getAguaEfecto() {
        return aguaEfecto;
    }

    public void setAguaEfecto(String aguaEfecto) {
        this.aguaEfecto = aguaEfecto;
    }

    public String getComidaEfecto() {
        return comidaEfecto;
    }

    public void setComidaEfecto(String comidaEfecto) {
        this.comidaEfecto = comidaEfecto;
    }

    public String getMontanaEfecto() {
        return montanaEfecto;
    }

    public void setMontanaEfecto(String montanaEfecto) {
        this.montanaEfecto = montanaEfecto;
    }

    public String getTesoroEfecto() {
        return tesoroEfecto;
    }

    public void setTesoroEfecto(String tesoroEfecto) {
        this.tesoroEfecto = tesoroEfecto;
    }

    public String getBibliotecaEfecto() {
        return bibliotecaEfecto;
    }

    public void setBibliotecaEfecto(String bibliotecaEfecto) {
        this.bibliotecaEfecto = bibliotecaEfecto;
    }

    public String getAguaAparicion() {
        return aguaAparicion;
    }

    public void setAguaAparicion(String aguaAparicion) {
        this.aguaAparicion = aguaAparicion;
    }

    public String getComidaAparicion() {
        return comidaAparicion;
    }

    public void setComidaAparicion(String comidaAparicion) {
        this.comidaAparicion = comidaAparicion;
    }

    public String getMontanaAparicion() {
        return montanaAparicion;
    }

    public void setMontanaAparicion(String montanaAparicion) {
        this.montanaAparicion = montanaAparicion;
    }

    public String getTesoroAparicion() {
        return tesoroAparicion;
    }

    public void setTesoroAparicion(String tesoroAparicion) {
        this.tesoroAparicion = tesoroAparicion;
    }

    public String getBibliotecaAparicion() {
        return bibliotecaAparicion;
    }

    public void setBibliotecaAparicion(String bibliotecaAparicion) {
        this.bibliotecaAparicion = bibliotecaAparicion;
    }

    public String getPozoAparicion() {
        return pozoAparicion;
    }

    public void setPozoAparicion(String pozoAparicion) {
        this.pozoAparicion = pozoAparicion;
    }

    public int getAnadir() {
        return anadir;
    }

    public void setAnadir(int anadir) {
        this.anadir = anadir;
    }

    public boolean isAnadirTab() {
        return anadirTab;
    }

    public void setAnadirTab(boolean anadirTab) {
        this.anadirTab = anadirTab;
    }

    public boolean isContenidoCeldaTab() {
        return contenidoCeldaTab;
    }

    public void setContenidoCeldaTab(boolean contenidoCeldaTab) {
        this.contenidoCeldaTab = contenidoCeldaTab;
    }

    public int getNumIndividuos() {
        return numIndividuos;
    }

    public void setNumIndividuos(int numIndividuos) {
        this.numIndividuos = numIndividuos;
    }

    public int getTurnoJuego() {
        return turnoJuego;
    }

    public void setTurnoJuego(int turnoJuego) {
        this.turnoJuego = turnoJuego;
    }

    public double getVelocidadJuego() {
        return velocidadJuego;
    }

    public void setVelocidadJuego(double velocidadJuego) {
        this.velocidadJuego = velocidadJuego;
    }

    public boolean isGameIniciado() {
        return gameIniciado;
    }

    public void setGameIniciado(boolean gameIniciado) {
        this.gameIniciado = gameIniciado;
    }

    public ListaEnlazada<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(ListaEnlazada<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public ListaEnlazada<Individuo> getListaIndividuos() {
        return listaIndividuos;
    }

    public void setListaIndividuos(ListaEnlazada<Individuo> listaIndividuos) {
        this.listaIndividuos = listaIndividuos;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void guardar(String rutaArchivo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Individuo.class, new gsonAdapterIndividuo())
                .registerTypeAdapter(Cola.class, new gsonAdapterCola())
                .registerTypeAdapter(Acción.class, new gsonAdapterAccion())
//                .registerTypeAdapter(ListaSimple.class, new gsonAdapterListaSimple())
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.STATIC)
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            gson.toJson(this,writer);
        } catch (IOException ex) {
            log.fatal("Error al guardar el archivo");
        }
    }

}
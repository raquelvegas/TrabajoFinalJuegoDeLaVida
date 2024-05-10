package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.Cola;
import com.example.NewInterfaz.DatosCompartidos;

public abstract class Individuo {
    private int ID;
    private int Generacion;
    private int TurnosVida;
    private int ProbRepr;
    private int ProbClon;
    private Cola<Integer> acciones;
    private ArbolBinario<Individuo> arbolGenealogico;

    public abstract int getTipo();

    public Individuo(ArbolBinario<Individuo> arbolGenealogico) {
        this.ID = DatosCompartidos.getNumIndividuos();
        Generacion = DatosCompartidos.getTurnoJuego();
        TurnosVida = Integer.parseInt(DatosCompartidos.getVidaInicial());
        ProbRepr = Integer.parseInt(DatosCompartidos.getProbReproduccion());
        ProbClon = Integer.parseInt(DatosCompartidos.getProbClonacion());
        this.acciones = new Cola<Integer>();
        this.arbolGenealogico = arbolGenealogico;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGeneracion() {
        return Generacion;
    }

    public void setGeneracion(int generacion) {
        Generacion = generacion;
    }

    public int getTurnosVida() {
        return TurnosVida;
    }

    public void setTurnosVida(int turnosVida) {
        TurnosVida = turnosVida;
    }

    public int getProbRepr() {
        return ProbRepr;
    }

    public void setProbRepr(int probRepr) {
        ProbRepr = probRepr;
    }

    public int getProbClon() {
        return ProbClon;
    }

    public void setProbClon(int probClon) {
        ProbClon = probClon;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public Cola<Integer> getAcciones() {
        return acciones;
    }

    public void setAcciones(Cola<Integer> acciones) {
        this.acciones = acciones;
    }

    public ArbolBinario<Individuo> getArbolGenealogico() {
        return arbolGenealogico;
    }

    public void setArbolGenealogico(ArbolBinario<Individuo> arbolGenealogico) {
        this.arbolGenealogico = arbolGenealogico;
    }
}
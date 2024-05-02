package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.Cola;

public class Individuo {
    int ID;

    int Generacion;

    int TurnosVida;

    int ProbRepr;

    int ProbClon;

    int Tipo;

    Cola<Integer> acciones;

    ArbolBinario<Individuo> arbolGenealogico;

    public Individuo(int ID, int generacion, int turnosVida, int probRepr, int probClon, int tipo, Cola<Integer> acciones, ArbolBinario<Individuo> arbolGenealogico) {
        this.ID = ID;
        Generacion = generacion;
        TurnosVida = turnosVida;
        ProbRepr = probRepr;
        ProbClon = probClon;
        Tipo = tipo;
        this.acciones = acciones;
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

    public int getTipo() {
        return Tipo;
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

package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.Cola;
import com.example.NewInterfaz.DatosCompartidos;
import com.example.NewInterfaz.Grafo_Conocimiento.Acción;
import com.example.NewInterfaz.Square;
import com.google.gson.annotations.Expose;

public abstract class Individuo {
    @Expose
    private int ID;

    @Expose
    private int Generacion;

    @Expose
    private int TurnosVida;

    @Expose
    private int ProbRepr;

    @Expose
    private int ProbClon;

    @Expose
    private Cola<Acción> acciones;

    @Expose
    private ArbolBinario<Integer> arbolGenealogico;

    public abstract int getTipo();

    public Individuo(ArbolBinario<Integer> arbolGenealogico) {
        this.ID = DatosCompartidos.getNumIndividuos();
        Generacion = DatosCompartidos.getTurnoJuego();
        TurnosVida = Integer.parseInt(DatosCompartidos.getVidaInicial());
        ProbRepr = Integer.parseInt(DatosCompartidos.getProbReproduccion());
        ProbClon = Integer.parseInt(DatosCompartidos.getProbClonacion());
        this.acciones = new Cola<>();
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

    public Cola<Acción> getAcciones() {
        return acciones;
    }

    public void setAcciones(Cola<Acción> acciones) {
        this.acciones = acciones;
    }

    public ArbolBinario<Integer> getArbolGenealogico() {
        return arbolGenealogico;
    }

    public void setArbolGenealogico(ArbolBinario<Integer> arbolGenealogico) {
        this.arbolGenealogico = arbolGenealogico;
    }
}

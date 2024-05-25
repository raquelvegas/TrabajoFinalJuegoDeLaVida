package com.example.NewInterfaz.Grafo_Conocimiento;

import com.google.gson.annotations.Expose;

public abstract class Accion {
    @Expose
    private int turno;

    public abstract int getTipo();

    public Accion(int turno) {
        this.turno = turno;
    }
}

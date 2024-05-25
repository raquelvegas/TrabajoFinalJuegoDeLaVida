package com.example.NewInterfaz.Grafo_Conocimiento;

public abstract class Accion {
    private int turno;

    public abstract int getTipo();

    public Accion(int turno) {
        this.turno = turno;
    }
}

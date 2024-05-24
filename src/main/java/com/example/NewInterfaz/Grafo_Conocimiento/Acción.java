package com.example.NewInterfaz.Grafo_Conocimiento;

public abstract class Acción {
    private int turno;

    public abstract int getTipo();

    public Acción(int turno) {
        this.turno = turno;
    }
}

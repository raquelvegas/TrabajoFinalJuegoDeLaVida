package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.NewInterfaz.Recurso;

public class Consumición extends Accion {
    private Recurso rec;

    public int getTipo(){
        return 1;
    }
    public Consumición(int turno, Recurso tipoRecurso) {
        super(turno);
        this.rec = tipoRecurso;
    }

    public Recurso getRec() {
        return rec;
    }

    public void setRec(Recurso rec) {
        this.rec = rec;
    }
}

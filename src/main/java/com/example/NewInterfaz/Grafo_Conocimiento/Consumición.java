package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.NewInterfaz.Recurso;

public class Consumición extends Acción{
    private Recurso rec;

    public int getTipo(){
        return 1;
    }
    public Consumición(int turno, Recurso tipoRecurso) {
        super(turno);
        this.rec = tipoRecurso;
    }

}

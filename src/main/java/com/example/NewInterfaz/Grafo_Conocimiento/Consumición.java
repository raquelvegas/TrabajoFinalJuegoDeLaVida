package com.example.NewInterfaz.Grafo_Conocimiento;

import com.example.NewInterfaz.Recurso;
import com.google.gson.annotations.Expose;

public class Consumición extends Accion {
    @Expose
    private Recurso recursoConsumido;

    public int getTipo(){
        return 1;
    }
    public Consumición(int turno, Recurso tipoRecurso) {
        super(turno);
        this.recursoConsumido = tipoRecurso;
    }

    public Recurso getRecursoConsumido() {
        return recursoConsumido;
    }

    public void setRecursoConsumido(Recurso recursoConsumido) {
        this.recursoConsumido = recursoConsumido;
    }
}

package com.example.NewInterfaz.Grafo_Conocimiento;

import com.google.gson.annotations.Expose;

public class Clonación extends Accion {
    @Expose
    private int IdNuevoIndividuo;

    public Clonación(int turno, int idNuevoIndividuo) {
        super(turno);
        IdNuevoIndividuo = idNuevoIndividuo;
    }

    @Override
    public int getTipo() {
        return 3;
    }

    public int getIdNuevoIndividuo() {
        return IdNuevoIndividuo;
    }

    public void setIdNuevoIndividuo(int idNuevoIndividuo) {
        IdNuevoIndividuo = idNuevoIndividuo;
    }
}

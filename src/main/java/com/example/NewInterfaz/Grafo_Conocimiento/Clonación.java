package com.example.NewInterfaz.Grafo_Conocimiento;

public class Clonación extends Acción{
    private int IdNuevoIndividuo;

    public Clonación(int turno, int idNuevoIndividuo) {
        super(turno);
        IdNuevoIndividuo = idNuevoIndividuo;
    }

    @Override
    public int getTipo() {
        return 3;
    }
}

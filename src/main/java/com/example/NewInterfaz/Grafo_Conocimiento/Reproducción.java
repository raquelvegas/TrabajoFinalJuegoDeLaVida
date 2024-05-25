package com.example.NewInterfaz.Grafo_Conocimiento;


public class Reproducción extends Acción{

    private int IdPareja;
    private int IdNuevoIndividuo;

    public Reproducción(int turno, int pareja, int IdNuevo) {
        super(turno);
        this.IdPareja = pareja;
        this.IdNuevoIndividuo = IdNuevo;
    }

    @Override
    public int getTipo() {
        return 2;
    }
}
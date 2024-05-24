package com.example.NewInterfaz.Grafo_Conocimiento;


public class Reproducción extends Acción{

    private int IdPareja;

    public Reproducción(int turno, int pareja) {
        super(turno);
        this.IdPareja = pareja;
    }

    @Override
    public int getTipo() {
        return 2;
    }
}
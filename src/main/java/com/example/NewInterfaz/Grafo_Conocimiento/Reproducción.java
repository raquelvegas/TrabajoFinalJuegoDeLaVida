package com.example.NewInterfaz.Grafo_Conocimiento;


import com.google.gson.annotations.Expose;

public class Reproducción extends Accion {

    @Expose
    private int IdPareja;
    @Expose
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

    public int getIdPareja() {
        return IdPareja;
    }

    public void setIdPareja(int idPareja) {
        IdPareja = idPareja;
    }

    public int getIdNuevoIndividuo() {
        return IdNuevoIndividuo;
    }

    public void setIdNuevoIndividuo(int idNuevoIndividuo) {
        IdNuevoIndividuo = idNuevoIndividuo;
    }
}
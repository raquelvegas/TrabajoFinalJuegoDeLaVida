package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.NewInterfaz.Square;

public class IndNormal extends Individuo{
    Square objetivo = null;
    public IndNormal(ArbolBinario<Individuo> arbolGenealogico) {
        super(arbolGenealogico);
    }

    @Override
    public int getTipo() {
        return 1;
    }

    public Square getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Square objetivo) {
        this.objetivo = objetivo;
    }
}

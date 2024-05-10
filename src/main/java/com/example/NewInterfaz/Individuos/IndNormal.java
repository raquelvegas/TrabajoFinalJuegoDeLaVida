package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;

public class IndNormal extends Individuo{
    public IndNormal(int tipo, ArbolBinario<Individuo> arbolGenealogico) {
        super(tipo, arbolGenealogico);
    }

    @Override
    public int getTipo() {
        return 1;
    }
}

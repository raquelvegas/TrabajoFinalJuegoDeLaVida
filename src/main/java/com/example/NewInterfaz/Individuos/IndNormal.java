package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;

public class IndNormal extends Individuo{
    public IndNormal(ArbolBinario<Individuo> arbolGenealogico) {
        super(arbolGenealogico);
    }

    @Override
    public int getTipo() {
        return 1;
    }
}

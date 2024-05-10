package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;

public class IndAvanzado extends Individuo{
    public IndAvanzado(ArbolBinario<Individuo> arbolGenealogico) {
        super(arbolGenealogico);
    }

    @Override
    public int getTipo() {
        return 2;
    }
}

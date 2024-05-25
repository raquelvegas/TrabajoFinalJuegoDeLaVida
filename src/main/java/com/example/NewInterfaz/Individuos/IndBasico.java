package com.example.NewInterfaz.Individuos;

import com.example.EstructurasDeDatos.ArbolBinario;

public class IndBasico extends Individuo{
    public IndBasico(ArbolBinario<Integer> arbolGenealogico) {
        super(arbolGenealogico);
    }

    @Override
    public int getTipo() {
        return 0;
    }
}

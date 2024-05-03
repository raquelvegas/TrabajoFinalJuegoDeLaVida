package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.scene.layout.GridPane;

public class Game {
    public static Tablero tablero;
    private boolean game;

    public Game(GridPane tablero, String theme){
        this.tablero = new Tablero(tablero, theme);
        this.game = true;
    }

    public void move() {
        ListaSimple<Square> listaRecuadros = this.tablero.getSquares();
        ListaSimple<Integer> listaIdentificadoresIndMovidos = new ListaSimple<>();
        int contador = 0;
        while (contador < listaRecuadros.getNumeroElementos()) {

        }
    }

    private void move(ListaSimple<Square> listaCuadrados, Square cuadrado, ListaSimple<Integer> listaID) {
        Integer contador = 0;
        while (contador < cuadrado.getIndividuos().getNumeroElementos()) {
            Individuo ind = cuadrado.getIndividuos().getDato(contador);
            if (!individuoYaMovido(ind, listaID)) { // Compruebo si el individuo a mover se ha movido ya en este turno o no
                if (ind.getTipo() == 0) {

                }
                listaID.add(ind.getID()); // Se añade el ID del individuo a la lista de identificadores de los individuos que se han movido
            }
        }
    }

    private boolean individuoYaMovido(Individuo ind, ListaSimple<Integer> lista) {
        int contador = 0;
        boolean individuoMovido = false;
        while (contador < lista.getNumeroElementos() && !individuoMovido) {
            if (ind.getID() == lista.getDato(contador)) {
                individuoMovido = true;
            }
            contador++;
        }
        return individuoMovido;
    }

    private int cuadradoEnElBorde(Square cuadrado) { //Codigo numérico en el Readme
        Integer alturaTablero = Integer.valueOf(DatosCompartidos.getAltoMatriz());
        Integer anchuraTablero = Integer.valueOf(DatosCompartidos.getAnchoMatriz());
        boolean cuadradoEnFilaExterior = false;
        boolean cuadradoEnColumnaExterior = false;
        if (cuadrado.getX() == 1 || cuadrado.getX() == anchuraTablero) {
            cuadradoEnColumnaExterior = true;
        }
        if (cuadrado.getY() == 1 || cuadrado.getY() == alturaTablero) {
            cuadradoEnFilaExterior = true;
        }
        if (!cuadradoEnFilaExterior && !cuadradoEnColumnaExterior) {
            return 0;
        } else if (cuadradoEnColumnaExterior && cuadradoEnFilaExterior) {
            return 2;
        } else {
            return 1;
        }
    }



}

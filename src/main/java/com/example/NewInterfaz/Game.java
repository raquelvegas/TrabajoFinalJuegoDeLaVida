package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ListaSimple;
import javafx.scene.layout.GridPane;

import java.util.Random;

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
        while (!cuadrado.getIndividuos().isVacia()) {
            Individuo ind = cuadrado.getIndividuos().getPrimero();
            if (!individuoYaMovido(ind, listaID)) { // Compruebo si el individuo a mover se ha movido ya en este turno o no
                if (ind.getTipo() == 0) {
                    Integer posicionCuadrado = cuadradoEnElBorde(cuadrado);
                    if (posicionCuadrado == 0) {
                        Integer movimiento = generarEnteroAleatorio(1,8);
                        moverindividuo(listaCuadrados,cuadrado,ind,movimiento);
                    }
                }
                cuadrado.getIndividuos().del(0); // Se elimina el individuo del cuadrado en el que estaba
                listaID.add(ind.getID()); // Se añade el ID del individuo a la lista de identificadores de los individuos que se han movido
            }
        }
    }

    // Método para comprobar si el individuo ya se ha movido o no
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


    // Método para especificar la posicion del cuadrado en el que se va a hacer el movimiento.
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


    // Método para generar un número entero aleatorio entre los valores pedidos
    private int generarEnteroAleatorio(int min, int max) {
        Random random = new Random();
        int rango = max - min + 1;
        int numeroAleatorio = random.nextInt(rango) + min;
        return numeroAleatorio;
    }


    // Método para mover individuos básicos localizados en cuadrados NO exteriores
    private void moverindividuo(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind, Integer movimiento){
        if (movimiento==1){

        }
    }

    public Tablero getTablero() {
        return tablero;
    }
}

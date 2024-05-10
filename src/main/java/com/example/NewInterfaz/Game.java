package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.ListaSimple;
import com.example.NewInterfaz.Individuos.Individuo;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Random;

public class Game {
    public static Tablero tablero;
    private boolean game;

    public Game(GridPane tablero){
        this.tablero = new Tablero(tablero, "Agua");
        this.game = true;
        addEventHandlers(this.tablero.tableroJuego);

    }


    private void addEventHandlers(GridPane tablero) {
        tablero.setOnMouseClicked(event -> {
            if(DatosCompartidos.isAnadirTab()) {
                double x = event.getX();
                double y = event.getY();
                for (Node node : tablero.getChildren()) {
                    if (node instanceof Square) {
                        Square square = (Square) node;
                        if (square.getBoundsInParent().contains(x, y)) {
                            int columna = square.getX();
                            int fila = square.getY();
                            handleSquareClick(columna, fila, square);
                            break; // Salir del bucle una vez que se encuentre un cuadrado clicado
                        }
                    }
                }
                actualizarTablero();
            }
        });
    }

    private void handleSquareClick(int columna, int fila, Square square) {
        System.out.println("Clic en el cuadrado " + columna + ", " + fila);
        System.out.println("Elección: "+DatosCompartidos.getAnadir());
        if (DatosCompartidos.getAnadir() == 1) {
            addIndividuo(square);
        } else if (DatosCompartidos.getAnadir() == 2) {
            addRecursos(square, 2);
        } else if (DatosCompartidos.getAnadir() == 3) {
            addRecursos(square, 3);
        } else if (DatosCompartidos.getAnadir() == 4) {
            addRecursos(square, 4);
        } else if (DatosCompartidos.getAnadir() == 5) {
            addRecursos(square, 5);
        } else if (DatosCompartidos.getAnadir() == 6) {
            addRecursos(square, 6);
        } else if (DatosCompartidos.getAnadir() == 7) {
            addRecursos(square, 7);
        } else {
            System.out.println("No has seleccionado el tipo");
        }
    }

    private void addIndividuo(Square square){
        if (square.getIndividuos().getNumeroElementos() < 3) {
            DatosCompartidos.setNumIndividuos(DatosCompartidos.getNumIndividuos()+1);
            int tipo = generarEnteroAleatorio(0, 2); //generar aleatorio de tipo
            Individuo individuoNuevo = new Individuo(tipo, new ArbolBinario<>(null));
            addTipo(square, 1);
            square.getIndividuos().add(individuoNuevo);
            System.out.println("Se ha añadido un individuo con id: " + individuoNuevo.getID());
        }
    }

    private void addRecursos(Square square, int tipo){
        if(square.getRecursos().getNumeroElementos() <3){
            Recurso recursoNuevo = new Recurso(tipo, square);
            int idCeldaAleatoria = addTipo(square, tipo);
            square.getRecursos().add(recursoNuevo);
            recursoNuevo.setCelda(idCeldaAleatoria);
            DatosCompartidos.getListaRecursos().add(recursoNuevo);
            System.out.println("Se ha añadido un recurso tipo: " + recursoNuevo.getTipoRecurso());
        }
    }

    private int addTipo (Square square, Integer tipo){
        int idCeldaAleatoria = celdaAleatoria(square);
        square.getCelda(idCeldaAleatoria).setTipo(tipo);
        square.getCelda(idCeldaAleatoria).setOcupado(true);
        System.out.println("Se ha añadido a la celda" + idCeldaAleatoria);
        return idCeldaAleatoria;
    }

    public void actualizarTablero(){
        int tamañoTablero = tablero.getSquares().getNumeroElementos();
        Integer identificador = 0;
        for (int i = 0; i < tamañoTablero; i++) {
            actualizarSquare(tablero.getSquare(identificador));
            identificador++;
        }
    }

    private void actualizarSquare(Square square){
        Integer identificador = 0;
        for (int i = 0; i < 6; i++) {
            actualizarCelda(square.getCelda(identificador));
            identificador++;
        }
    }

    private void actualizarCelda(Celda celda) {
        if (celda.isOcupado()) {
            switch (celda.getTipo()) {
                case 1: //Individuo
                    celda.setColor(Color.BLACK);
                    break;
                case 2: //Agua
                    celda.setColor(Color.BLUE);
                    break;
                case 3: //Comida
                    celda.setColor(Color.GREEN);
                    break;
                case 4: //Montaña
                    celda.setColor(Color.ORANGE);
                    break;
                case 5: //Biblioteca
                    celda.setColor(Color.BEIGE);
                    break;
                case 6: //Tesoro
                    celda.setColor(Color.GOLD);
                    break;
                case 7: //Pozo
                    celda.setColor(Color.RED);
                    break;
            }
        } else {
            celda.setColor(Color.TRANSPARENT);
        }
    }

    public void clearTablero(Tablero tablero){
        int tamañoTablero = tablero.getSquares().getNumeroElementos();
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j<6; j++){
                tablero.getSquare(i).getCelda(j).setTipo(0);
                tablero.getSquare(i).getCelda(j).setOcupado(false); // asegurarse de marcar la celda como no ocupada
            }
            tablero.getSquare(i).setIndividuos(new ListaSimple<Individuo>());
            tablero.getSquare(i).setRecursos(new ListaSimple<Recurso>());
        }
    }

    public void crearTableroAleatorio(){
        Integer numeroCuadrados = tablero.getSquares().getNumeroElementos();
        if (numeroCuadrados == 1) {
            addIndividuo(tablero.getSquare(0));
            int tipoRecurso = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(0), tipoRecurso);
        } else if (numeroCuadrados == 2) {
            int numerocuadrado = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado));
            int numerocuadrado2 = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado2));
            int tipoRecurso = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), tipoRecurso);
            int tipoRecurso2 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado2), tipoRecurso2);
            int tipoRecurso3 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), tipoRecurso3);
        } else {
            int numIndividuos = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 2);
            int numAgua = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numComida = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numMontana = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numBiblioteca = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numTesoro = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            int numPozo = generarEnteroAleatorio(numeroCuadrados / 5, numeroCuadrados / 5 + numeroCuadrados / 3);
            for (int i = 0; i < numIndividuos; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addIndividuo(tablero.getSquare(idSquareAleatorio));
            }
            for (int i = 0; i < numAgua; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 2);
            }
            for (int i = 0; i < numComida; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 3);
            }
            for (int i = 0; i < numMontana; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 4);
            }
            for (int i = 0; i < numBiblioteca; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 5);
            }
            for (int i = 0; i < numTesoro; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 6);
            }
            for (int i = 0; i < numPozo; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 7);
            }
        }
    }

    private int celdaAleatoria(Square square){
        Integer identificador = 0;
        ListaSimple<Celda> listaCeldasLibres = new ListaSimple<Celda>();
        for (int i = 0; i < 6; i++) {
            if(!square.getCelda(identificador).isOcupado()){
                listaCeldasLibres.add(square.getCelda(identificador));
            }
            identificador++;
        }
        int aleatorio = generarEnteroAleatorio(0,listaCeldasLibres.getNumeroElementos()-1);
        int celdaIdentificador = listaCeldasLibres.getDato(aleatorio).getID();
        return celdaIdentificador;
    }

    private void actualizarIndividuos(){
        int numIndividuos = DatosCompartidos.getListaIndividuos().getNumeroElementos();
        int tamanoTablero = tablero.getSquares().getNumeroElementos();
        ListaSimple<Individuo> listaDel = new ListaSimple<Individuo>();
        for (int i = 0; i < numIndividuos; i++){
            Square squareRecurso = DatosCompartidos.getListaRecursos().getDato(i).getSquare();
            Recurso recurso = DatosCompartidos.getListaRecursos().getDato(i);
            for (int j = 0; j < tamanoTablero; j++) {
                if(squareRecurso == tablero.getSquare(i)){
                    for (int m = 0; m < 3; m++){
                        if(tablero.getSquare(i).getRecursos().getDato(m) == recurso){
                            if(recurso.getTiempoVida() == 0){
                                int celda = recurso.getCelda();
                                tablero.getSquare(i).getCelda(celda).setTipo(0);
                                tablero.getSquare(i).getCelda(celda).setOcupado(false);
                                listaDel.add(recurso);
                            }
                        }
                    }
                }
            }
        }
        eliminarRecursos(listaDel);
    }

    private void actualizarRecursos(){
        int numRecursos = DatosCompartidos.getListaRecursos().getNumeroElementos();
        int tamanoTablero = tablero.getSquares().getNumeroElementos();
        ListaSimple<Recurso> listaDel = new ListaSimple<Recurso>();
        for (int i = 0; i < numRecursos; i++){
            Square squareRecurso = DatosCompartidos.getListaRecursos().getDato(i).getSquare();
            Recurso recurso = DatosCompartidos.getListaRecursos().getDato(i);
            for (int j = 0; j < tamanoTablero; j++) {
                if(squareRecurso == tablero.getSquare(i)){
                    for (int m = 0; m < 3; m++){
                        if(tablero.getSquare(i).getRecursos().getDato(m) == recurso){
                            if(recurso.getTiempoVida() == 0){
                                int celda = recurso.getCelda();
                                tablero.getSquare(i).getCelda(celda).setTipo(0);
                                tablero.getSquare(i).getCelda(celda).setOcupado(false);
                                listaDel.add(recurso);
                            }
                        }
                    }
                }
            }
        }
        eliminarRecursos(listaDel);
    }

    private void eliminarRecursos(ListaSimple<Recurso> listaDel){
        int numRecursosDel = DatosCompartidos.getListaRecursos().getNumeroElementos();
        int numRecursos = DatosCompartidos.getListaRecursos().getNumeroElementos();
        for (int i = 0; i < numRecursosDel; i++){
            for (int j = 0; j < numRecursos; j++){
                if(listaDel.getDato(i) == DatosCompartidos.getListaRecursos().getDato(j)){
                    DatosCompartidos.getListaRecursos().del(j);
                }
            }
        }
    }

    ////////////// MOVIMIENTO DE INDIVIDUOS //////////////////

    public void moverIndividuos() {
        ListaSimple<Integer> listaIdentificadoresIndMovidos = new ListaSimple<>();
        int contador = 0;
        while (contador < tablero.getSquares().getNumeroElementos()) {
            moverIndividuosCuadrado(tablero.getSquares(), tablero.getSquares().getDato(contador), listaIdentificadoresIndMovidos);
            contador++;
        }
    }

    private void moverIndividuosCuadrado(ListaSimple<Square> listaCuadrados, Square cuadrado, ListaSimple<Integer> listaID) {
        ListaSimple<Individuo> individuosMovidosCuadrado = new ListaSimple<>(); // Se crea una lista donde se van a meter los individuos que esten en el cuadrado que ya se hayan movido
        while (!cuadrado.getIndividuos().isVacia()) {
            Individuo ind = cuadrado.getIndividuos().getPrimero();
            if (!individuoYaMovido(ind, listaID)) {   // Compruebo si el individuo a mover se ha movido ya en este turno o no

                if (ind.getTipo() == 0) {   // Tipo Básico
                    int posicionCuadrado = posicionCuadrado(cuadrado);
                    if (posicionCuadrado == 0) {
                        Integer movimiento = generarEnteroAleatorio(1, 8);   // Se genera el movimiento a realizar (véase el código numérico en README)
                        moverIndBasicoCuadradoInterior(listaCuadrados, cuadrado, ind, movimiento);   // Se añade el individuo a la lista de individuos del nuevo cuadrado
                    } else if ((posicionCuadrado == 1) || (posicionCuadrado == 3) || (posicionCuadrado == 5) || (posicionCuadrado == 7)) {
                        Integer movimiento = generarEnteroAleatorio(1, 3);
                        moverIndBasicoCuadradoEsquina(listaCuadrados, cuadrado, ind, movimiento, posicionCuadrado);
                    } else {
                        Integer movimiento = generarEnteroAleatorio(1, 5);
                        moverIndBasicoCuadradoBorde(listaCuadrados, cuadrado, ind, movimiento, posicionCuadrado);
                    }
                } else if (ind.getTipo() == 1) {   // Tipo Normal
                    moverIndNormal(listaCuadrados, cuadrado, ind);

                } else {   // Tipo Avanzado

                    // FALTA ESTO

                }
                listaID.add(ind.getID()); // Se añade el ID del individuo a la lista de identificadores de los individuos que se han movido
            } else {
                individuosMovidosCuadrado.add(ind); // añado el individuo a la lista de individuos que ya se habían movido
            }
            cuadrado.getIndividuos().del(0);// Se elimina el individuo del cuadrado en el que estaba
        }

        // Volvemos a añadir a la lista los individuos que ya se habían movido
        int contador = 0;
        while (contador < individuosMovidosCuadrado.getNumeroElementos()) {
            cuadrado.getIndividuos().add(individuosMovidosCuadrado.getDato(contador));
            contador++;
        }

    }


    // Método para mover un individuo normal en un cuadrado interior
    private void moverIndNormal(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind) {

        // Primero nos vamos a construir listas con los cuadrados en las cuatro direcciones del plano hasta que haya uno con un recurso o hallamos llegado al borde de la matriz
        boolean ERecDerecha = false;
        ListaSimple<Square> cuadradosDerecha = new ListaSimple<>();
        int identificador = cuadrado.getID() + Integer.parseInt(DatosCompartidos.getAltoMatriz());
        while ((identificador <= listaCuadrados.getNumeroElementos()) && (!ERecDerecha)) {
            cuadradosDerecha.add(listaCuadrados.getDato(identificador));
            if (!listaCuadrados.getDato(identificador).getRecursos().isVacia()) {
                ERecDerecha = true;
            } else {
                identificador += Integer.parseInt(DatosCompartidos.getAltoMatriz());
            }
        }

        boolean ERecIzquierda = false;
        ListaSimple<Square> cuadradosIzquierda = new ListaSimple<>();
        int identificador2 = cuadrado.getID() - Integer.parseInt(DatosCompartidos.getAltoMatriz());
        while ((identificador2 <= 0) && (!ERecIzquierda)) {
            cuadradosIzquierda.add(listaCuadrados.getDato(identificador2));
            if (!listaCuadrados.getDato(identificador2).getRecursos().isVacia()) {
                ERecIzquierda = true;
            } else {
                identificador2 -= Integer.parseInt(DatosCompartidos.getAltoMatriz());
            }
        }

        boolean ERecArriba = false;
        ListaSimple<Square> cuadradosArriba = new ListaSimple<>();
        int identificador3 = cuadrado.getID() - 1;
        while ((identificador3 % Integer.parseInt(DatosCompartidos.getAltoMatriz()) != Integer.parseInt(DatosCompartidos.getAltoMatriz()) - 1) && (!ERecArriba)) {
            cuadradosArriba.add(listaCuadrados.getDato(identificador3));
            if (!listaCuadrados.getDato(identificador3).getRecursos().isVacia()) {
                ERecArriba = true;
            } else {
                identificador3--;
            }
        }

        boolean ERecAbajo = false;
        ListaSimple<Square> cuadradosAbajo = new ListaSimple<>();
        int identificador4 = cuadrado.getID() + 1;
        while ((identificador4 % Integer.parseInt(DatosCompartidos.getAltoMatriz()) != 0) && (!ERecAbajo)) {
            cuadradosAbajo.add(listaCuadrados.getDato(identificador4));
            if (!listaCuadrados.getDato(identificador4).getRecursos().isVacia()) {
                ERecAbajo = true;
            } else {
                identificador4++;
            }
        }


        // Una vez hechas las listas, vamos a dividir el problema en casos (dependiendo de el elemento final de cada lista es un cuadrado del borde de la matriz o es un cuadrado con un recurso)
        if (ERecArriba) {
            if (ERecAbajo) {
                if (ERecDerecha) {
                    if (ERecIzquierda) { // 4 direcciones: Arr, abj, izq, dch

                    } else { // 3 direcciones: Arr, abj, dch
                    }
                } else {
                    if (ERecIzquierda) { // 3 direcciones: Arr, abj, izq

                    } else { // 2 direcciones: Arr, abj
                        moverNormal2Listas(cuadradosArriba, cuadradosAbajo, ind);
                    }
                }
            } else {
                if (ERecDerecha) {
                    if (ERecIzquierda) { // 3 direcciones: Arr, izq, dch

                    } else { // 2 direcciones: Arr, dch
                        moverNormal2Listas(cuadradosArriba, cuadradosDerecha, ind);
                    }
                } else {
                    if (ERecIzquierda) { // 2 direcciones: Arr, izq
                        moverNormal2Listas(cuadradosArriba, cuadradosIzquierda, ind);
                    } else { // 1 direcciones: Arr
                        cuadradosArriba.getPrimero().getIndividuos().add(ind);
                    }
                }
            }
        } else {
            if (ERecAbajo) {
                if (ERecDerecha) {
                    if (ERecIzquierda) { // 3 direcciones: Abj, izq, dch

                    } else { // 2 direcciones: Abj, dch
                        moverNormal2Listas(cuadradosAbajo, cuadradosDerecha, ind);
                    }
                } else {
                    if (ERecIzquierda) { // 2 direcciones: Abj, izq
                        moverNormal2Listas(cuadradosAbajo, cuadradosIzquierda, ind);
                    } else { // 1 direcciones: Abj
                        cuadradosAbajo.getPrimero().getIndividuos().add(ind);
                    }
                }
            } else {
                if (ERecDerecha) {
                    if (ERecIzquierda) { // 2 direcciones: Izq, dch
                        moverNormal2Listas(cuadradosIzquierda, cuadradosDerecha, ind);
                    } else { // 1 direcciones: Dch
                        cuadradosDerecha.getPrimero().getIndividuos().add(ind);
                    }
                } else {
                    if (ERecIzquierda) { // 1 direcciones: Izq
                        cuadradosIzquierda.getPrimero().getIndividuos().add(ind);
                    } else { // 0 direcciones

                    }
                }
            }
        }
    }

    private void moverNormal2Listas(ListaSimple<Square> lista1, ListaSimple<Square> lista2, Individuo ind) {
        int comparacion = lista1.getNumeroElementos().compareTo(lista2.getNumeroElementos());
        if (comparacion > 0) {
            lista2.getPrimero().getIndividuos().add(ind);
        } else if (comparacion < 0) {
            lista1.getPrimero().getIndividuos().add(ind);
        } else {
            int numerorandom = generarEnteroAleatorio(0, 1);
            if (numerorandom == 1) {
                lista2.getPrimero().getIndividuos().add(ind);
            } else {
                lista1.getPrimero().getIndividuos().add(ind);
            }
        }
    }

    private void moverNormal3Listas(ListaSimple<Square> lista1, ListaSimple<Square> lista2, ListaSimple<Square> lista3, Individuo ind) {
        int comparacion1_2 = lista1.getNumeroElementos().compareTo(lista2.getNumeroElementos());
        int comparacion1_3 = lista1.getNumeroElementos().compareTo(lista3.getNumeroElementos());
        int comparacion2_3 = lista2.getNumeroElementos().compareTo(lista3.getNumeroElementos());
        if (comparacion1_2 < 0) {
            if (comparacion1_3 < 0) { // lista1

            }
            if (comparacion1_3 > 0) {
                if (comparacion2_3>0){

                }
            }
        } else if (comparacion1_2 > 0) {

        } else {

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
    private int posicionCuadrado(Square cuadrado) { //Codigo numérico en el Readme
        Integer alturaTablero = Integer.valueOf(DatosCompartidos.getAltoMatriz());
        Integer anchuraTablero = Integer.valueOf(DatosCompartidos.getAnchoMatriz());
        if (cuadrado.getX() == 0) {
            if (cuadrado.getY() == 0) {
                return 1; // Esquina superior izquierda
            } else if (cuadrado.getY() == alturaTablero - 1) {
                return 7; // Esquina inferior izquierda
            } else {
                return 8; // Columna izquierda
            }
        } else if (cuadrado.getX() == anchuraTablero - 1) {
            if (cuadrado.getY() == 0) {
                return 3; // Esquina superior derecha
            }
            if (cuadrado.getY() == alturaTablero - 1) {
                return 5; // Esquina inferior derecha
            } else {
                return 4; //Columna derecha
            }
        } else {
            if (cuadrado.getY() == 0) {
                return 2; // Fila superior
            }
            if (cuadrado.getY() == alturaTablero - 1) {
                return 6; // Fila inferior
            } else {
                return 0; // Cuadrado interior
            }
        }
    }


    // Método para generar un número entero aleatorio entre los valores pedidos
    private int generarEnteroAleatorio(int min, int max) {
        Random random = new Random();
        int rango = max - min + 1;
        int numeroAleatorio = random.nextInt(rango) + min;
        return numeroAleatorio;
    }


    // Método para mover individuos básicos localizados en cuadrados interiores
    private void moverIndBasicoCuadradoInterior(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind, Integer movimiento) {
        int contador = 0;
        Square nuevoCuadrado = null;
        if (movimiento == 1) {  // Arriba-Izquierda
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 2) {  // Arriba
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 3) {  // Arriba-Derecha
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 4) {  // Derecha
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 5) {  // Abajo-Derecha
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 6) {  // Abajo
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 7) {  //Abajo-Izquierda
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        } else if (movimiento == 8) {  // Izquierda
            while (nuevoCuadrado == null) {
                if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                    nuevoCuadrado = listaCuadrados.getDato(contador);
                } else {
                    contador++;
                }
            }
        }
        nuevoCuadrado.getIndividuos().add(ind);
    }


    // Método para mover individuos básicos localizados en esquinas
    private void moverIndBasicoCuadradoEsquina(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind, Integer movimiento, int esquina) {
        if (esquina == 1) {
            Square nuevoCuadrado = null;
            int contador = 0;
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
            nuevoCuadrado.getIndividuos().add(ind);
        } else if (esquina == 3) {
            Square nuevoCuadrado = null;
            int contador = 0;
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
            nuevoCuadrado.getIndividuos().add(ind);
        } else if (esquina == 5) {
            Square nuevoCuadrado = null;
            int contador = 0;
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
            nuevoCuadrado.getIndividuos().add(ind);
        } else {
            Square nuevoCuadrado = null;
            int contador = 0;
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
            nuevoCuadrado.getIndividuos().add(ind);
        }
    }


    // Método para mover individuos básicos localizados en bordes (no esquinas)
    private void moverIndBasicoCuadradoBorde(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind, Integer movimiento, int borde) {
        Square nuevoCuadrado = null;
        int contador = 0;
        if (borde == 2) {
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 3) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 4) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 5) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
        } else if (borde == 4) {
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()-1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY()-1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 3) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY()+1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 4) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() -1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() -1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 5) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
        } else if (borde == 6) {
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 3) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 4) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 5) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() - 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
        } else {
            if (movimiento == 1) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()+1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY())) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 2) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY()-1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 3) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX()) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY()+1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 4) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() - 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            } else if (movimiento == 5) {
                while (nuevoCuadrado == null) {
                    if ((listaCuadrados.getDato(contador).getX() == cuadrado.getX() + 1) && (listaCuadrados.getDato(contador).getY() == cuadrado.getY() + 1)) {
                        nuevoCuadrado = listaCuadrados.getDato(contador);
                    } else {
                        contador++;
                    }
                }
            }
        }
        nuevoCuadrado.getIndividuos().add(ind);
    }

    public static Tablero getTablero() {
        return tablero;
    }
}

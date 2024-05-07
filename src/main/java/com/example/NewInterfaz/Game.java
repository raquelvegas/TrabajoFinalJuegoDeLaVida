package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.ListaSimple;
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
                actualizarTablero(this.getTablero());
            }
        });
    }

    private void handleSquareClick(int columna, int fila, Square square) {
        System.out.println("Clic en el cuadrado " + columna + ", " + fila);
        System.out.println("elección es: "+DatosCompartidos.getAnadir());
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
        if(square.getIndividuos().getNumeroElementos() <3){
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
            Recurso recursoNuevo = new Recurso(tipo);
            addTipo(square, tipo);
            square.getRecursos().add(recursoNuevo);
            System.out.println("Se ha añadido un recurso tipo: " + recursoNuevo.getTipoRecurso());
        }
    }

    private void addTipo (Square square, Integer tipo){
        int idCeldaAleatoria = celdaAleatoria(square);
        square.getCelda(idCeldaAleatoria).setTipo(tipo);
        square.getCelda(idCeldaAleatoria).setOcupado(true);
        System.out.println("Se ha añadido a la celda" + idCeldaAleatoria);

    }

    public void actualizarTablero(Tablero tablero){
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
        int numIndividuos = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        int numAgua = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        int numComida = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        int numMontana = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        int numBiblioteca = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        int numTesoro = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        int numPozo = generarEnteroAleatorio(numeroCuadrados/4,numeroCuadrados/4+numeroCuadrados/2);
        for (int i = 0; i < numIndividuos; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addIndividuo(tablero.getSquare(idSquareAleatorio));
        }
        for (int i = 0; i < numAgua; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addRecursos(tablero.getSquare(idSquareAleatorio),2);
        }
        for (int i = 0; i < numComida; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addRecursos(tablero.getSquare(idSquareAleatorio),3);
        }
        for (int i = 0; i < numMontana; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addRecursos(tablero.getSquare(idSquareAleatorio),4);
        }
        for (int i = 0; i < numBiblioteca; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addRecursos(tablero.getSquare(idSquareAleatorio),5);
        }
        for (int i = 0; i < numTesoro; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addRecursos(tablero.getSquare(idSquareAleatorio),6);
        }
        for (int i = 0; i < numPozo; i++){
            int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados);
            addRecursos(tablero.getSquare(idSquareAleatorio),7);
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

                }
                if (ind.getTipo() == 1) {   // Tipo Normal
                    int posicionCuadrado = posicionCuadrado(cuadrado);
                    if (posicionCuadrado == 0) {
                        moverIndNormal(listaCuadrados, cuadrado, ind);
                    }
                } else {   // Tipo Avanzado

                    // FALTA ESTO

                }
                cuadrado.getIndividuos().del(0); // Se elimina el individuo del cuadrado en el que estaba
                listaID.add(ind.getID()); // Se añade el ID del individuo a la lista de identificadores de los individuos que se han movido
            }
        }
    }


    // Método para mover un individuo normal en un cuadrado interior
    private void moverIndNormal(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind) {
        ListaSimple<Square> cuadradosTotales = getTablero().getSquares();
        Integer distanciaRecursoDerecha = Integer.valueOf(DatosCompartidos.getAnchoMatriz());
        boolean finFila = false;
        Square cuadradoActual = cuadrado;
        Integer contadorDerecha = 0;
        Integer contador = 0;
        while ((!finFila) && (cuadradoActual != null)) {
            if (cuadradosTotales.getDato(contador).getY() == cuadradoActual.getY() + 1 && cuadradosTotales.getDato(contador).getX() == cuadradoActual.getX()) {


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
        } else if (borde==4){
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
        } else if (borde==6){
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

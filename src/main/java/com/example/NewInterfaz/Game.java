package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.NewInterfaz.Individuos.IndNormal;
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
            } else if(DatosCompartidos.isContenidoCeldaTab()) {
                double x = event.getX();
                double y = event.getY();
                for (Node node : tablero.getChildren()) {
                    if (node instanceof Square) {
                        Square square = (Square) node;
                        if (square.getBoundsInParent().contains(x, y)) {
                            int columna = square.getX();
                            int fila = square.getY();
                            handleCeldaClick(columna, fila, square);
                            break; // Salir del bucle una vez que se encuentre un cuadrado clicado
                        }
                    }
                }
                actualizarTablero();
            }
        });
    }

    private void handleCeldaClick(int columna, int fila, Square square) {
        for (int i = 0; i < 6; i++ ){

        }
    }


    private void handleSquareClick(int columna, int fila, Square square) {
        System.out.println("Clic en el cuadrado " + columna + ", " + fila);
        System.out.println("Elección: "+DatosCompartidos.getAnadir());
        if (DatosCompartidos.getAnadir() == 1) {
            addIndividuo(square);
        } else if (DatosCompartidos.getAnadir() == 2) {
            addRecursos(square, 2.0);
        } else if (DatosCompartidos.getAnadir() == 3) {
            addRecursos(square, 3.0);
        } else if (DatosCompartidos.getAnadir() == 4) {
            addRecursos(square, 4.0);
        } else if (DatosCompartidos.getAnadir() == 5) {
            addRecursos(square, 5.0);
        } else if (DatosCompartidos.getAnadir() == 6) {
            addRecursos(square, 6.0);
        } else if (DatosCompartidos.getAnadir() == 7) {
            addRecursos(square, 7.0);
        } else {
            System.out.println("No has seleccionado el tipo");
        }
    }


    private void addIndividuo(Square square){
        if (square.getIndividuos().getNumeroElementos() < 3) {
            Individuo individuoNuevo;
            DatosCompartidos.setNumIndividuos(DatosCompartidos.getNumIndividuos()+1);
            int tipo = generarEnteroAleatorio(0, 2); // Generar aleatorio de tipo
//            if (tipo == 0) {
//                individuoNuevo = new IndBasico(new ArbolBinario<>(null));
//                Double tipoIndividuo = 1.1;
//            } else if (tipo == 1) {
                individuoNuevo = new IndNormal(new ArbolBinario<>(null));
                Double tipoIndividuo = 1.2;
//            } else {
//                individuoNuevo = new IndAvanzado(new ArbolBinario<>(null));
//            Double tipoIndividuo = 1.3;
//            }
            addTipo(square, tipoIndividuo); // Añado una celda de tipo 1 al square donde se añade el individuo
            square.getIndividuos().add(individuoNuevo);
            System.out.println("Se ha añadido un individuo con id: " + individuoNuevo.getID());
        }
    }


    // Metes el cuadrado y el tipo de recurso que quieres añadir y te lo añade
    private void addRecursos(Square square, Double tipo){
        if(square.getRecursos().getNumeroElementos() <3){
            Recurso recursoNuevo = new Recurso(tipo, square);
            int idCeldaAleatoria = addTipo(square, tipo);
            square.getRecursos().add(recursoNuevo);
            recursoNuevo.setCelda(idCeldaAleatoria);
            DatosCompartidos.getListaRecursos().add(recursoNuevo);
            System.out.println("Se ha añadido un recurso tipo: " + recursoNuevo.getTipoRecurso());
        }
    }


    // Mete en una celda aleatoria el tipo de individuo/recurso que haya
    private int addTipo (Square square, Double tipo){
        int idCeldaAleatoria = celdaAleatoria(square);
        square.getCelda(idCeldaAleatoria).setTipo(tipo);
        square.getCelda(idCeldaAleatoria).setOcupado(true);
        System.out.println("Se ha añadido a la celda" + idCeldaAleatoria);
        return idCeldaAleatoria;
    }


    // Actualiza todos los cuadrados del tablero
    public void actualizarTablero(){
        int tamañoTablero = tablero.getSquares().getNumeroElementos();
        Integer identificador = 0;
        for (int i = 0; i < tamañoTablero; i++) {
            actualizarSquare(tablero.getSquare(identificador));
            identificador++;
        }
    }


    // Actualiza todas las celdas del square
    private void actualizarSquare(Square square){
        Integer identificador = 0;
        for (int i = 0; i < 6; i++) {
            actualizarCelda(square.getCelda(identificador));
            identificador++;
        }
    }


    // Según el tipo de cada celda poner el color correspondiente
    private void actualizarCelda(Celda celda) {
        if (celda.isOcupado()) {
            double tipo = celda.getTipo();
            int tipoEntero = (int) tipo;
            int subtipo = (int) ((tipo - 1)*10+1);

            switch (tipoEntero) {
                case 1: //Individuo
                    switch (subtipo) {
                        case 1: // Tipo 1.1
                            // Código para el tipo 1.1
                            celda.setColor(Color.web("#212121"));
                            break;
                        case 2: // Tipo 1.2
                            // Código para el tipo 1.2
                            celda.setColor(Color.web("#616161"));
                            break;
                        case 3: // Tipo 1.3
                            // Código para el tipo 1.3
                            celda.setColor(Color.web("#9e9e9e"));
                            break;
                    }
                    break;
                case 2: //Agua - Azul
                    celda.setColor(Color.web("#0404e2"));
                    break;
                case 3: //Comida - Verde
                    celda.setColor(Color.web("#00AD43"));
                    break;
                case 4: //Montaña - Marrón
                    celda.setColor(Color.web("#864332"));
                    break;
                case 5: //Biblioteca - Naranja
                    celda.setColor(Color.web("#fc4b08"));
                    break;
                case 6: //Tesoro - Amarillo
                    celda.setColor(Color.web("#ffd700"));
                    break;
                case 7: //Pozo - Rojo
                    celda.setColor(Color.web("#ff0000"));
                    break;
            }
        } else {
            celda.setColor(Color.TRANSPARENT);
        }
    }


    // Eliminar todo lo que hay en el tablero (Boton borrartablero)
    public void clearTablero(Tablero tablero){
        int tamañoTablero = tablero.getSquares().getNumeroElementos();
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j<6; j++){
                tablero.getSquare(i).getCelda(j).setTipo(0.0);
                tablero.getSquare(i).getCelda(j).setOcupado(false); // asegurarse de marcar la celda como no ocupada
            }
            tablero.getSquare(i).setIndividuos(new ListaSimple<>());
            tablero.getSquare(i).setRecursos(new ListaSimple<>());
        }
    }


    // Generar un tablero aleatorio
    public void crearTableroAleatorio(){
        Integer numeroCuadrados = tablero.getSquares().getNumeroElementos();
        if (numeroCuadrados == 1) {
            addIndividuo(tablero.getSquare(0));
            int tipoRecurso = generarEnteroAleatorio((int)2.0, (int)7.0);
            addRecursos(tablero.getSquare(0), (double) tipoRecurso);
        } else if (numeroCuadrados == 2) {
            int numerocuadrado = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado));
            int numerocuadrado2 = generarEnteroAleatorio(0, 1);
            addIndividuo(tablero.getSquare(numerocuadrado2));
            int tipoRecurso = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), (double) tipoRecurso);
            int tipoRecurso2 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado2), (double) tipoRecurso2);
            int tipoRecurso3 = generarEnteroAleatorio(2, 7);
            addRecursos(tablero.getSquare(numerocuadrado), (double) tipoRecurso3);
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
                addRecursos(tablero.getSquare(idSquareAleatorio), 2.0);
            }
            for (int i = 0; i < numComida; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 3.0);
            }
            for (int i = 0; i < numMontana; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 4.0);
            }
            for (int i = 0; i < numBiblioteca; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 5.0);
            }
            for (int i = 0; i < numTesoro; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 6.0);
            }
            for (int i = 0; i < numPozo; i++) {
                int idSquareAleatorio = generarEnteroAleatorio(0, numeroCuadrados - 1);
                addRecursos(tablero.getSquare(idSquareAleatorio), 7.0);
            }
        }
    }


    // Devuelve una celda aleatoria de las que NO estan ocupadas
    private int celdaAleatoria(Square square){
        Integer identificador = 0;
        ListaSimple<Celda> listaCeldasLibres = new ListaSimple<>();
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


    // Mira la lista de individuos y pone celdas de tipo 1 para cada individuo en la lista
    public void actualizarIndividuos(){
        clearIndividuos();
        int tamanoTablero = tablero.getSquares().getNumeroElementos();
        for (int i = 0; i < tamanoTablero; i++) {
            int numIndividuos = tablero.getSquare(i).getIndividuos().getNumeroElementos();
            for (int j = 0; j < numIndividuos; j++){
                int tipoIndividuo = tablero.getSquare(i).getIndividuos().getDato(j).getTipo();
                if (tipoIndividuo == 0){
                    addTipo(tablero.getSquare(i), 1.1);
                } else if (tipoIndividuo == 1) {
                    addTipo(tablero.getSquare(i), 1.2);
                } else if (tipoIndividuo == 2) {
                    addTipo(tablero.getSquare(i), 1.3);
                }

            }
        }
    }


    // Poner las celdas en las que había individuos transparentes (porque todos los individuos se mueven)
    private void clearIndividuos(){
        int tamanoTablero = tablero.getSquares().getNumeroElementos();
        for (int i = 0; i < tamanoTablero; i++) {
            for (int j = 0; j < 6; j++) {
                Double tipo = tablero.getSquare(i).getCelda(j).getTipo();
                if(tipo == 1.1 || tipo == 1.2 || tipo == 1.3){
                    tablero.getSquare(i).getCelda(j).setTipo(0.0);
                    tablero.getSquare(i).getCelda(j).setOcupado(false);
                    System.out.println("ELIMINADO");
                }
            }
        }
    }


    // Comprobar si los recursos tienen tiempo de vida y sino eliminarlos
    public void actualizarRecursos(){
        int numRecursos = DatosCompartidos.getListaRecursos().getNumeroElementos();
        ListaSimple<Recurso> listaDel = new ListaSimple<>();

        for (int i = 0; i < numRecursos; i++) {
            Recurso recurso = DatosCompartidos.getListaRecursos().getDato(i);
            if (recurso.getTiempoVida() == 0){
                int idSquareRecurso = DatosCompartidos.getListaRecursos().getDato(i).getSquare().getID();
                int idCeldaRecurso = DatosCompartidos.getListaRecursos().getDato(i).getCelda();
                tablero.getSquare(idSquareRecurso).getCelda(idCeldaRecurso).setTipo(0.0);
                tablero.getSquare(idSquareRecurso).getCelda(idCeldaRecurso).setOcupado(false);
                for (int j = 0; j < 3; j++) {
                    if (tablero.getSquare(idSquareRecurso).getRecursos().getDato(j)==recurso){
                        tablero.getSquare(idSquareRecurso).getRecursos().del(j);
                    }
                }
                listaDel.add(recurso);
            }
        }
        eliminarRecursos(listaDel);
    }


    // Eliminar los recursos que no tienen tiempo de vida de la lista de DatosCompartidos
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


    // Reducir los turnos de vida tanto de recursos como de individuos
    public void actualizarVidas(){
        int numIndividuos = DatosCompartidos.getListaIndividuos().getNumeroElementos();
        int numRecursos = DatosCompartidos.getListaRecursos().getNumeroElementos();

        for(int i = 0; i < numIndividuos; i++){
            int vida = DatosCompartidos.getListaIndividuos().getDato(i).getTurnosVida();
            DatosCompartidos.getListaIndividuos().getDato(i).setTurnosVida(vida-1);
        }
        for(int i = 0; i < numRecursos; i++){
            int vida = DatosCompartidos.getListaRecursos().getDato(i).getTiempoVida();
            DatosCompartidos.getListaRecursos().getDato(i).setTiempoVida(vida-1);
        }
    }


    ////////////// MOVIMIENTO DE INDIVIDUOS //////////////////

    // Método principal para el movimiento de los individuos
    public void moverIndividuos() {
        ListaSimple<Integer> listaIdentificadoresIndMovidos = new ListaSimple<>();
        int contador = 0;
        while (contador < tablero.getSquares().getNumeroElementos()) {
            moverIndividuosCuadrado(tablero.getSquares(), tablero.getSquares().getDato(contador), listaIdentificadoresIndMovidos);
            contador++;
        }
    }


    // Método cuadrado por cuadrado
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
                } else if (ind.getTipo() == 1) { // Tipo Normal
                    moverIndNormal(listaCuadrados, cuadrado, ind);
                } else {   // Tipo Avanzado

                    // FALTA ESTO

                }
                listaID.add(ind.getID()); // Se añade el ID del individuo a la lista de identificadores de los individuos que se han movido
            } else {
                individuosMovidosCuadrado.add(ind); // añado el individuo a la lista de individuos que ya se habían movido
            }
            cuadrado.getIndividuos().del(0); // Se elimina el individuo del cuadrado en el que estaba
        }

        // Volvemos a añadir a la lista los individuos que ya se habían movido
        int contador = 0;
        while (contador < individuosMovidosCuadrado.getNumeroElementos()) {
            cuadrado.getIndividuos().add(individuosMovidosCuadrado.getDato(contador));
            contador++;
        }

    }


    // Método principal para mover un individuo normal
    private void moverIndNormal(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind) {
        IndNormal indNormal = (IndNormal) ind;
        if (!DatosCompartidos.getListaRecursos().isVacia()) {
            if (indNormal.getRecorrido().isVacia()) {
                buscarNuevoObjetivo(listaCuadrados, cuadrado, indNormal);
            }
            moverIndNormalDirigido(indNormal.getRecorrido(), indNormal);
        } else {
            if (!indNormal.getRecorrido().isVacia()) { // Aunque no existan ya recursos, si el individuo tenía fijado un objetivo, llegará hasta él
                moverIndNormalDirigido(indNormal.getRecorrido(), indNormal);
            } else { // Si no hay más recursos y el individuo no tenía ningún objetivo fijado, se moverá como un individuo básico
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
        }
    }


    // Método para buscar un objetivo random para un individuo normal
    private void buscarNuevoObjetivo(ListaSimple<Square> listaCuadrados, Square cuadrado, IndNormal ind) {
        int recursoRandom = generarEnteroAleatorio(0, DatosCompartidos.getListaRecursos().getNumeroElementos() - 1);
        Recurso objetivo = DatosCompartidos.getListaRecursos().getDato(recursoRandom);
        ListaEnlazada<Square> listaRecorrido = new ListaEnlazada<>();
        int coordXObjetivo = objetivo.getSquare().getX();
        while (cuadrado.getX() != coordXObjetivo) {
            Integer coordXCuadrado = cuadrado.getX();
            int comparacion = coordXCuadrado.compareTo(coordXObjetivo);
            if (comparacion > 0) {
                Square siguienteCuadrado = listaCuadrados.getDato(cuadrado.getID() - Integer.parseInt(DatosCompartidos.getAltoMatriz()));
                listaRecorrido.add(siguienteCuadrado);
                cuadrado = siguienteCuadrado;
            } else {
                Square siguienteCuadrado = listaCuadrados.getDato(cuadrado.getID() + Integer.parseInt(DatosCompartidos.getAltoMatriz()));
                listaRecorrido.add(siguienteCuadrado);
                cuadrado = siguienteCuadrado;
            }
        }
        int coordYObjetivo = objetivo.getSquare().getY();
        while (cuadrado.getY() != coordYObjetivo) {
            Integer coordYCuadrado = cuadrado.getY();
            int comparacion = coordYCuadrado.compareTo(coordYObjetivo);
            if (comparacion > 0) {
                Square siguienteCuadrado = listaCuadrados.getDato(cuadrado.getID() - 1);
                listaRecorrido.add(siguienteCuadrado);
                cuadrado = siguienteCuadrado;
            } else {
                Square siguienteCuadrado = listaCuadrados.getDato(cuadrado.getID() + 1);
                listaRecorrido.add(siguienteCuadrado);
                cuadrado = siguienteCuadrado;
            }
        }
        ind.setRecorrido(listaRecorrido);
    }


    // Método para mover un individuo normal cuando ya tiene en objetivo fijado
    private void moverIndNormalDirigido(ListaEnlazada<Square> listaRecorrido, IndNormal ind){
        Square siguienteCuadrado = listaRecorrido.getPrimero().getData();
        siguienteCuadrado.getIndividuos().add(ind);
        listaRecorrido.del(0);
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


    ///////////////// OTROS MÉTODOS ////////////////
    public static Tablero getTablero() {
        return tablero;
    }

    public void turno(){
        actualizarVidas();
        actualizarRecursos();
    }
}

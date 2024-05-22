package com.example.NewInterfaz;

import com.example.EstructurasDeDatos.ArbolBinario;
import com.example.EstructurasDeDatos.ElementoArbol;
import com.example.EstructurasDeDatos.Grafos.Camino;
import com.example.EstructurasDeDatos.Grafos.Grafo;
import com.example.EstructurasDeDatos.Grafos.Vertice;
import com.example.EstructurasDeDatos.Listas.ListaEnlazada;
import com.example.EstructurasDeDatos.Listas.ListaSimple;
import com.example.NewInterfaz.Individuos.IndAvanzado;
import com.example.NewInterfaz.Individuos.IndBasico;
import com.example.NewInterfaz.Individuos.IndNormal;
import com.example.NewInterfaz.Individuos.Individuo;
import com.google.gson.annotations.Expose;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Game {
    @Expose
    public static Tablero tablero;

    @Expose
    private boolean game;

    @Expose
    private ControllerMainStage controller;

    private static final Logger log = LogManager.getLogger(Game.class);


    public Game(GridPane tablero){
        this.tablero = new Tablero(tablero, "Agua");
        this.game = true;
        this.controller = ControllerMainStage.getInstance();
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
                            handleCeldaClick(square);
                            break; // Salir del bucle una vez que se encuentre un cuadrado clicado
                        }
                    }
                }
            }
        });
    }

    private void handleCeldaClick(Square square) {
        CeldaSeleccionada.setTipo1(square.getCelda(0).getTipo());
        CeldaSeleccionada.setTipo2(square.getCelda(1).getTipo());
        CeldaSeleccionada.setTipo3(square.getCelda(2).getTipo());
        CeldaSeleccionada.setTipo4(square.getCelda(3).getTipo());
        CeldaSeleccionada.setTipo5(square.getCelda(4).getTipo());
        CeldaSeleccionada.setTipo6(square.getCelda(5).getTipo());

        ListaSimple<Individuo> listaIndividuos = square.getIndividuos().copiaLista();
        ListaSimple<Recurso> listaRecursos = square.getRecursos().copiaLista();

        CeldaSeleccionada.setVida1(getVidasCeldas(square,1,square.getCelda(0).getTipo(),listaIndividuos,listaRecursos));
        CeldaSeleccionada.setVida2(getVidasCeldas(square,2,square.getCelda(1).getTipo(),listaIndividuos,listaRecursos));
        CeldaSeleccionada.setVida3(getVidasCeldas(square,3,square.getCelda(2).getTipo(),listaIndividuos,listaRecursos));
        CeldaSeleccionada.setVida4(getVidasCeldas(square,4,square.getCelda(3).getTipo(),listaIndividuos,listaRecursos));
        CeldaSeleccionada.setVida5(getVidasCeldas(square,5,square.getCelda(4).getTipo(),listaIndividuos,listaRecursos));
        CeldaSeleccionada.setVida6(getVidasCeldas(square,6,square.getCelda(5).getTipo(),listaIndividuos,listaRecursos));

        controller.actulizarCeldaSeleccionadaTab();
    }

    private double getVidasCeldas(Square square, int celda, Double tipo, ListaSimple<Individuo> indLista, ListaSimple<Recurso> recLista) {
        double vida = 0.0;
        if (tipo == 1.1 || tipo == 1.2 || tipo == 1.3) {
            // Buscar el individuo por ID en la lista de todos los individuos
            for (int i = 0; i < DatosCompartidos.getListaIndividuos().getNumeroElementos(); i++) {
                Individuo individuo = DatosCompartidos.getListaIndividuos().getDato(i);
                if (individuo.getID() == tipo.intValue()) {
                    vida = individuo.getTurnosVida();
                    indLista.del(0);
                    break; // Salir del bucle cuando se encuentra el individuo
                }
            }
        } else if (tipo >= 2.0 && tipo <= 7.0) {
            // Buscar el recurso por tipo en la lista de recursos del square
            for (int i = 0; i < recLista.getNumeroElementos(); i++) {
                Recurso recurso = recLista.getDato(i);
                if (recurso.getTipoRecurso().equals(tipo)) {
                    vida = recurso.getTiempoVida();
                    recLista.del(i); // Eliminar el recurso de la lista
                    break; // Salir del bucle cuando se encuentra el recurso
                }
            }
        }
        return vida;
    }

    private void handleSquareClick(int columna, int fila, Square square) {
        System.out.println("Clic en el cuadrado " + columna + ", " + fila);
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
            int tipo = generarEnteroAleatorio(0, 1); // Generar tipo aleatorio
            Double tipoIndividuo;
//            if (tipo == 0) {
//                individuoNuevo = new IndBasico(new ArbolBinario<>(null));
//                tipoIndividuo = 1.1;
//            } else if (tipo == 1) {
//                individuoNuevo = new IndNormal(new ArbolBinario<>(null));
//                individuoNuevo.getArbolGenealogico().setRaiz(individuoNuevo); // Añadimos individuonuevo al arbol para que sea la raíz
//                tipoIndividuo = 1.2;
//            } else {
                individuoNuevo = new IndAvanzado(new ArbolBinario<>(null));
                tipoIndividuo = 1.3;
//            }
            addTipo(square, tipoIndividuo); // Añado una celda de tipo 1 al square donde se añade el individuo
            square.getIndividuos().add(individuoNuevo);
            DatosCompartidos.getListaIndividuos().add(individuoNuevo);
            System.out.println("Se ha añadido un individuo tipo " + tipoIndividuo + " con id: " + individuoNuevo.getID());

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
            int subtipo = (int) ((tipo *10)-10);

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
                    celda.setColor(Color.web("#fa8072"));
//                    celda.setColor(Color.web("#fc4b08"));
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

    // Aparición de recursos durante la partida
    public void aparicionRecursos(){
         for (int i = 0; i < tablero.getSquares().getNumeroElementos(); i++){
             // Agua
             if (revisionAparicion(Double.parseDouble(DatosCompartidos.getAguaAparicion())/15)){
                 addRecursos(tablero.getSquare(i), 2.0);
             }

             // Comida
             if (revisionAparicion(Double.parseDouble(DatosCompartidos.getComidaAparicion())/15)){
                 addRecursos(tablero.getSquare(i), 3.0);
             }

             // Montaña
             if (revisionAparicion(Double.parseDouble(DatosCompartidos.getMontanaAparicion())/15)){
                 addRecursos(tablero.getSquare(i), 4.0);
             }

             // Biblioteca
             if (revisionAparicion(Double.parseDouble(DatosCompartidos.getBibliotecaAparicion())/15)){
                 addRecursos(tablero.getSquare(i), 5.0);
             }

             // Tesoro
             if (revisionAparicion(Double.parseDouble(DatosCompartidos.getTesoroAparicion())/15)){
                 addRecursos(tablero.getSquare(i), 6.0);
             }

             // Pozo
             if (revisionAparicion(Double.parseDouble(DatosCompartidos.getPozoAparicion())/15)){
                 addRecursos(tablero.getSquare(i), 7.0);
             }
         }
    }

    public boolean revisionAparicion(double recursoAparicion){
        double porcentaje = generarDoubleAleatorio(0,100);
        boolean aparicion = false;
            if (porcentaje < recursoAparicion){
                aparicion = true;
            }
        return aparicion;
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
                }
            }
        }
    }


    // Comprobar si los recursos tienen tiempo de vida y sino eliminarlos
    public void eliminarRecursos() {
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
        ListaSimple<Recurso> recursosTotales = DatosCompartidos.getListaRecursos();
        for (int i = 0; i < listaDel.getNumeroElementos(); i++) {
            for (int j = 0; j < recursosTotales.getNumeroElementos(); j++) {
                if (listaDel.getDato(i) == recursosTotales.getDato(j)) {
                    recursosTotales.del(j);
                }
            }
        }
    }


    // Se va comprobando Square por Square si los individuos tienen turnos de vida o no y si no los tienen se eliminan del square en el que estén y se llama al siguiente método para eliminarlos de DatosCompartidos
    public void eliminarIndividuos() {
        ListaSimple<Square> listaCuadrados = tablero.getSquares();
        ListaSimple<Individuo> listaDel = new ListaSimple<>();
        for (int i = 0; i < listaCuadrados.getNumeroElementos(); i++) {
            Square actual = listaCuadrados.getDato(i);
            if (!actual.getIndividuos().isVacia()) {
                int contador = 0;
                while (contador < actual.getIndividuos().getNumeroElementos()) {
                    if (actual.getIndividuos().getDato(contador).getTurnosVida() <= 0) {
                        listaDel.add(actual.getIndividuos().getDato(contador));
                        actual.getIndividuos().del(contador);
                    } else {
                        contador++;
                    }
                }
            }
        }
        if (!listaDel.isVacia()) {
            eliminarIndividuos(listaDel);
        }
    }

    // Se meten como argumento los individuos que no tienen turnos de vida y se eliminan de la lista de DatosCompartidos
    private void eliminarIndividuos(ListaSimple<Individuo> individuos) {
        ListaSimple<Individuo> indTotales = DatosCompartidos.getListaIndividuos();
        for (int i = 0; i < individuos.getNumeroElementos(); i++) {
            for (int j = 0; j < indTotales.getNumeroElementos(); j++) {
                if (individuos.getDato(i).getID() == indTotales.getDato(j).getID()) {
                    indTotales.del(j);
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
            if (vida != 0) {
            DatosCompartidos.getListaIndividuos().getDato(i).setTurnosVida(vida-1);
                System.out.println("Vida= " + DatosCompartidos.getListaIndividuos().getDato(i).getTurnosVida());
            }
        }
        for(int i = 0; i < numRecursos; i++){
            int vida = DatosCompartidos.getListaRecursos().getDato(i).getTiempoVida();
            if (vida != 0) {
                DatosCompartidos.getListaRecursos().getDato(i).setTiempoVida(vida - 1);
            }
        }
    }


    // Consumición de los recursos existentes en las celdas
    public void consumirRecursos() {
        ListaSimple<Square> listaCuadrados = tablero.getSquares();
        ListaSimple<Individuo> listaDel = new ListaSimple<>(); // Lista de individuos borrados (que se tienen que borrar de la lista de datos compartidos)
        for (int i = 0; i < listaCuadrados.getNumeroElementos(); i++) {
            Square actual = listaCuadrados.getDato(i);
            if (!actual.getIndividuos().isVacia()) {
                if (!actual.getRecursos().isVacia()) {
                    for (int ind = 0; ind < actual.getIndividuos().getNumeroElementos(); ind++) {
                        for (int rec = 0; rec < actual.getRecursos().getNumeroElementos(); rec++) {
                            if (actual.getRecursos().getDato(rec).getTipoRecurso() == 2) { // Agua
                                consumirAgua(actual.getIndividuos().getDato(ind), actual.getRecursos().getDato(rec));
                                log.info("El individuo "+actual.getIndividuos().getDato(ind).getID()+" ha consumido agua en la casilla "+actual.getX()+", "+actual.getY());
                            } else if (actual.getRecursos().getDato(rec).getTipoRecurso() == 3) { // Comida
                                consumirComida(actual.getIndividuos().getDato(ind), actual.getRecursos().getDato(rec));
                                log.info("El individuo "+actual.getIndividuos().getDato(ind).getID()+" ha consumido comida en la casilla "+actual.getX()+", "+actual.getY());

                            } else if (actual.getRecursos().getDato(rec).getTipoRecurso() == 4) { // Montaña
                                consumirMontana(actual.getIndividuos().getDato(ind), actual.getRecursos().getDato(rec));
                                log.info("El individuo "+actual.getIndividuos().getDato(ind).getID()+" ha consumido montaña en la casilla "+actual.getX()+", "+actual.getY());

                            } else if (actual.getRecursos().getDato(rec).getTipoRecurso() == 5) { // Biblioteca
                                consumirBiblioteca(actual.getIndividuos().getDato(ind), actual.getRecursos().getDato(rec));
                                log.info("El individuo "+actual.getIndividuos().getDato(ind).getID()+" ha consumido biblioteca en la casilla "+actual.getX()+", "+actual.getY());

                            } else if (actual.getRecursos().getDato(rec).getTipoRecurso() == 6) { // Tesoro
                                consumirTesoro(actual.getIndividuos().getDato(ind), actual.getRecursos().getDato(rec));
                                log.info("El individuo "+actual.getIndividuos().getDato(ind).getID()+" ha consumido tesoro en la casilla "+actual.getX()+", "+actual.getY());

                            } else {
                                listaDel.add(actual.getIndividuos().getDato(ind));
                                actual.getIndividuos().del(ind);
                                ind--; // Para que vuelva a comprobar la posición en la que estaba
                            }
                        }
                    }
                }
            }
        }
        eliminarIndividuos(listaDel);
    }

    private void consumirAgua(Individuo ind, Recurso rec) {
        int turnosExtra = rec.getEfecto();
        ind.setTurnosVida(ind.getTurnosVida() + turnosExtra);
    }

    private void consumirComida(Individuo ind, Recurso rec) {
        int turnosExtra = rec.getEfecto();
        ind.setTurnosVida(ind.getTurnosVida() + turnosExtra);
    }

    private void consumirMontana(Individuo ind, Recurso rec) {
        int turnosMenos = rec.getEfecto();
        ind.setTurnosVida(ind.getTurnosVida() - turnosMenos);
    }

    private void consumirBiblioteca(Individuo ind, Recurso rec) {
        int probabilidadAñadida = rec.getEfecto();
        ind.setProbRepr(ind.getProbRepr() + probabilidadAñadida);
    }

    private void consumirTesoro(Individuo ind, Recurso rec) {
        int probabilidadAñadida = rec.getEfecto();
        ind.setProbClon(ind.getProbClon()+probabilidadAñadida);
    }

    private void reproduccion() {
        ListaSimple<Square> listaCuadrados = tablero.getSquares();
        for (int i = 0; i < listaCuadrados.getNumeroElementos(); i++) {
            Square actual = listaCuadrados.getDato(i);
            if (actual.getIndividuos().getNumeroElementos() == 2) {
                Individuo ind1 = actual.getIndividuos().getPrimero();
                Individuo ind2 = actual.getIndividuos().getDato(1);
                int probRepr1 = ind1.getProbRepr();
                int probRepr2 = ind2.getProbRepr();
                if (generarEnteroAleatorio(0, 100) <= probRepr1 && generarEnteroAleatorio(0, 100) <= probRepr2) { //Reproducción
                    Integer tipo1 = ind1.getTipo();
                    Integer tipo2 = ind2.getTipo();
                    Integer tipoGanador;
                    if (tipo1.compareTo(tipo2) >= 0) { // El mayor tipo es tipo1
                        tipoGanador = tipo1;
                    } else { // El mayor tipo es tipo2
                        tipoGanador = tipo2;
                    }
                    DatosCompartidos.setNumIndividuos(DatosCompartidos.getNumIndividuos() + 1);
                    double tipoIndividuo;
                    Individuo individuoNuevo;
                    if (tipoGanador == 0) {
                        individuoNuevo = new IndBasico(new ArbolBinario<>(null));
                        tipoIndividuo = 1.1;
                    } else if (tipoGanador == 1) {
                        individuoNuevo = new IndNormal(new ArbolBinario<>(null));
                        tipoIndividuo = 1.2;
                    } else {
                        individuoNuevo = new IndAvanzado(new ArbolBinario<>(null));
                        tipoIndividuo = 1.3;
                    }
                    ArbolBinario<Individuo> nuevoArbol = new ArbolBinario<>(individuoNuevo, new ElementoArbol<>(ind1), new ElementoArbol<>(ind2));
                    individuoNuevo.setArbolGenealogico(nuevoArbol);
                    addTipo(actual, tipoIndividuo);
                    actual.getIndividuos().add(individuoNuevo);
                    DatosCompartidos.getListaIndividuos().add(individuoNuevo);
                    System.out.println("Ha habido reproduccion entre los individuos " + ind1.getID() + " y " + ind2.getID() + ". Ahora hay " + DatosCompartidos.getNumIndividuos() + " individuos.");
                } else { // Muerte de ambos individuos

                }
            }
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

                    moverIndBasico(cuadrado, listaCuadrados, ind);

                } else if (ind.getTipo() == 1) { // Tipo Normal

                    moverIndNormal(listaCuadrados, cuadrado, ind);

                } else {   // Tipo Avanzado

                    moverIndAvanzado(cuadrado,listaCuadrados,(IndAvanzado) ind);

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


    // Metodo principal para mover individuos Avanzados
    private void moverIndAvanzado(Square cuadrado, ListaSimple<Square> listaCuadrados, IndAvanzado ind) {
        if (ind.getRecorrido().isVacia()) {
            ListaSimple<Recurso> listaRecursosTotales = DatosCompartidos.getListaRecursos();
            ListaSimple<Recurso> recursosPositivos = new ListaSimple<>(); // Se instancia una lista de recursos benefiiosos para el individuo
            for (int i = 0; i < listaRecursosTotales.getNumeroElementos(); i++) {
                if (listaRecursosTotales.getDato(i).getTipoRecurso() != 4 && listaRecursosTotales.getDato(i).getTipoRecurso() != 7) { // Se comprueba que el recurso a añadir no sea un recurso perjudicial para el individuo
                    recursosPositivos.add(listaRecursosTotales.getDato(i));
                }
            }
            if (recursosPositivos.isVacia()) {
                moverIndBasico(cuadrado, listaCuadrados, ind);
            } else {
                buscarRecorridoAvanzado(recursosPositivos, cuadrado, ind);
                moverIndAvanzadoDirigido(ind);
            }
        } else {
            moverIndAvanzadoDirigido(ind);
        }
    }


    // Método para mover un individuo avanzado con recorrido
    private void moverIndAvanzadoDirigido(IndAvanzado ind){
        Square siguienteCuadrado = ind.getRecorrido().getPrimero().getData();
        siguienteCuadrado.getIndividuos().add(ind);
        ind.getRecorrido().del(0);
    }


    // Método para buscar un recorrido de un individuo avanzado
    private void buscarRecorridoAvanzado(ListaSimple<Recurso> recursosPositivos, Square cuadrado, IndAvanzado ind) {
        Grafo<Square> grafo = crearGrafoTablero();  // Formamos un grafo con la situación actual del tablero
        ListaSimple<Camino> caminosMinimos = new ListaSimple<>();  // Lista de caminos mínimos hacia cada uno de los recursos ventajosos
        for (int j = 0; j < recursosPositivos.getNumeroElementos(); j++) {
            Square cuadradoDestino = recursosPositivos.getDato(j).getSquare();
            caminosMinimos.add(grafo.getCaminoMinimo(cuadrado, cuadradoDestino));
        }

        // De entre todos los caminos a todos los recursos posibles, buscamos el que tenga un menor peso

        Camino caminoFinal = null;
        for (int k = 0; k < caminosMinimos.getNumeroElementos(); k++) {
            if (caminoFinal == null) {
                caminoFinal = caminosMinimos.getDato(k);
            } else {
                if (caminoFinal.getPeso() > caminosMinimos.getDato(k).getPeso()) {
                    caminoFinal = caminosMinimos.getDato(k);
                }
            }
        }

        ListaEnlazada<Square> recorrido = new ListaEnlazada<>();  // Lista de "Squares" por donde el individuo va a pasar para llegar a su destino
        
        for (int t = 0; t < caminoFinal.getCamino().getNumeroElementos(); t++) {
            if (caminoFinal.getCamino().getDato(t).getDato() != cuadrado) {
                recorrido.add((Square) caminoFinal.getCamino().getDato(t).getDato());
            }

        }

        ind.setRecorrido(recorrido);
    }


    // Método para crear un grafo a partir del tablero actual
    private Grafo<Square> crearGrafoTablero(){
        Grafo<Square> grafoTablero = new Grafo<>();
        for (int alto = 0; alto < Integer.parseInt(DatosCompartidos.getAltoMatriz()); alto++) {
            for (int ancho = 0; ancho < Integer.parseInt(DatosCompartidos.getAnchoMatriz()); ancho++) {
                Square actual = tablero.getSquare(ancho, alto);
                Vertice<Square> verticeAct = new Vertice<>(actual);
                grafoTablero.addVertice(verticeAct);

                int pesoHaciaAct = calcularPesoArista(actual);
                if (ancho > 0) {
                    Square cuadradoIzq = tablero.getSquare(ancho - 1, alto);

                    int pesoAct_Izq = calcularPesoArista(cuadradoIzq);
                    grafoTablero.addArista(actual, cuadradoIzq, pesoAct_Izq);
                    grafoTablero.addArista(cuadradoIzq, actual, pesoHaciaAct);

                }
                if (alto > 0) {
                    Square cuadradoArr = tablero.getSquare(ancho, alto - 1);

                    int pesoAct_Arr = calcularPesoArista(cuadradoArr);
                    grafoTablero.addArista(actual, cuadradoArr, pesoAct_Arr);
                    grafoTablero.addArista(cuadradoArr, actual, pesoHaciaAct);
                }
                if ((ancho > 0) && (alto > 0)) {
                    Square cuadradoDiagIzq = tablero.getSquare(ancho - 1, alto - 1);

                    int pesoAct_Diag = calcularPesoArista(cuadradoDiagIzq);
                    grafoTablero.addArista(actual, cuadradoDiagIzq, pesoAct_Diag);
                    grafoTablero.addArista(cuadradoDiagIzq, actual, pesoHaciaAct);

                }
                if ((ancho < Integer.parseInt(DatosCompartidos.getAnchoMatriz()) - 1) && (alto > 0)) {
                    Square cuadradoDiagDch = tablero.getSquare(ancho + 1, alto - 1);

                    int pesoAct_Diag = calcularPesoArista(cuadradoDiagDch);
                    grafoTablero.addArista(actual, cuadradoDiagDch, pesoAct_Diag);
                    grafoTablero.addArista(cuadradoDiagDch, actual, pesoHaciaAct);

                }
            }
        }
        return grafoTablero;
    }


    // Método para calcular el peso de las aristas del grafo en función de los recursos que tenga
    private int calcularPesoArista(Square cuadrado1) {
        int peso = 1;
        peso = añadirPesoObstaculo(peso, cuadrado1);
        return peso;
    }


    // Método para añadir peso a la arista si hay montañas o pozos
    private int añadirPesoObstaculo(int peso, Square cuadrado) {
        for (int k = 0; k < cuadrado.getRecursos().getNumeroElementos(); k++) {
            switch (cuadrado.getRecursos().getDato(k).getTipoRecurso().intValue()){
                case 4:
                    if (peso<Integer.MAX_VALUE/2){
                        peso+=cuadrado.getRecursos().getDato(k).getEfecto();
                        log.info("Peso de la arista modificado por existencia de una montaña");
                    }
                    break;
                case 7:
                    peso = Integer.MAX_VALUE/2;
                    log.info("Peso de la arista modificado por existencia de un pozo");
            }
        }
        return peso;
    }


    // Método principal para mover un individuo básico
    private void moverIndBasico(Square cuadrado, ListaSimple<Square> listaCuadrados, Individuo ind) {
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


    // Método principal para mover un individuo normal
    private void moverIndNormal(ListaSimple<Square> listaCuadrados, Square cuadrado, Individuo ind) {
        IndNormal indNormal = (IndNormal) ind;
        if (!DatosCompartidos.getListaRecursos().isVacia()) {
            if (indNormal.getRecorrido().isVacia()) {
                buscarNuevoObjetivo(listaCuadrados, cuadrado, indNormal);
                if (!indNormal.getRecorrido().isVacia()) {
                    moverIndNormalDirigido(indNormal.getRecorrido(), indNormal);
                }
            } else {
                moverIndNormalDirigido(indNormal.getRecorrido(), indNormal);
            }
        } else {
            if (!indNormal.getRecorrido().isVacia()) { // Aunque no existan ya recursos, si el individuo tenía fijado un objetivo, llegará hasta él
                moverIndNormalDirigido(indNormal.getRecorrido(), indNormal);
            } else { // Si no hay más recursos y el individuo no tenía ningún objetivo fijado, se moverá como un individuo básico
                moverIndBasico(cuadrado, tablero.getSquares(), ind);
            }
        }
    }


    // Método para buscar un objetivo random para un individuo normal
    private void buscarNuevoObjetivo(ListaSimple<Square> listaCuadrados, Square cuadrado, IndNormal ind) {
        int recursoRandom = generarEnteroAleatorio(0, DatosCompartidos.getListaRecursos().getNumeroElementos() - 1);
        Recurso objetivo = DatosCompartidos.getListaRecursos().getDato(recursoRandom);
        if ((DatosCompartidos.getListaRecursos().getNumeroElementos() == 1) && (cuadrado.getID() == objetivo.getSquare().getID())) {
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
            ind.getRecorrido().vaciar();
        } else {
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

    private double generarDoubleAleatorio(double min, double max){
        Random random = new Random();

        double numeroAleatorio = min + (max-min)* random.nextDouble();
        NumberFormat formatoDecimal = NumberFormat.getNumberInstance(Locale.US);
        formatoDecimal.setMaximumFractionDigits(2);
        String numeroFormateaado = formatoDecimal.format(numeroAleatorio);
        return Double.parseDouble(numeroFormateaado);
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


    // Actualiza las probabilidades de reproduccion y clonación de cada individuo
    public void actualizarProbabilidades() {
        ListaSimple<Individuo> indTotales = DatosCompartidos.getListaIndividuos();
        for (int i = 0; i < indTotales.getNumeroElementos(); i++) {
            Individuo actual = indTotales.getDato(i);

            // Probabilidad de clonación
            if (actual.getProbClon() >= 10) {
                actual.setProbClon(actual.getProbClon() - 10);
            } else if (actual.getProbClon() < 10) {
                actual.setProbClon(0);
            }

            // Probabilidad de reproducción
            if (actual.getProbRepr() >= 10) {
                actual.setProbRepr(actual.getProbRepr() - 10);
            } else if (actual.getProbRepr() < 10) {
                actual.setProbRepr(0);
            }
        }
    }


    public static Tablero getTablero() {
        return tablero;
    }

    public void turno(){

        // 1º Actualizamos Vidas y porcentajes de individuos y eliminamos los que no tengan más turnos de vida
        actualizarVidas();
        actualizarProbabilidades();
        eliminarIndividuos();
        eliminarRecursos();
        actualizarIndividuos();

        // 2ª Movimiento de individuos
        moverIndividuos();

        // 3º Consumición de los recursos
        consumirRecursos();

        // 4º Reproduccion de los individuos
        reproduccion();
        actualizarIndividuos();

        // 5º Clonación de los individuos


        // 6º Evauación de que no haya más de tres individuos/recursos por casilla


        // 7º Generación de nuevos recursos
        aparicionRecursos();

        // 7º Pintar el tablero restante
        actualizarTablero();
    }
}

package com.example.ElJuegoDeLaVida;

public class DatosCompartidos {
    private static String  altoMatriz = "0";
    private static String anchoMatriz = "0";
    private static String probReproduccion = "0";
    private static String probClonacion = "0";
    private static String VidaInicial = "0";


    public static String getAltoMatriz() {
        return altoMatriz;
    }

    public static void setAltoMatriz(String altoMatriz) {
        DatosCompartidos.altoMatriz = altoMatriz;
    }

    public static String getAnchoMatriz() {
        return anchoMatriz;
    }

    public static void setAnchoMatriz(String anchoMatriz) {
        DatosCompartidos.anchoMatriz = anchoMatriz;
    }

    public static String getProbReproduccion() {
        return probReproduccion;
    }

    public static void setProbReproduccion(String probReproduccion) {
        DatosCompartidos.probReproduccion = probReproduccion;
    }

    public static String getProbClonacion() {
        return probClonacion;
    }

    public static void setProbClonacion(String probClonacion) {
        DatosCompartidos.probClonacion = probClonacion;
    }

    public static String getVidaInicial() {
        return VidaInicial;
    }

    public static void setVidaInicial(String vidaInicial) {
        VidaInicial = vidaInicial;
    }
}

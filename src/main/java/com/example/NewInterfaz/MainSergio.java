package com.example.NewInterfaz;

import java.util.Random;

public class MainSergio {
    private static int generarEnteroAleatorio(int min, int max) {
        Random random = new Random();
        int rango = max - min + 1;
        int numeroAleatorio = random.nextInt(rango) + min;
        return numeroAleatorio;
    }
    public static void main(String[] args){
        System.out.println(generarEnteroAleatorio(1,8));
    }
}

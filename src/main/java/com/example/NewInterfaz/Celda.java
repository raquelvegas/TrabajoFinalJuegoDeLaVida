package com.example.NewInterfaz;

public class Celda {
    private String tipo;
    private int numero;
    private boolean ocupado;

    public Celda() {
        this.tipo = "";
        this.numero = 0;
        this.ocupado = false;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isOcupado() {
        return ocupado;
    }
}

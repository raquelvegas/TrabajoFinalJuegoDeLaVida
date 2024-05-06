package com.example.NewInterfaz;

public class Recurso {
    int tipoRecurso;
    int tiempoVida;
    int efecto;

    public Recurso(int tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
        if (tipoRecurso == 2){
            tiempoVida = Integer.parseInt(DatosCompartidos.getAguaVida());
            efecto = Integer.parseInt(DatosCompartidos.getAguaEfecto());
        } else if (tipoRecurso == 3) {
            tiempoVida = Integer.parseInt(DatosCompartidos.getComidaVida());
            efecto = Integer.parseInt(DatosCompartidos.getComidaEfecto());
        } else if (tipoRecurso == 4) {
            tiempoVida = Integer.parseInt(DatosCompartidos.getMontanaVida());
            efecto = Integer.parseInt(DatosCompartidos.getMontanaEfecto());
        } else if (tipoRecurso == 5) {
            tiempoVida = Integer.parseInt(DatosCompartidos.getBibliotecaVida());
            efecto = Integer.parseInt(DatosCompartidos.getBibliotecaEfecto());
        } else if (tipoRecurso == 6) {
            tiempoVida = Integer.parseInt(DatosCompartidos.getTesoroVida());
            efecto = Integer.parseInt(DatosCompartidos.getTesoroEfecto());
        } else if (tipoRecurso == 7) {
            tiempoVida = Integer.parseInt(DatosCompartidos.getPozoVida());
            efecto = 100;
        }
    }

    public int getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(int tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }
}

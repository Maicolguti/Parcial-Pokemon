package org.example.Model;

import java.util.Objects;


public class Pokemon {
    private String nombre;
    private int puntosDeVidaMaximos;
    private int ataque;
    private int defensa;
    private int experienciaRequerida;
    private Pokemon evolucionSiguiente;

    public Pokemon (String nombre, int puntosDeVidaMaximos, int ataque, int defensa, int experienciaRequerida)
    {
        this.nombre = nombre;
        this.puntosDeVidaMaximos= puntosDeVidaMaximos;
        this.ataque = ataque;
        this.defensa = defensa;
        this.experienciaRequerida = experienciaRequerida;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeVidaMaximos() {
        return puntosDeVidaMaximos;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getExperienciaRequerida() {
        return experienciaRequerida;
    }

    public Pokemon getEvolucionSiguiente() {
        return evolucionSiguiente;
    }

    public void setEvolucionSiguiente(Pokemon evolucionSiguiente) {
        this.evolucionSiguiente = evolucionSiguiente;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", HP=" + puntosDeVidaMaximos +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", xpRequerida=" + experienciaRequerida +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pokemon otro = (Pokemon) obj;
        return puntosDeVidaMaximos == otro.puntosDeVidaMaximos &&
                ataque == otro.ataque &&
                defensa == otro.defensa &&
                experienciaRequerida == otro.experienciaRequerida &&
                Objects.equals(nombre, otro.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, puntosDeVidaMaximos, ataque, defensa, experienciaRequerida);
    }
}
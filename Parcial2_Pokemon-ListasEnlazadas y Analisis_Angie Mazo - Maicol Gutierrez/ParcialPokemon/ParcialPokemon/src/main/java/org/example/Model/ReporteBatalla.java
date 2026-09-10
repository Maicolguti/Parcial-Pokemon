package org.example.Model;

import java.util.Objects;

public class ReporteBatalla {
    private String nombreMiPokemon;
    private String nombreEnemigo;
    private int cantidadDerrotados;

    public ReporteBatalla (String nombreMipokemon, String nombreEnemigo, int CantidadDerrotados) {

    this.nombreMiPokemon=getNombreMiPokemon();
    this.nombreEnemigo=getNombreEnemigo();
    this.cantidadDerrotados=getCantidadDerrotados();
    }


    public String getNombreMiPokemon() {
        return nombreMiPokemon;
    }

    public String getNombreEnemigo() {
        return nombreEnemigo;
    }

    public void setNombreEnemigo(String nombreEnemigo) {
        this.nombreEnemigo = nombreEnemigo;
    }

    public void setNombreMiPokemon(String nombreMiPokemon) {
        this.nombreMiPokemon = nombreMiPokemon;
    }

    public int getCantidadDerrotados() {
        return cantidadDerrotados;
    }

    public void setCantidadDerrotados(int cantidadDerrotados) {
        this.cantidadDerrotados += cantidadDerrotados;
    }


    @Override
    public boolean equals(Object obje) {
        if (this == obje) return true;
        if (obje == null || getClass() != obje.getClass()) return false;
        ReporteBatalla otro = (ReporteBatalla) obje;

        return Objects.equals(nombreMiPokemon, otro.nombreMiPokemon) &&
                Objects.equals(nombreEnemigo, otro.nombreEnemigo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombreMiPokemon, nombreEnemigo);
    }
}
}





package org.example;

import org.example.Model.Pokemon;
import org.example.Model.LineaEvolutiva;
import org.example.Service.EntrenamientoMasivo;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jol.info.ClassLayout;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Logger loggerTiempos = LogManager.getLogger("tiempos");
    private static final Logger loggerPerformance = LogManager.getLogger("performance");
    private static final Logger loggerBatalla = LogManager.getLogger("Historicos");
    public static void main(String[] args) {

        Pokemon charizard = new Pokemon("Charizard", 78, 84, 78, -1);
        Pokemon charmeleon = new Pokemon("Charmeleon", 58, 64, 58, 5000);
        Pokemon charmander = new Pokemon("Charmander", 39, 52, 43, 1500);

        charmander.setEvolucionSiguiente(charmeleon);
        charmeleon.setEvolucionSiguiente(charizard);

        EntrenamientoMasivo entrenamiento = new EntrenamientoMasivo();

        LineaEvolutiva miPokemonPrueba = new LineaEvolutiva(charmander);
        Pokemon rattata = new Pokemon("Rattata", 30, 56, 35, -1);

        logger.info("Pokemon:\n{}", ClassLayout.parseInstance(charmander).toPrintable());
        logger.info("LineaEvolutiva:\n{}", ClassLayout.parseInstance(miPokemonPrueba).toPrintable());

        logger.info("---Prueba unitaria: Charmander vs Rattata---");
        Pokemon[] hordaPrueba = { rattata };
        entrenamiento.iniciarEntrenamientoMasivo(miPokemonPrueba, hordaPrueba, true);

        logger.info("XP acumulada: {}", miPokemonPrueba.getExperienciaAcumulada());
        logger.info("Fase actual: {}", miPokemonPrueba.getFaseActual().getNombre());

        logger.info("---Prueba de estrés: entrenamiento masivo---");

        LineaEvolutiva miPokemonEntrenamiento = new LineaEvolutiva(charmander);

        Random random = new Random();
        Pokemon[] hordaEnemigos = new Pokemon[100000];

        for (int i = 0; i < hordaEnemigos.length; i++) {
            int hpAleatorio = 30 + random.nextInt(21);
            int ataqueAleatorio = 20 + random.nextInt(16);
            int defensaAleatoria = 20 + random.nextInt(21);
            hordaEnemigos[i] = new Pokemon("Caterpie", hpAleatorio, ataqueAleatorio, defensaAleatoria, -1);
        }

        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory memoria = systemInfo.getHardware().getMemory();
        long ramDisponibleAntes = memoria.getAvailable();

        long inicio = System.nanoTime();
        entrenamiento.iniciarEntrenamientoMasivo(miPokemonEntrenamiento, hordaEnemigos, false);
        long fin = System.nanoTime();

        long duracionNanos = fin - inicio;
        long duracionMs = duracionNanos / 1_000_000;

        long ramDisponibleDespues = memoria.getAvailable();
        long ramUsada = ramDisponibleAntes - ramDisponibleDespues;

        loggerPerformance.info("RAM disponible antes: {} MB", ramDisponibleAntes / (1024 * 1024));
        loggerPerformance.info("RAM disponible después: {} MB", ramDisponibleDespues / (1024 * 1024));
        loggerPerformance.info("RAM consumida durante el entrenamiento masivo: {} MB", ramUsada / (1024 * 1024));

        loggerTiempos.info("Entrenamiento masivo de {} enemigos completado en {} ms ({} ns)",
                hordaEnemigos.length, duracionMs, duracionNanos);

        logger.info("Fase final: {}", miPokemonEntrenamiento.getFaseActual().getNombre());
        logger.info("XP final acumulada: {}", miPokemonEntrenamiento.getExperienciaAcumulada());
    }
}
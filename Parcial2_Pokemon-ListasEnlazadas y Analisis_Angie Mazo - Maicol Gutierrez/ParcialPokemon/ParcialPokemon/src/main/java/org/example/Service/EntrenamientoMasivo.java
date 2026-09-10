 package org.example.Service;

import org.example.Model.LineaEvolutiva;
import org.example.Model.Pokemon;
import static java.lang.Math.max;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Model.ReporteBatalla;
import java.util.LinkedList;
import java.util.ArrayDeque;

public class EntrenamientoMasivo {
    int caterpiesDerrotados = 0;
    LinkedList<ReporteBatalla> historicoBatallas = new LinkedList<>();
    int limite = 50;
    private static final Logger logger = LogManager.getLogger(EntrenamientoMasivo.class);
    public void iniciarEntrenamientoMasivo(LineaEvolutiva miPokemon, Pokemon[] hordaEnemigos, boolean mostrarDetalle) {        int turno =0;

        for (int i = 0; i < hordaEnemigos.length; i++) {
            Pokemon enemigoActual = hordaEnemigos[i];
            int hpMiPokemon = miPokemon.getFaseActual().getPuntosDeVidaMaximos();
            int hpEnemigoActual = enemigoActual.getPuntosDeVidaMaximos();

            while (hpMiPokemon > 0 && hpEnemigoActual > 0 ) {
                turno++;

               int danoinflingido = Math.max(1, miPokemon.getFaseActual().getAtaque() - enemigoActual.getDefensa());
               hpEnemigoActual -= danoinflingido;

                if (mostrarDetalle) {
                    logger.info("Turno {} - {} ataca. Daño: {} | {} HP: {}",
                            turno, miPokemon.getFaseActual().getNombre(), danoinflingido,
                            enemigoActual.getNombre(), Math.max(hpEnemigoActual, 0));
                }

               if (hpEnemigoActual <= 0) {
                   miPokemon.ganarExperiencia(50);
                   miPokemon.mecanismoEvolucion(i + 1);
                   ReporteBatalla batallaActual = new ReporteBatalla (miPokemon.getFaseActual().getNombre(), enemigoActual.getNombre(), 1);
                   if (historicoBatallas.size() != 0){
                       if (historicoBatallas.getLast().equals(batallaActual)) {
                           historicoBatallas.getLast().setCantidadDerrotados(1);
                       }
                       else {
                           if (historicoBatallas.size()>limite) {
                               historicoBatallas.removeFirst();
                               historicoBatallas.add(batallaActual); }
                           else {
                               historicoBatallas.add(batallaActual);
                           }
                           break;

                       }
                   }
                  break;
               }
               int danoRecibido = Math.max(1, enemigoActual.getAtaque() - miPokemon.getFaseActual().getDefensa());
               hpMiPokemon -= danoRecibido;

               if (mostrarDetalle) {
                   logger.info("Turno {} - {} ataca. Daño: {} | {} HP: {}",
                           turno, enemigoActual.getNombre(), danoRecibido,
                           miPokemon.getFaseActual().getNombre(), Math.max(hpMiPokemon, 0));
               }

               if (hpMiPokemon <= 0) {
                   logger.warn("Tu pokemon murio");
                   break;
               }
            }
            }

    }
}

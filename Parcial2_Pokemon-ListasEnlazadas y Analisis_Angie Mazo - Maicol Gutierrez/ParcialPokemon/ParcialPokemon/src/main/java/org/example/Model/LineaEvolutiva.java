package org.example.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LineaEvolutiva {
private Pokemon faseActual;
private int experienciaAcumulada;
private static final Logger logger = LogManager.getLogger(LineaEvolutiva.class);
public LineaEvolutiva (Pokemon faseActual){ //No recibimos experienciaAcumulada en el constructor, ya que si se está creando, no debería tener experiencia aún. Siempre empieza en 0 :)
    this.experienciaAcumulada = 0;
    this.faseActual = faseActual;
}

    public Pokemon getFaseActual() {
        return faseActual;
    }

    public int getExperienciaAcumulada() {
        return experienciaAcumulada;
    }
    public void ganarExperiencia (int cantidadXp){
    this.experienciaAcumulada += cantidadXp;
    }

    public void mecanismoEvolucion(int numeroEnemigo) {
        if (this.experienciaAcumulada >= faseActual.getExperienciaRequerida() && faseActual.getEvolucionSiguiente() != null) {
            faseActual = faseActual.getEvolucionSiguiente();
            logger.info("Enemigo #{} -> XP: {} -> Evolucion: {}",
                    numeroEnemigo, experienciaAcumulada, faseActual.getNombre());
        }
    }

}



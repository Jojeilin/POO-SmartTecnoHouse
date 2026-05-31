package modelo;

import java.util.ArrayList;
import java.util.List;

public class CasaDomoticaManager {
    // 1. Instancia única estática (Singleton)
    private static CasaDomoticaManager instancia;

    // Atributos del Modelo
    private List<Sensor> sensores;
    private List<Actuador> actuadores;
    private List<Regla> reglas;

    // 2. Constructor privado para evitar que se use "new" fuera de esta clase
    private CasaDomoticaManager() {
        sensores = new ArrayList<>();
        actuadores = new ArrayList<>();
        reglas = new ArrayList<>();
        inicializarEntorno();
    }

    // 3. Método de acceso global a la instancia única
    public static synchronized CasaDomoticaManager getInstancia() {
        if (instancia == null) {
            instancia = new CasaDomoticaManager();
        }
        return instancia;
    }

    private void inicializarEntorno() {
        // Carga inicial de tus componentes de la casa inteligentes (incluyendo los extras)
        sensores.add(new SensorTemperatura("temp_salon", "Termómetro Salón"));
        sensores.add(new SensorLuz("luz_terraza", "Sensor Luz Exterior"));
        sensores.add(new SensorPresencia("pir_entrada", "Sensor Presencia Hall"));
        sensores.add(new SensorHumedad("hum_baño", "Higrómetro Baño"));

        actuadores.add(new ActuadorBombilla("bulb_salon", "Lámpara Principal"));
        actuadores.add(new ActuadorVentilador("fan_salon", "Ventilador Techo"));
        actuadores.add(new ActuadorPersiana("blind_hab", "Persiana Dormitorio"));

        // Registrar las estrategias de reglas
        reglas.add(new ReglaVentilacionConfortable());
        reglas.add(new ReglaIluminacionAutomatica());
    }

    // Métodos de lógica del negocio
    public void simularPasoTiempo() {
        // Actualiza el valor de todos los sensores de forma polimórfica
        for (Sensor sensor : sensores) {
            sensor.actualizarValor();
        }
        // Evalúa y aplica las reglas del patrón Strategy
        evaluarReglas();
    }

    public void evaluarReglas() {
        for (Regla regla : reglas) {
            regla.aplicar(sensores, actuadores);
        }
    }

    // Getters
    public List<Sensor> getSensores() { return sensores; }
    public List<Actuador> getActuadores() { return actuadores; }
    public List<Regla> getReglas() { return reglas; }
}
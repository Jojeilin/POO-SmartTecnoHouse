package modelo;

import java.util.List;

public class ReglaIluminacionAutomatica implements Regla {
    @Override
    public String getNombre() { return "Iluminación Automática"; }

    @Override
    public String getDescripcion() { return "Enciende luces si hay poca luz ambiental y se detecta presencia"; }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor luzSensor = sensores.stream().filter(s -> s.getID().contains("luz")).findFirst().orElse(null);
        Sensor pirSensor = sensores.stream().filter(s -> s.getID().contains("pir")).findFirst().orElse(null);
        Actuador bulbActuador = actuadores.stream().filter(a -> a.getID().contains("bulb")).findFirst().orElse(null);

        if (luzSensor != null && pirSensor != null && bulbActuador != null) {
            if (luzSensor.getValor() < 200.0 && pirSensor.getValor() == 1.0) {
                bulbActuador.ejecutarAccion("ON");
            } else if (pirSensor.getValor() == 0.0) {
                bulbActuador.ejecutarAccion("OFF");
            }
        }
    }
}

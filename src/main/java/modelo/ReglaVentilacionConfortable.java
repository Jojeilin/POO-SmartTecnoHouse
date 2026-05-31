package modelo;

import java.util.List;

public class ReglaVentilacionConfortable implements Regla {
    @Override
    public String getNombre() { return "Ventilación Confortable"; }

    @Override
    public String getDescripcion() { return "Enciende el ventilador si T > 28°C y lo apaga si T < 22°C"; }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores) {
        Sensor tempSensor = sensores.stream().filter(s -> s.getID().contains("temp")).findFirst().orElse(null);
        Actuador fanActuador = actuadores.stream().filter(a -> a.getID().contains("fan")).findFirst().orElse(null);

        if (tempSensor != null && fanActuador != null) {
            double temp = tempSensor.getValor();
            if (temp > 28.0) {
                fanActuador.ejecutarAccion("HIGH");
            } else if (temp < 22.0) {
                fanActuador.ejecutarAccion("OFF");
            }
        }
    }
}

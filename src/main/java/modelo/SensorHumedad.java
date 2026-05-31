package modelo;

public class SensorHumedad extends Sensor {

    public SensorHumedad(String id, String nombre) {
        super(id, nombre, "% HR"); // Humedad Relativa
        this.ultimoValor = 50.0; // Valor inicial estándar
    }

    @Override
    public void actualizarValor() {
        // Simula la humedad ambiente de una casa entre el 40% y el 80%
        double nuevaHumedad = 40.0 + (Math.random() * 40.0);
        this.ultimoValor = Math.round(nuevaHumedad * 10.0) / 10.0;
    }
}

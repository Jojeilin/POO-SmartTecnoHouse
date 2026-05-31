package modelo;

public class SensorLuz extends Sensor {
    public SensorLuz(String id, String nombre) {
        super(id, nombre, "lux");
        this.ultimoValor = 300.0;
    }

    @Override
    public void actualizarValor() {
        // Simula luz ambiental (0 a 1000 lux)
        this.ultimoValor = Math.round(Math.random() * 1000);
    }
}

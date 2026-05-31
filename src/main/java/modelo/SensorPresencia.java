package modelo;

public class SensorPresencia extends Sensor {
    public SensorPresencia(String id, String nombre) {
        super(id, nombre, "(1=Detectado, 0=Vacío)");
        this.ultimoValor = 0.0;
    }

    @Override
    public void actualizarValor() {
        // Simula presencia: 0 o 1 de forma aleatoria
        this.ultimoValor = Math.random() > 0.7 ? 1.0 : 0.0;
    }

    @Override
    public String getEstadoActual() {
        return this.ultimoValor == 1.0 ? "MOVIMIENTO DETECTADO" : "SIN NOVEDAD";
    }
}

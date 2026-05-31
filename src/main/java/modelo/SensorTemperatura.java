package modelo;

public class SensorTemperatura extends Sensor {

    // El constructor recibe el ID y el nombre, y se los pasa a la clase madre (Sensor)
    // Además, le dice que su unidad de medida es "°C"
    public SensorTemperatura(String id, String nombre) {
        super(id, nombre, "°C");
        this.ultimoValor = 22.0; // Valor inicial de confort
    }

    @Override
    public void actualizarValor() {
        // Simula fluctuaciones de temperatura realistas entre 18°C y 32°C
        // Redondeamos a un decimal para que quede bonito en la interfaz gráfica
        double nuevoValor = 18.0 + (Math.random() * 14.0);
        this.ultimoValor = Math.round(nuevoValor * 10.0) / 10.0;
    }
}

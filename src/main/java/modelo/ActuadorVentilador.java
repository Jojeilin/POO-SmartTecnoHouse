package modelo;

public class ActuadorVentilador extends Actuador {
    public ActuadorVentilador(String id, String nombre) {
        super(id, nombre, "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equalsIgnoreCase("OFF") || accion.equalsIgnoreCase("LOW") || accion.equalsIgnoreCase("HIGH")) {
            this.estado = accion.toUpperCase();
        } else {
            System.out.println("Acción no válida para Ventilador: " + accion);
        }
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"OFF", "LOW", "HIGH"};
    }
}

package modelo;

public class ActuadorBombilla extends Actuador {

    public ActuadorBombilla(String id, String nombre) {
        super(id, nombre, "OFF"); // Por defecto empieza apagada
    }

    @Override
    public String[] getAccionesPosibles() {
        // Una bombilla estándar solo entiende de encender y apagar
        return new String[]{"ON", "OFF"};
    }

    @Override
    public void ejecutarAccion(String accion) {
        // Validamos que la acción recibida sea una de las permitidas
        if (accion.equalsIgnoreCase("ON") || accion.equalsIgnoreCase("OFF")) {
            this.estado = accion.toUpperCase();
        } else {
            System.out.println("⚠️ Error: Acción '" + accion + "' no válida para una bombilla.");
        }
    }
}



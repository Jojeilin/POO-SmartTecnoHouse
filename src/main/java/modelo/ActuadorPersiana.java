package modelo;

public class ActuadorPersiana extends Actuador {

    public ActuadorPersiana(String id, String nombre) {
        super(id, nombre, "BAJADA"); // Estado inicial
    }

    @Override
    public String[] getAccionesPosibles() {
        // Una persiana motorizada tiene tres estados posibles
        return new String[]{"SUBIR", "BAJAR", "STOP"};
    }

    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equalsIgnoreCase("SUBIR")) {
            this.estado = "SUBIDA";
        } else if (accion.equalsIgnoreCase("BAJAR")) {
            this.estado = "BAJADA";
        } else if (accion.equalsIgnoreCase("STOP")) {
            this.estado = "DETENIDA";
        } else {
            System.out.println("⚠️ Error: Acción '" + accion + "' no válida para la persiana.");
        }
    }
}

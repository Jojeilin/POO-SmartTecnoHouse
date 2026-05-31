package modelo;

package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {

    // 1. Instancia única estática (Singleton)
    private static LogService instancia;
    private final String NOMBRE_ARCHIVO = "actuators.log";

    // 2. Constructor privado
    private LogService() {
        // Inicializa el archivo creando una cabecera si el archivo está vacío
        registrarLinea("--- SISTEMA SMART TECNOHOUSE LOG INICIADO ---");
    }

    // 3. Acceso global a la instancia
    public static synchronized LogService getInstancia() {
        if (instancia == null) {
            instancia = new LogService();
        }
        return instancia;
    }

    /**
     * Escribe un cambio de estado de un actuador en el archivo actuators.log
     */
    public void registrarCambio(Actuador actuador, String accion Solicitada) {
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String mensaje = String.format("[%s] ACTUADOR: %s (%s) -> Acción: '%s' -> Nuevo Estado: %s",
                fechaHora, actuador.getNombre(), actuador.getID(), accionSolicitada, actuador.getEstadoActual());

        registrarLinea(mensaje);
    }

    // Método interno auxiliar para escribir en el archivo de texto
    private void registrarLinea(String linea) {
        // Usamos 'true' en el FileWriter para que añada líneas al final (append) en vez de borrar el archivo
        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(linea);
        } catch (IOException e) {
            System.err.println("❌ Error crítico al escribir en " + NOMBRE_ARCHIVO + ": " + e.getMessage());
        }
    }
}
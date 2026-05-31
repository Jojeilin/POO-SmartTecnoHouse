package controlador;

import modelo.Actuador;
import modelo.CasaDomoticaManager;
import modelo.Sensor;
import vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DomoticaControlador {

    private final VentanaPrincipal vista;
    private final CasaDomoticaManager modelo;

    public DomoticaControlador(VentanaPrincipal vista, CasaDomoticaManager modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // 1. Enlazamos los botones de la vista con los listeners de control
        this.vista.escucharBotonSimular(new BotonSimularListener());
        this.vista.escucharBotonAccion(new BotonAccionListener());

        // 2. LLAMADA REFACTORIZADA: Rellenamos el ComboBox dinámicamente desde el Modelo
        configurarSelectorAcciones();

        // 3. Pintamos el estado inicial de las tablas
        actualizarPantalla();
    }

    /**
     * MÉTODO REFACTORIZADO: Mapea dinámicamente los comandos válidos de los actuadores del modelo.
     * La Vista ya no tiene textos fijos; se adapta a lo que el Modelo contenga.
     */
    private void configurarSelectorAcciones() {
        // Limpiamos cualquier texto por defecto que tuviera el ComboBox de la Vista
        vista.getComboAcciones().removeAllItems();

        // Traemos los actuadores reales de la casa
        List<Actuador> actuadores = modelo.getActuadores();

        // Recorremos cada aparato y sus acciones disponibles para meterlas al menú
        for (Actuador actuador : actuadores) {
            for (String accion : actuador.getAccionesPosibles()) {
                // Formato neutro de comunicación: "ID:ACCION" (ej. "bulb_salon:ON" o "blind_hab:SUBIR")
                vista.getComboAcciones().addItem(actuador.getID() + ":" + accion);
            }
        }
    }

    private void actualizarPantalla() {
        vista.getModeloTablaSensores().setRowCount(0);
        vista.getModeloTablaActuadores().setRowCount(0);

        for (Sensor s : modelo.getSensores()) {
            vista.getModeloTablaSensores().addRow(new Object[]{s.getID(), s.getNombre(), s.getEstadoActual()});
        }

        for (Actuador a : modelo.getActuadores()) {
            vista.getModeloTablaActuadores().addRow(new Object[]{a.getID(), a.getNombre(), a.getEstadoActual()});
        }
    }

    // --- ESCUCHADORES DE EVENTOS ---

    private class BotonSimularListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modelo.simularPasoTiempo();
            actualizarPantalla();
        }
    }

    private class BotonAccionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Leemos el par "ID:ACCION" seleccionado por el usuario en la interfaz
            String itemSeleccionado = (String) vista.getComboAcciones().getSelectedItem();

            if (itemSeleccionado != null && itemSeleccionado.contains(":")) {
                // Rompemos el texto por los dos puntos ":"
                String[] partes = itemSeleccionado.split(":");
                String idActuador = partes[0];
                String accion = partes[1];

                // El controlador busca el actuador en el modelo por su ID y le manda la orden directa
                modelo.getActuadores().stream()
                        .filter(a -> a.getID().equals(idActuador))
                        .findFirst()
                        .ifPresent(actuador -> actuador.ejecutarAccion(accion));

                // Volvemos a chequear si alguna regla (Strategy) debe reaccionar al cambio manual
                modelo.evaluarReglas();

                // Refrescamos los componentes visuales
                actualizarPantalla();
            }
        }
    }
}




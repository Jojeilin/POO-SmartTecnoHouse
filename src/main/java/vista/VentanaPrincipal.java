package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    // Componentes de la interfaz (Botones y Tablas)
    private JButton btnSimularTiempo;
    private JButton btnEjecutarAccion;
    private JComboBox<String> comboAcciones;
    private JTable tablaSensores;
    private JTable tablaActuadores;

    // Modelos de datos para las tablas (permiten cambiar los datos dinámicamente)
    private DefaultTableModel modeloTablaSensores;
    private DefaultTableModel modeloTablaActuadores;

    public VentanaPrincipal() {
        // Configuración básica de la ventana principal
        setTitle("Smart TecnoHouse SA - Panel de Control Domótico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // 1. PANEL SUPERIOR: Título y Botón de Simulación General
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("SISTEMA DE CONTROL DOMÓTICO IoT", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        btnSimularTiempo = new JButton("Simular Paso del Tiempo ⏱️");
        btnSimularTiempo.setFont(new Font("Arial", Font.BOLD, 12));
        panelSuperior.add(btnSimularTiempo, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        // 2. PANEL CENTRAL: Tablas de Monitoreo (Layout de rejilla 1x2)
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Subpanel Sensores
        JPanel panelSensores = new JPanel(new BorderLayout());
        panelSensores.setBorder(BorderFactory.createTitledBorder("Sensores en Tiempo Real"));
        modeloTablaSensores = new DefaultTableModel(new String[]{"ID", "Dispositivo", "Lectura"}, 0);
        tablaSensores = new JTable(modeloTablaSensores);
        panelSensores.add(new JScrollPane(tablaSensores), BorderLayout.CENTER);
        panelCentral.add(panelSensores);

        // Subpanel Actuadores
        JPanel panelActuadores = new JPanel(new BorderLayout());
        panelActuadores.setBorder(BorderFactory.createTitledBorder("Actuadores y Estado de Relés"));
        modeloTablaActuadores = new DefaultTableModel(new String[]{"ID", "Dispositivo", "Estado Actual"}, 0);
        tablaActuadores = new JTable(modeloTablaActuadores);
        panelActuadores.add(new JScrollPane(tablaActuadores), BorderLayout.CENTER);
        panelCentral.add(panelActuadores);

        add(panelCentral, BorderLayout.CENTER);

        // 3. PANEL INFERIOR: Acciones manuales del Usuario
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelInferior.setBorder(BorderFactory.createTitledBorder("Panel de Control Manual"));

        panelInferior.add(new JLabel("Acción Manual:"));
        comboAcciones = new JComboBox<>();
        panelInferior.add(comboAcciones);

        btnEjecutarAccion = new JButton("Enviar Comando 🚀");
        panelInferior.add(btnEjecutarAccion);

        add(panelInferior, BorderLayout.SOUTH);
    }

    // --- MÉTODOS PARA EL CONTROLADOR (Los "ganchos" de comunicación) ---

    /**
     * Permite al Controlador registrar qué hacer cuando se pulsa el botón de simulación.
     */
    public void escucharBotonSimular(ActionListener listener) {
        btnSimularTiempo.addActionListener(listener);
    }

    /**
     * Permite al Controlador registrar qué hacer cuando se pulsa el botón de comandos manuales.
     */
    public void escucharBotonAccion(ActionListener listener) {
        btnEjecutarAccion.addActionListener(listener);
    }

    // Getters para que el controlador pueda rellenar o leer los datos visuales
    public DefaultTableModel getModeloTablaSensores() { return modeloTablaSensores; }
    public DefaultTableModel getModeloTablaActuadores() { return modeloTablaActuadores; }
    public JTable getTablaActuadores() { return tablaActuadores; }
    public JComboBox<String> getComboAcciones() { return comboAcciones; }
}
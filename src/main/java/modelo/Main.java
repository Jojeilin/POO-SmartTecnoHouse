package modelo;

import controlador.DomoticaControlador;
import vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Es una buena práctica de Swing arrancar la interfaz gráfica dentro del hilo de eventos seguro
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Instanciamos el Modelo Único (Singleton)
                CasaDomoticaManager modelo = CasaDomoticaManager.getInstancia();

                // 2. Instanciamos la Vista (La Ventana Swing)
                VentanaPrincipal vista = new VentanaPrincipal();

                // 3. El Controlador une a ambos y toma el mando de la ejecución
                new DomoticaControlador(vista, modelo);

                // 4. Hacemos visible la ventana del panel de control
                vista.setVisible(true);
            }
        });
    }
}
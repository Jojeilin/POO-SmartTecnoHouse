package modelo;

// Usamos 'abstract' para que no se puedan crear objetos "Actuador" genéricos.
// Usamos 'implements IDispositivo' para heredar el contrato de identificación de la casa.
public abstract class Actuador implements IDispositivo {

    // Atributos comunes a cualquier aparato que realiza una acción
    protected String id;
    protected String nombre;
    protected String estado; // Guardará textos como "ON", "OFF", "HIGH", "SUBIDA", etc.

    // Constructor común: todos los actuadores necesitan identificarse y empezar en un estado conocido
    public Actuador(String id, String nombre, String estadoInicial) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estadoInicial;
    }

    // --- IMPLEMENTACIÓN DE LOS MÉTODOS DE LA INTERFAZ (IDispositivo) ---

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getEstadoActual() {
        // Devuelve el estado en texto plano para que la interfaz gráfica lo muestre directamente
        return this.estado;
    }

    // --- MÉTODOS POLIMÓRFICOS PROPIOS DE LOS ACTUADORES ---

    /**
     * MÉTODO POLIMÓRFICO: Cada aparato reacciona de forma única a los comandos.
     * Una bombilla entenderá "ON", pero un ventilador necesitará "HIGH" o "LOW".
     * Al ser abstract, delegamos la validación y ejecución a los hijos concretos.
     */
    public abstract void ejecutarAccion(String accion);

    /**
     * MÉTODO POLIMÓRFICO: Devuelve una lista con las palabras clave válidas para este aparato.
     * Es extremadamente útil para que la GUI (Vista) cree menús desplegables automáticos
     * con las opciones reales de cada dispositivo.
     */
    public abstract String[] getAccionesPosibles();
}
/**
 * Clase abstracta que define el comportamiento común de todos los actuadores (aparatos que reciben órdenes).
 * Implementa IDispositivo para cumplir con el contrato mínimo del sistema domótico.
 */
public abstract class Actuador implements IDispositivo {

    protected String id;
    protected String nombre;
    protected String estado; // Almacena el estado actual (ej. "ON", "OFF", "SUBIDA", "BAJADA")

    /**
     * Constructor para inicializar los datos básicos de cualquier actuador.
     */
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
        return this.estado;
    }

    // --- GESTIÓN DE ACCIONES Y LOGGING ---

    /**
     * Método público principal para ejecutar un comando.
     * Coordina de forma transparente el procesamiento de la acción y el registro automático en el log.
     * Se declara 'final' para que los hijos no puedan romper esta secuencia de control.
     */
    public final void ejecutarAccion(String accion) {
        String estadoAnterior = this.estado;

        // Delegamos en el método protegido para que el hijo específico cambie el estado
        procesarComandoEspecifico(accion);

        // Si tras procesar el comando el estado ha cambiado, disparamos el servicio de Logging (Singleton)
        if (!estadoAnterior.equals(this.estado)) {
            LogService.getInstancia().registrarCambio(this, accion);
        }
    }

    /**
     * MÉTODO POLIMÓRFICO INTERNO: Cada actuador concreto (Bombilla, Persiana, etc.)
     * implementará aquí sus propias validaciones y reglas para cambiar la variable 'this.estado'.
     */
    protected abstract void procesarComandoEspecifico(String accion);

    /**
     * MÉTODO POLIMÓRFICO: Devuelve la lista de comandos válidos que acepta el dispositivo.
     */
    public abstract String[] getAccionesPosibles();
}
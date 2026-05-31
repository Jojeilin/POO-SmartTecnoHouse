package modelo;

    public abstract class Sensor implements IDispositivo {
        protected String id;
        protected String nombre;
        protected double ultimoValor;
        protected String unidadMedida;

        public Sensor(String id, String nombre, String unidadMedida) {
            this.id = id;
            this.nombre = nombre;
            this.unidadMedida = unidadMedida;
            this.ultimoValor = 0.0;
        }

        @Override
        public String getID() { return id; }

        @Override
        public String getNombre() { return nombre; }

        @Override
        public String getEstadoActual() {
            return ultimoValor + " " + unidadMedida;
        }

        public double getValor() { return ultimoValor; }

        // Forzamos a que cada sensor simule sus datos de forma única
        public abstract void actualizarValor();
}

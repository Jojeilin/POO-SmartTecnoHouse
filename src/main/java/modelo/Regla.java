package modelo;

import java.util.List;

public interface Regla {
    String getNombre();
    String getDescripcion();
    void aplicar(List<Sensor> sensores, List<Actuador> actuadores);
}

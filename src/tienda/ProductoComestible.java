package tienda;

import java.time.LocalDate;

public interface ProductoComestible {
    LocalDate getFechaVencimiento();
    int getCalorias();
}
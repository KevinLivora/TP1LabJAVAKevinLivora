package tienda;

public class ProductoLimpieza extends ProductoBase {
    public enum TipoAplicacion {
        COCINA, BAÑO, ROPA, MULTIUSO
    }

    private TipoAplicacion tipoAplicacion;

    public ProductoLimpieza(String identificador, String descripcion, int cantidadStock,
                            double precioPorUnidad, double porcentajeGanancia,
                            TipoAplicacion tipoAplicacion) {
        super(identificador, descripcion, cantidadStock, precioPorUnidad, porcentajeGanancia);
        this.tipoAplicacion = tipoAplicacion;
        validarPorcentajeGanancia(porcentajeGanancia);
    }

    private void validarPorcentajeGanancia(double porcentajeGanancia) {
        if (tipoAplicacion == TipoAplicacion.COCINA || tipoAplicacion == TipoAplicacion.MULTIUSO) {
            if (porcentajeGanancia > 0.25) {
                throw new IllegalArgumentException("El porcentaje de ganancia para productos de limpieza de cocina y multiuso no puede ser mayor al 25%");
            }
        } else {
            if (porcentajeGanancia < 0.10 || porcentajeGanancia > 0.25) {
                throw new IllegalArgumentException("El porcentaje de ganancia para productos de limpieza debe estar entre 10% y 25%");
            }
        }
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje <= 0.20) {
            super.aplicarDescuento(porcentaje);
        } else {
            throw new IllegalArgumentException("El descuento para productos de limpieza no puede superar el 20%");
        }
    }
}
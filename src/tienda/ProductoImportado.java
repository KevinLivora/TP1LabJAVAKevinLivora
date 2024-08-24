package tienda;

public abstract class ProductoImportado extends ProductoBase {
    private static final double IMPUESTO_IMPORTACION = 0.12;

    public ProductoImportado(String identificador, String descripcion, int cantidadStock,
                             double precioPorUnidad, double porcentajeGanancia) {
        super(identificador, descripcion, cantidadStock, precioPorUnidad, porcentajeGanancia);
    }

    @Override
    public double calcularPrecioVenta() {
        double precioBase = super.calcularPrecioVenta();
        return precioBase * (1 + IMPUESTO_IMPORTACION);
    }
}
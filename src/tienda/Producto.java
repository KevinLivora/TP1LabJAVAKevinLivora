package tienda;

public interface Producto {
    String getIdentificador();
    String getDescripcion();
    int getCantidadStock();
    void setCantidadStock(int cantidadStock);
    double getPrecioPorUnidad();
    double getPorcentajeGanancia();
    boolean isDisponibleParaVenta();
    void setDisponibleParaVenta(boolean disponible);
    double calcularPrecioVenta();
    void aplicarDescuento(double porcentaje);
    double getDescuento();
}
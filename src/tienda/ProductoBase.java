package tienda;

public abstract class ProductoBase implements Producto {
    private String identificador;
    private String descripcion;
    private int cantidadStock;
    private double precioPorUnidad;
    private double porcentajeGanancia;
    private boolean disponibleParaVenta;
    private double descuento;

    public ProductoBase(String identificador, String descripcion, int cantidadStock,
                        double precioPorUnidad, double porcentajeGanancia) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precioPorUnidad = precioPorUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponibleParaVenta = true;
        this.descuento = 0;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int getCantidadStock() {
        return cantidadStock;
    }

    @Override
    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    @Override
    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    @Override
    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    @Override
    public boolean isDisponibleParaVenta() {
        return disponibleParaVenta;
    }

    @Override
    public void setDisponibleParaVenta(boolean disponible) {
        this.disponibleParaVenta = disponible;
    }

    @Override
    public double calcularPrecioVenta() {
        double precioBase = precioPorUnidad * (1 + porcentajeGanancia);
        return precioBase * (1 - descuento);
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        this.descuento = porcentaje;
    }

    @Override
    public double getDescuento() {
        return descuento;
    }
}
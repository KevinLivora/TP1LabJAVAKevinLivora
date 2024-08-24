package tienda;

import java.time.LocalDate;

public class ProductoEnvasado extends ProductoImportado implements ProductoComestible {
    private String tipoEnvase;
    private LocalDate fechaVencimiento;
    private int calorias;

    public ProductoEnvasado(String identificador, String descripcion, int cantidadStock,
                            double precioPorUnidad, double porcentajeGanancia,
                            String tipoEnvase, LocalDate fechaVencimiento, int calorias) {
        super(identificador, descripcion, cantidadStock, precioPorUnidad, porcentajeGanancia);
        this.tipoEnvase = tipoEnvase;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public int getCalorias() {
        return calorias;
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje <= 0.15) {
            super.aplicarDescuento(porcentaje);
        } else {
            throw new IllegalArgumentException("El descuento para productos envasados no puede superar el 15%");
        }
    }
}
package tienda;

import java.time.LocalDate;

public class Bebida extends ProductoImportado implements ProductoComestible {
    private double graduacionAlcoholica;
    private LocalDate fechaVencimiento;
    private int calorias;

    public Bebida(String identificador, String descripcion, int cantidadStock,
                  double precioPorUnidad, double porcentajeGanancia,
                  double graduacionAlcoholica, LocalDate fechaVencimiento, int calorias) {
        super(identificador, descripcion, cantidadStock, precioPorUnidad, porcentajeGanancia);
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public int getCalorias() {
        if (graduacionAlcoholica <= 2) {
            return calorias;
        } else if (graduacionAlcoholica <= 4.5) {
            return (int) (calorias * 1.25);
        } else {
            return (int) (calorias * 1.5);
        }
    }

    @Override
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje <= 0.10) {
            super.aplicarDescuento(porcentaje);
        } else {
            throw new IllegalArgumentException("El descuento para bebidas no puede superar el 10%");
        }
    }
}
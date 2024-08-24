package tienda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tienda {
    private String nombre;
    private int maxProductosStock;
    private double saldoCaja;
    private List<Producto> productos;

    public Tienda(String nombre, int maxProductosStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxProductosStock = maxProductosStock;
        this.saldoCaja = saldoCaja;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        if (getTotalUnidades() + producto.getCantidadStock() > maxProductosStock) {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock");
            return;
        }

        double costoTotal = producto.getPrecioPorUnidad() * producto.getCantidadStock();
        if (saldoCaja < costoTotal) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
            return;
        }

        productos.add(producto);
        saldoCaja -= costoTotal;
        System.out.println("Producto agregado: " + producto.getDescripcion());
        System.out.println("Saldo en caja: " + saldoCaja);
    }

    public void venderProductos(List<Producto> productosVenta) {
        if (productosVenta.size() > 3) {
            System.out.println("No se pueden vender más de 3 productos en una venta");
            return;
        }

        double totalVenta = 0;
        boolean hayProductosConStockInsuficiente = false;
        boolean hayProductosNoDisponibles = false;

        System.out.println("Detalle de la venta:");

        for (Producto producto : productosVenta) {
            int cantidadVendida = Math.min(producto.getCantidadStock(), 12);

            if (!producto.isDisponibleParaVenta()) {
                System.out.println("El producto " + producto.getIdentificador() + " " + producto.getDescripcion() + " no se encuentra disponible");
                hayProductosNoDisponibles = true;
                continue;
            }

            if (cantidadVendida < 12) {
                hayProductosConStockInsuficiente = true;
            }

            double precioVenta = producto.calcularPrecioVenta();
            totalVenta += precioVenta * cantidadVendida;

            System.out.printf("%s %s %d x %.2f%n",
                    producto.getIdentificador(),
                    producto.getDescripcion(),
                    cantidadVendida,
                    precioVenta);

            producto.setCantidadStock(producto.getCantidadStock() - cantidadVendida);
            if (producto.getCantidadStock() == 0) {
                producto.setDisponibleParaVenta(false);
            }
        }

        System.out.printf("TOTAL VENTA: %.2f%n", totalVenta);

        if (hayProductosConStockInsuficiente) {
            System.out.println("Hay productos con stock disponible menor al solicitado");
        }

        if (hayProductosNoDisponibles) {
            System.out.println("Algunos productos no estaban disponibles para la venta");
        }

        saldoCaja += totalVenta;
        System.out.println("Saldo en caja después de la venta: " + saldoCaja);
    }

    public List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        return productos.stream()
                .filter(p -> p instanceof ProductoComestible)
                .filter(p -> !(p instanceof ProductoImportado))
                .filter(p -> p.getDescuento() < porcentajeDescuento)
                .map(Producto::getDescripcion)
                .map(String::toUpperCase)
                .sorted((a, b) -> Double.compare(
                        productos.stream().filter(p -> p.getDescripcion().equalsIgnoreCase(a)).findFirst().get().calcularPrecioVenta(),
                        productos.stream().filter(p -> p.getDescripcion().equalsIgnoreCase(b)).findFirst().get().calcularPrecioVenta()
                ))
                .collect(Collectors.toList());
    }

    private int getTotalUnidades() {
        return productos.stream().mapToInt(Producto::getCantidadStock).sum();
    }
}
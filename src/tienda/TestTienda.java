package tienda;

import java.time.LocalDate;
import java.util.List;

public class TestTienda {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Mi Tienda", 1000, 10000);

        // Crear productos de diferentes tipos
        ProductoEnvasado cafe = new ProductoEnvasado("AB122", "Café", 100, 1.5, 0.2,
                "Lata", LocalDate.now().plusMonths(6), 10);
        Bebida vino = new Bebida("AC345", "Vino tinto", 50, 10, 0.3,
                12.5, LocalDate.now().plusYears(2), 85);
        ProductoLimpieza detergente = new ProductoLimpieza("AZ789", "Detergente", 200, 5, 0.15,
                ProductoLimpieza.TipoAplicacion.ROPA);

        // Agregar productos a la tienda
        tienda.agregarProducto(cafe);
        tienda.agregarProducto(vino);
        tienda.agregarProducto(detergente);

        // Aplicar descuentos
        cafe.aplicarDescuento(0.05);
        vino.aplicarDescuento(0.10);
        detergente.aplicarDescuento(0.15);

        // Realizar ventas
        List<Producto> venta1 = List.of(cafe, vino);
        System.out.println("\nRealizando venta 1:");
        tienda.venderProductos(venta1);

        List<Producto> venta2 = List.of(detergente, cafe, vino);
        System.out.println("\nRealizando venta 2:");
        tienda.venderProductos(venta2);

        // Mostrar productos comestibles con menor descuento
        List<String> comestiblesMenorDescuento = tienda.obtenerComestiblesConMenorDescuento(0.1);
        System.out.println("\nComestibles con menor descuento: " + String.join(", ", comestiblesMenorDescuento));
    }
}
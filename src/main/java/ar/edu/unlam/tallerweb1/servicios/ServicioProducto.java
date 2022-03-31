package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Producto;

import java.util.List;

public interface ServicioProducto {

    List<Producto> getProductosConStock(Integer stock);

}

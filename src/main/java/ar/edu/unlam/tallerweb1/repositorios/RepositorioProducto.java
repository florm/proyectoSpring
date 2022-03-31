package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Producto;

import java.util.List;

public interface RepositorioProducto {

    Producto guardar(Producto producto);

    List<Producto> listarProductosConStockMayor(Integer stock);
}

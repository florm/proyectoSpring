package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Factura;

import java.util.List;

public interface RepositorioFactura {
    List<Factura> buscarPor(Cliente cliente1);
}

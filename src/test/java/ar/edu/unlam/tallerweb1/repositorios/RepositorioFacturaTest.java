package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioFacturaTest extends SpringTest {

    @Autowired
    private RepositorioFactura repositorioFactura;

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarLasFacturasDeUnCliente(){
        Cliente cliente1 = new Cliente("Benjamin");
        Factura factura = new Factura();
        factura.setCliente(cliente1);
        session().save(factura);

        Factura factura2 = new Factura();
        factura2.setCliente(cliente1);
        session().save(factura2);

        List<Factura> facturasBuscadas2 = repositorioFactura.buscarPor(cliente1);

        assertThat(facturasBuscadas2).isNotNull();
        assertThat(facturasBuscadas2).hasSize(2);
    }

}

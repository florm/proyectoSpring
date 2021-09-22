package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuelaImpl;
import org.hibernate.SessionFactory;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ServicioEscuelaTest {


    private ServicioEscuela servicioEscuela = new ServicioEscuelaImpl(mock(RepositorioEscuela.class));

    @Test
    public void cuandoBuscoEscuelasPorNombreMeTraeLasEscuelasDeEseNombre(){
        givenExisteEscuela();
        List<Escuela> escuelasBuscadas = whenBuscoEscuelaPorNombre("Juan");
        thenLaBusquedaEsExitosa(escuelasBuscadas);
    }

    private void thenLaBusquedaEsExitosa(List<Escuela> escuelasBuscadas) {
        assertThat(escuelasBuscadas).isNotNull();
    }

    private List<Escuela> whenBuscoEscuelaPorNombre(String nombre) {
        return servicioEscuela.buscarPor(nombre);
    }

    private void givenExisteEscuela() {
    }
}

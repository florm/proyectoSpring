package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RepositorioEscuelaTest extends SpringTest {

    @Autowired
    RepositorioEscuela repositorioEscuela;

    @Test
    @Transactional
    @Rollback
    public void siGuardoUnaEscuelaSePersiste(){
        Escuela escuela = givenExisteEscuela();
        escuela.setNombre("Pedro 1");
        Long id = whenGuardoLaEscuela(escuela);
        thenLaPuedoBuscarPorId(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarEscuelasPorNombre(){
        Escuela escuela1 = givenExisteEscuela("Pedro");
        Escuela escuela2 = givenExisteEscuela("Juan");
        Escuela escuela3 = givenExisteEscuela("Juan");
        givenGuardoEscuela(escuela1);
        givenGuardoEscuela(escuela2);
        givenGuardoEscuela(escuela3);
        List<Escuela> buscadas = whenBuscoEscuelaPorNombre("Juan");
        Integer cantidadEsperada = 2;
        thenObtengoEscuelas(buscadas, cantidadEsperada);

    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarTodasLasEscuelas(){
        Escuela escuela1 = givenExisteEscuela("Pedro");
        Escuela escuela2 = givenExisteEscuela("Juan 1");
        Escuela escuela3 = givenExisteEscuela("Juan 2");
        givenGuardoEscuela(escuela1);
        givenGuardoEscuela(escuela2);
        givenGuardoEscuela(escuela3);
        Integer cantidadEsperada = 3;
        List<Escuela> buscadas = whenBuscoEscuelas();
        thenObtengoEscuelas(buscadas, cantidadEsperada);
    }

    private List<Escuela> whenBuscoEscuelas() {
        return repositorioEscuela.buscarTodas();
    }

    private void thenObtengoEscuelas(List<Escuela> buscadas, Integer cantidadEsperada) {
        assertThat(buscadas).hasSize(cantidadEsperada);
    }

    private List<Escuela> whenBuscoEscuelaPorNombre(String nombre) {
        List<Escuela> buscadas = repositorioEscuela.buscarPor(nombre);
        return buscadas;
    }

    private void givenGuardoEscuela(Escuela escuela) {
        repositorioEscuela.guardar(escuela);
    }

    private Escuela givenExisteEscuela() {
        return new Escuela();
    }

    private Escuela givenExisteEscuela(String nombre) {
        Escuela escuela = new Escuela();
        escuela.setNombre(nombre);
        return escuela;
    }

    private Long whenGuardoLaEscuela(Escuela escuela) {
        repositorioEscuela.guardar(escuela);
        return escuela.getId();
    }

    private void thenLaPuedoBuscarPorId(Long id) {
        Escuela escuela = repositorioEscuela.buscarPor(id);
        assertThat(escuela).isNotNull();
    }
}

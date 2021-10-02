package ar.edu.unlam.tallerweb1.repositorios;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class RepositorioEscuelaTest extends SpringTest {

    @Autowired
    private RepositorioEscuela repositorioEscuela;

    @Test
    @Transactional
    @Rollback
    public void guardarUnaEscuelaDeberiaPersistirla(){
        Escuela escuela = givenExisteUnaEscuela("Juan");
        Long id = whenGuardoEscuela(escuela);
        thenEncuentroLaEscuela(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarEscuelasPorNombre(){
        Escuela escuela1 = givenExisteUnaEscuela("Juan");
        Escuela escuela2 = givenExisteUnaEscuela("Pedro");
        Escuela escuela3 = givenExisteUnaEscuela("Juan");
        givenGuardoEscuela(escuela1);
        givenGuardoEscuela(escuela2);
        givenGuardoEscuela(escuela3);
        List<Escuela> escuelasBuscadas = whenBuscoEscuelaPorNombre("Juan");
        Integer cantidadEsperada = 2;
        thenEncuentroLaEscuelaConNombre(escuelasBuscadas, cantidadEsperada);
    }

    private List<Escuela> whenBuscoEscuelaPorNombre(String nombre) {
        return repositorioEscuela.buscarPor(nombre);
    }


    @Test
    @Transactional
    @Rollback
    public void sePuedenBuscarTodasLasEscuelas(){
        Escuela escuela1 = givenExisteUnaEscuela("Juan");
        Escuela escuela2 = givenExisteUnaEscuela("Pedro");
        Escuela escuela3 = givenExisteUnaEscuela("Juan");
        givenGuardoEscuela(escuela1);
        givenGuardoEscuela(escuela2);
        givenGuardoEscuela(escuela3);
        Integer cantidadEsperada = 3;
        List<Escuela> todas = whenBuscoTodasLasEscuelas();
        thenEncuentroTodasLasEscuelas(todas, cantidadEsperada);

    }

    private List<Escuela> whenBuscoTodasLasEscuelas() {
        return repositorioEscuela.buscarTodas();
    }

    private void thenEncuentroTodasLasEscuelas(List<Escuela> todas, Integer cantidadEsperada) {
        assertThat(todas).hasSize(cantidadEsperada);
    }

    private void thenEncuentroLaEscuelaConNombre(List<Escuela> escuelasBuscadas, int cantidadEsperada) {
        assertThat(escuelasBuscadas).hasSize(cantidadEsperada);
    }

    private void givenGuardoEscuela(Escuela escuela) {
        repositorioEscuela.guardar(escuela);
    }

    private void thenEncuentroLaEscuela(Long id) {
        Escuela escuela = repositorioEscuela.buscarPor(id);
        assertThat(escuela).isNotNull();
    }

    private Long whenGuardoEscuela(Escuela escuela) {
        repositorioEscuela.guardar(escuela);
        return escuela.getId();
    }

    private Escuela givenExisteUnaEscuela(String nombre) {
        Escuela escuela = new Escuela();
        escuela.setNombre(nombre);
        return escuela;
    }
}

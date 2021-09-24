package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

public class PersistenciaEscuelaTest extends SpringTest {


    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUnaEscuela(){
        Escuela escuela = givenExisteEscuela("Juan");
        Long id = whenGuardoLaEscuela(escuela);
        thenLaPuedoBuscarPorId(id);
    }

    @Test
    @Transactional
    @Rollback
    public void puedoModificarUnaEscuela(){
        Escuela escuela = givenExisteEscuela("Juan");
        Long id = givenPersistoLaEscuela(escuela);
        String nuevoNombre = "Juan1";
        whenModificoLaEscuela(escuela, nuevoNombre);
        thenLaEscuelaTieneNuevoNombre(id, nuevoNombre);

    }

    private void thenLaEscuelaTieneNuevoNombre(Long id, String nuevoNombre) {
        Escuela buscada = session().get(Escuela.class, id);
        assertThat(buscada.getNombre()).isEqualTo(nuevoNombre);
    }

    private void whenModificoLaEscuela(Escuela escuela, String nuevoNombre) {
        escuela.setNombre(nuevoNombre);
        session().update(escuela);
    }

    private Long givenPersistoLaEscuela(Escuela escuela) {
        session().save(escuela);
        return escuela.getId();
    }

    private Escuela givenExisteEscuela(String nombre) {
        Escuela escuela = new Escuela();
        escuela.setNombre(nombre);
        return escuela;
    }

    private Long whenGuardoLaEscuela(Escuela escuela) {
        session().save(escuela);
        return escuela.getId();
    }

    private void thenLaPuedoBuscarPorId(Long id) {
        Escuela buscada = session().get(Escuela.class, id);
        assertThat(buscada).isNotNull();
    }
}

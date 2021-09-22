package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.hibernate.annotations.ColumnTransformer;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

public class PersistenciaEscuelaTest extends SpringTest {

    /*
    * 1. Que se pueda guardar una escuela
    * 2. Puedo modificar escuela
    * */

    @Test
    @Transactional
    @Rollback
    public void sePuedeGuardarUnaEscuela(){
        Escuela escuela = givenExisteEscuela();
        escuela.setNombre("Pedro 1");
        Long id = whenGuardoLaEscuela(escuela);
        thenLaPuedoBuscarPorId(id);

    }

    @Test
    @Transactional
    @Rollback
    public void puedoModificarElNombreDeUnaEscuela(){
        Escuela escuela = givenExisteEscuela("Pedro 1");
        givenPersistoLaEscuela(escuela);
        String nuevoNombre = "Juan 1";
        givenCambioNombre(escuela, nuevoNombre);
        Long id = whenModificoLaEscuela(escuela);
        thenLaEscuelaTienNombreNuevo(id, nuevoNombre);
    }

    private void thenLaEscuelaTienNombreNuevo(Long id, String nuevoNombre) {
        Escuela buscada = session().get(Escuela.class, id);
        assertThat(buscada.getNombre()).isEqualTo(nuevoNombre);
    }

    private Long whenModificoLaEscuela(Escuela escuela) {
        session().update(escuela);
        return escuela.getId();
    }

    private void givenCambioNombre(Escuela escuela, String nuevoNombre) {
        escuela.setNombre(nuevoNombre);
    }

    private void givenPersistoLaEscuela(Escuela escuela) {
        session().save(escuela);
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
        session().save(escuela);
        return escuela.getId();
    }

    private void thenLaPuedoBuscarPorId(Long id) {
        Escuela buscada = session().get(Escuela.class, id);
        assertThat(buscada).isNotNull();
    }
}

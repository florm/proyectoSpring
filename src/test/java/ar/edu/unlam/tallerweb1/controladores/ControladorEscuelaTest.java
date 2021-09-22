package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuelaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuela;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuelaImpl;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ControladorEscuelaTest {

    ModelAndView mav;
    ServicioEscuela servicioEscuela = new ServicioEscuelaImpl(mock(RepositorioEscuela.class));
    ControladorEscuela controladorEscuela = new ControladorEscuela(servicioEscuela);
    @Test
    public void cuandoBuscoEscuelasPorNombreMeTraeLasEscuelasDeEseNombre(){
        givenExisteEscuela();
        whenBuscoEscuelaPorNombre("Juan");
        thenLaBusquedaEsExitosa();
    }

    @Test
    public void siNoEncuentraEscuelasInformaConUnMensaje(){
        givenExisteEscuela();
        whenBuscoEscuelaPorNombre("Pablo");
        thenObtengoUnMensajeDeListaVacia("Pablo");
    }

    private void thenObtengoUnMensajeDeListaVacia(String nombre) {
        assertThat(mav.getModel().get("sinResultado")).isEqualTo("No se han encontrado Escuela con el nombre " + nombre);
    }


    private void givenExisteEscuela() {

    }

    private void whenBuscoEscuelaPorNombre(String nombre) {
        mav = controladorEscuela.buscarPorNombre(nombre);
    }

    private void thenLaBusquedaEsExitosa() {
        assertThat(mav.getViewName()).isEqualTo("escuelas");
        assertThat(mav.getModel().get("listaEscuelas")).isNotNull();
    }
}

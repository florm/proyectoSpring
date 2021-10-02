package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.CampoVacioException;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuela;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorEscuelaTest {

    private ModelAndView mav;
    private static final String NOMBRE = "Pestalozzi";
    private ServicioEscuela servicioEscuela = mock(ServicioEscuela.class);
    private ControladorEscuela controladorEscuela = new ControladorEscuela(servicioEscuela);

    @Test
    public void siLaEscuelaTieneNombreSeGuardaConExito(){
        DatosEscuela datosEscuela = givenExistenDatosDeEscuela(NOMBRE);
        whenGuardoEscuelaConNombre(datosEscuela);
        thenLaEscuelaSeCreaCorrectamente();
    
    }

    @Test
    public void siLaEscuelaNoTieneNombreFallaLaCreacion(){
        DatosEscuela datosEscuela = givenExistenDatosDeEscuela();
        doThrow(CampoVacioException.class)
                .when(servicioEscuela)
                .guardar(datosEscuela);
        whenGuardoEscuelaConNombre(datosEscuela);
        thenLaCreacionFalla();
    }


    private DatosEscuela givenExistenDatosDeEscuela(String nombre) {
        return new DatosEscuela(nombre);

    }

    private DatosEscuela givenExistenDatosDeEscuela() {
        return new DatosEscuela();

    }

    private void whenGuardoEscuelaConNombre(DatosEscuela datosEscuela) {
        mav = controladorEscuela.guardar(datosEscuela);
    }

    private void thenLaEscuelaSeCreaCorrectamente() {
        assertThat(mav.getViewName()).isEqualTo("listaEscuelas");
        assertThat(mav.getModel().get("listaEscuelas")).isNotNull();
    }

    private void thenLaCreacionFalla() {
        assertThat(mav.getViewName()).isEqualTo("crearEscuela");
        assertThat(mav.getModel().get("error")).isEqualTo("Debe indicar un nombre de escuela");
    }


}

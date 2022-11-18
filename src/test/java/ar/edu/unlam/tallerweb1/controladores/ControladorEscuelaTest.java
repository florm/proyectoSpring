package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.servicios.CampoVacioException;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuela;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        String prueba = System.getProperty("user.dir") + "\\carpetaCreada";


        File fn = new File("carpetaNueva/archivoNuevo.txt");
        var b =  fn.getAbsolutePath();
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
        assertThat(mav.getViewName()).isEqualTo("redirect:/listar-escuelas");
    }

    private void thenLaCreacionFalla() {
        assertThat(mav.getViewName()).isEqualTo("crearEscuela");
        assertThat(mav.getModel().get("error")).isEqualTo("Debe indicar un nombre de escuela");
    }


}

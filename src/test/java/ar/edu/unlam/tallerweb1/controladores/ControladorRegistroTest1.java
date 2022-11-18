package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest1 {

    ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
    ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuario);

    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso(){

        //preparacion => given
        givenNoExisteUsuario();
        //ejecucion => when
        DatosRegistro datosRegistro = new DatosRegistro("test@test.com", "1234", "1234");
        ModelAndView mav = whenRegistroUsuario(datosRegistro);
        //comprobacion => then
        thenElRegistroEsExitoso(mav);
    }

    @Test
    public void siElUsuarioNoExisteYLasClavesSonDistintasElRegistroFalla(){

        //preparacion => given
        givenNoExisteUsuario();
        //ejecucion => when
        DatosRegistro datosRegistro = new DatosRegistro("test@test.com", "1234", "1234444");

        doThrow(ClavesDistintasException.class)
                .when(servicioUsuario).registrar(datosRegistro);

        ModelAndView mav = whenRegistroUsuario(datosRegistro);
        //comprobacion => then
        thenElRegistroFalla(mav);
    }

    private void givenNoExisteUsuario() {

    }

    private ModelAndView whenRegistroUsuario(DatosRegistro datosRegistro) {
        return controladorRegistro.registrar(datosRegistro);
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("redirect:/login");
    }

    private void thenElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
        assertThat(mav.getModel().get("error")).isEqualTo("Las claves deben ser iguales");
    }



}

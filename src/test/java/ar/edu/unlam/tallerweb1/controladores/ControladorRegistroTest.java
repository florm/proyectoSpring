package ar.edu.unlam.tallerweb1.controladores;



import ar.edu.unlam.tallerweb1.repositorios.TablaUsuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ControladorRegistroTest {

    /*
     * 1.Si el usuario no existe y las claves son iguales el registro es exitoso >>> OK
     * 2.Si las claves son distintas el registro falla >>> OK
     * 3.Si la clave tiene menoss de 8 caracteres el registro falla >>> OK
     * 4.Si el email es invalido el registro falla >>> TODO
     * 5.Si el usuario existe el registro falla
     * */


    public static final String EMAIL = "flor@gmail.com";
    public static final String CLAVE = "12345789";
    public static final String CLAVE_DISTINTA = "12344555555";
    public static final String CLAVE_LONGITUD_INCORRECTA = "1234";

    private ServicioUsuario servicioUsuario;


    private ControladorRegistro controladorRegistro;
    private ModelAndView mav;


    @Before
    public void init(){
        TablaUsuario.getInstance().reset();
        servicioUsuario = mock(ServicioUsuario.class);
        controladorRegistro = new ControladorRegistro(servicioUsuario);
    }

    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso() {
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = givenDatosDeRegistro(EMAIL, CLAVE, CLAVE);
        whenRegistroUnUsuarioConClaves(datosRegistro);
        thenElregistroEsExitoso();
    }

    @Test
    public void siLasClavesSonDistintasElRegistroFalla() {
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = givenDatosDeRegistro(EMAIL, CLAVE, CLAVE_DISTINTA);
        doThrow(ClavesDistintasException.class)
                .when(servicioUsuario)
                .registrar(datosRegistro);
        whenRegistroUnUsuarioConClaves(datosRegistro);
        thenElRegistroFalla("Las claves deben ser iguales");
    }

    @Test
    public void siLaClaveTieneMenosDeOchoCaracteresElRegistroFalla() {
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = givenDatosDeRegistro(EMAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
        doThrow(ClaveLongitudIncorrectaException.class)
                .when(servicioUsuario)
                .registrar(datosRegistro);
        whenRegistroUnUsuarioConClaves(datosRegistro);
        thenElRegistroFalla("La clave debe tener al menos 8 caracteres");
    }

    @Test
    public void siElUsuarioExisteElRegistroFalla(){
        givenExisteUsuario(EMAIL, CLAVE);
        DatosRegistro datosRegistro = givenDatosDeRegistro(EMAIL, CLAVE, CLAVE);
        doThrow(UsuarioYaExisteException.class)
                .when(servicioUsuario)
                .registrar(datosRegistro);
        whenRegistroUnUsuarioConClaves(datosRegistro);
        thenElRegistroFalla("El usuario ya se encuentra registrado");

    }

    private void whenRegistroUnUsuarioConClaves(DatosRegistro datosRegistro) {
        mav = controladorRegistro.registrar(datosRegistro);
    }

    private DatosRegistro givenDatosDeRegistro(String email, String clave, String repiteClave) {
        DatosRegistro datosRegistro = new DatosRegistro(email, clave, repiteClave);
        return datosRegistro;
    }

    private void givenExisteUsuario(String email, String clave) {
        controladorRegistro.registrar(new DatosRegistro(email, clave, clave));
    }


    private void thenElRegistroFalla(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
    }

    private void givenUsuarioNoExiste() {
    }

    private void thenElregistroEsExitoso() {
        //te muestra una nueva vista
        assertThat(mav.getViewName()).isEqualTo("redirect:/login");


    }
}

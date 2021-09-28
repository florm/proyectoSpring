package ar.edu.unlam.tallerweb1.controladores;



import ar.edu.unlam.tallerweb1.repositorios.TablaUsuario;
import ar.edu.unlam.tallerweb1.servicios.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;


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

    private ServicioUsuario servicioUsuario = new ServicioUsuarioImpl();
    private ControladorRegistro controladorRegistro = new ControladorRegistro(servicioUsuario);
    private ModelAndView mav;

    @Before
    public void init(){
        TablaUsuario.getInstance().reset();
    }

    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso() {
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = givenExisteDatoDeRegistro(EMAIL, CLAVE, CLAVE);
        whenRegistroUnUsuarioCon(datosRegistro);
        thenElregistroEsExitoso();
    }

    @Test
    public void siLasClavesSonDistintasElRegistroFalla() {
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = givenExisteDatoDeRegistro(EMAIL, CLAVE, CLAVE_DISTINTA);
        whenRegistroUnUsuarioCon(datosRegistro);
        thenElRegistroFalla("Las claves deben ser iguales");
    }

    @Test
    public void siLaClaveTieneMenosDeOchoCaracteresElRegistroFalla() {
        givenUsuarioNoExiste();
        DatosRegistro datosRegistro = givenExisteDatoDeRegistro(EMAIL, CLAVE_LONGITUD_INCORRECTA, CLAVE_LONGITUD_INCORRECTA);
        whenRegistroUnUsuarioCon(datosRegistro);
        thenElRegistroFalla("La clave debe tener al menos 8 caracteres");
    }

    @Test
    public void siElUsuarioExisteElRegistroFalla(){
        givenExisteUsuario(EMAIL, CLAVE);
        DatosRegistro datosRegistro = givenExisteDatoDeRegistro(EMAIL, CLAVE, CLAVE);
        whenRegistroUnUsuarioCon(datosRegistro);
        thenElRegistroFalla("El usuario ya se encuentra registrado");

    }

    private void givenUsuarioNoExiste() {
    }

    private void givenExisteUsuario(String email, String clave) {
        controladorRegistro.registrar(new DatosRegistro(email, clave, clave));
    }

    private DatosRegistro givenExisteDatoDeRegistro(String email, String clave, String repiteClave) {
        return new DatosRegistro(email, clave, repiteClave);
    }

    private void whenRegistroUnUsuarioCon(DatosRegistro datosRegistro) {
        mav = controladorRegistro.registrar(datosRegistro);
    }


    private void whenRegistroUnUsuarioCon(String email, String clave, String repiteClave) {
        DatosRegistro datosRegistro = new DatosRegistro(email, clave, repiteClave);
        mav = controladorRegistro.registrar(datosRegistro);
    }

    private void thenElRegistroFalla(String mensaje) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
        assertThat(mav.getModel().get("error")).isEqualTo(mensaje);
    }

    private void thenElregistroEsExitoso() {
        //te muestra una nueva vista
        assertThat(mav.getViewName()).isEqualTo("redirect:/login");


    }
}

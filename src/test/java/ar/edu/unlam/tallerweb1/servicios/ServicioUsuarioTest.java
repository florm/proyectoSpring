package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.TablaUsuario;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioUsuarioTest {

    private static final String EMAIL = "flor@gmail.com";
    private static final String CLAVE = "123456789";
    private static final String CLAVE_LONG_INCORRECTA = "1234";

    private ServicioUsuario servicioUsuario;
    private RepositorioUsuario repositorioUsuario;

    @Before
    public void init(){
        TablaUsuario.getInstance().reset();
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario);
    }

    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesElRegistroEsExitoso(){
        givenNoExisteUsuario();
        Usuario usuarioRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElRegistroEsExitoso(usuarioRegistrado);
    }

    @Test(expected = ClavesDistintasException.class)
    public void siLasClavesSonDistintasElRegistroFalla(){
        givenNoExisteUsuario();
        Usuario usuarioRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE+"11111");
        thenElRegistroFalla(usuarioRegistrado);
    }

    @Test(expected = ClaveLongitudIncorrectaException.class)
    public void siLasClavesTienenLongitudIncorectaElRegistroFalla(){
        givenNoExisteUsuario();
        Usuario usuarioRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE_LONG_INCORRECTA, CLAVE_LONG_INCORRECTA);
        thenElRegistroFalla(usuarioRegistrado);
    }

    @Test(expected = UsuarioYaExisteException.class)
    public void siElUsuarioExisteElRegistroFalla(){
        givenExisteUsuario(EMAIL, CLAVE);
        DatosRegistro datosRegistro = new DatosRegistro(EMAIL, CLAVE, CLAVE);
        when(repositorioUsuario.buscar(datosRegistro.getEmail())).thenReturn(new Usuario());
        Usuario usuarioRegistrado = whenRegistroUnUsuario(datosRegistro);
        thenElRegistroFalla(usuarioRegistrado);
    }

    @Test
    public void siRegistroUsuarioTieneMismoEmailQueDatosRegistro(){
        givenNoExisteUsuario();
        Usuario usuarioRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElUsuarioTieneEmailDeRegistro(usuarioRegistrado);

    }

    private void givenExisteUsuario(String email, String clave) {
        servicioUsuario.registrar(new DatosRegistro(email, clave, clave));
    }

    private void givenNoExisteUsuario() {
    }

    private Usuario whenRegistroUnUsuario(String email, String clave, String repiteClave) {
        return servicioUsuario.registrar(new DatosRegistro(email, clave, repiteClave));
    }

    private Usuario whenRegistroUnUsuario(DatosRegistro datosRegistro) {
        return servicioUsuario.registrar(datosRegistro);
    }

    private void thenElUsuarioTieneEmailDeRegistro(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado.getEmail()).isEqualTo(EMAIL);
    }

    private void thenElRegistroEsExitoso(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado).isNotNull();
    }

    private void thenElRegistroFalla(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado).isNull();
    }
}

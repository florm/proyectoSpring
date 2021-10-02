package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.TablaUsuario;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioUsuarioTest {

    private static final String EMAIL = "flor@gmail.com";
    private static final String CLAVE = "123456789";
    private static final String CLAVE_LONG_INCORRECTA = "1234";

    private RepositorioUsuario repositorioUsuario;
    private ServicioUsuarioImpl servicioUsuario;

    @Before
    public void init(){
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

        when(repositorioUsuario.buscar(EMAIL)).thenReturn(new Usuario());

        Usuario usuarioRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElRegistroFalla(usuarioRegistrado);
    }

    @Test
    public void siRegistroUsuarioTieneMismoEmailQueDatosRegistro(){
        givenNoExisteUsuario();
        Usuario usuarioRegistrado = whenRegistroUnUsuario(EMAIL, CLAVE, CLAVE);
        thenElUsuarioTieneEmailDeRegistro(usuarioRegistrado);

    }

    private void thenElUsuarioTieneEmailDeRegistro(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado.getEmail()).isEqualTo(EMAIL);
    }

    private void givenExisteUsuario(String email, String clave) {
        servicioUsuario.registrar(new DatosRegistro(email, clave, clave));
    }

    private void thenElRegistroFalla(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado).isNull();
    }

    private void givenNoExisteUsuario() {
    }

    private Usuario whenRegistroUnUsuario(String email, String clave, String repiteClave) {
        return servicioUsuario.registrar(new DatosRegistro(email, clave, repiteClave));
    }

    private void thenElRegistroEsExitoso(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado).isNotNull();

        //verifica que se llama al repositorioUsuario.guardar(..)
        verify(repositorioUsuario).guardar(usuarioRegistrado);

        //si el repositorioUsuario.guardar(...) NO fuera void se podria utilizar el verify de la siguiente forma, donde
        //times(1) indica la cantidad de veces que se deberia invocar el metodo
        //verify(repositorioUsuario.guardar(usuarioRegistrado), times(1));


    }
}

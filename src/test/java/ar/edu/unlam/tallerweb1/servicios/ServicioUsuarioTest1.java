package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ServicioUsuarioTest1 {

    RepositorioUsuario repositorioUsuario = mock(RepositorioUsuario.class);
    ServicioUsuario servicioUsuario = new ServicioUsuarioImpl(repositorioUsuario);
    
    
    @Test
    public void siElUsuarioNoExisteYLasClavesSonIgualesYConMasDeOchoCaracteresElRegistroEsExitoso(){

        //preparacion => given
        givenNoExisteUsuario();
        //ejecucion => when
        DatosRegistro datosRegistro = new DatosRegistro("test@test.com", "12345678", "12345678");
        Usuario usuarioRegistrado = whenRegistroUsuario(datosRegistro);
        //comprobacion => then
        thenElRegistroEsExitoso(usuarioRegistrado);
    }

    @Test(expected = ClavesDistintasException.class)
    public void siElUsuarioNoExisteYLasClavesSonDistintasYConMasDeOchoCaracteresElRegistroFalla(){

        //preparacion => given
        givenNoExisteUsuario();
        //ejecucion => when
        DatosRegistro datosRegistro = new DatosRegistro("test@test.com", "12345678", "12345678ttttt");
        Usuario usuarioRegistrado = whenRegistroUsuario(datosRegistro);
        //comprobacion => then
        thenElRegistroFalla(usuarioRegistrado);
    }


    @Test(expected = UsuarioYaExisteException.class)
    public void siElUsuarioExisteYLasClavesSonIgualesYConMasDeOchoCaracteresElRegistroFalla(){

        DatosRegistro datosRegistro = new DatosRegistro("test@test.com", "12345678", "12345678");
        //preparacion => given
        givenExisteUsuario(datosRegistro);
        //ejecucion => when

        when(repositorioUsuario.buscar(datosRegistro.getEmail()))
                .thenReturn(new Usuario());

        Usuario usuarioRegistrado = whenRegistroUsuario(datosRegistro);
        //comprobacion => then
        thenElRegistroFalla(usuarioRegistrado);
    }

    private void givenExisteUsuario(DatosRegistro datosRegistro) {
        servicioUsuario.registrar(datosRegistro);
    }

    private void thenElRegistroFalla(Usuario usuarioRegistrado) {
    }

    private void givenNoExisteUsuario() {
    }

    private Usuario whenRegistroUsuario(DatosRegistro datosRegistro) {
        return servicioUsuario.registrar(datosRegistro);
    }

    private void thenElRegistroEsExitoso(Usuario usuarioRegistrado) {
        assertThat(usuarioRegistrado).isNotNull();

        verify(repositorioUsuario, times(1)).guardar(usuarioRegistrado);
//        verify(repositorioUsuario, atLeastOnce()).guardar(usuarioRegistrado);
//        verify(repositorioUsuario, never()).guardar(usuarioRegistrado);

    }

}

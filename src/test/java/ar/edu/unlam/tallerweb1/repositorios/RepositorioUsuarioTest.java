package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

public class RepositorioUsuarioTest extends SpringTest {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void siAgregoUnUsuarioSeDeberiaPersisitir(){
        Usuario usuario = givenExisteUsuario();
        Long id = whenAgregoUsuario(usuario);
        thenPuedoBuscarUsuarioPorId(id);
    }

    @Test
    @Transactional
    @Rollback
    public void siBuscoUsuarioPorEmailDeberiaDevolverUsuario(){
        Usuario usuario1 = givenExisteUsuario("flor@gmail.com");
        Usuario usuario2 = givenExisteUsuario("pablo@gmail.com");
        givenAgregoUsuario(usuario1);
        givenAgregoUsuario(usuario2);
        String emailBuscado = "flor@gmail.com";
        Usuario buscado = whenBuscoUsuarioPorEmail(emailBuscado);
        thenObtengoUsuario(buscado, emailBuscado);

    }

    private Usuario whenBuscoUsuarioPorEmail(String email) {
        Usuario buscado = repositorioUsuario.buscar(email);
        return buscado;
    }

    private void givenAgregoUsuario(Usuario usuario) {
        repositorioUsuario.guardar(usuario);
    }

    private void thenObtengoUsuario(Usuario buscado, String emailBuscado) {
        assertThat(buscado).isNotNull();
        assertThat(buscado.getEmail()).isEqualTo(emailBuscado);
    }

    private Usuario givenExisteUsuario(String email) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        return usuario;
    }

    private void thenPuedoBuscarUsuarioPorId(Long id) {
        Usuario usuarioBuscado = repositorioUsuario.buscarPor(id);
        assertThat(usuarioBuscado).isNotNull();
    }

    private Long whenAgregoUsuario(Usuario usuario) {
        repositorioUsuario.guardar(usuario);
        return usuario.getId();
    }

    private Usuario givenExisteUsuario() {
        return new Usuario();
    }
}

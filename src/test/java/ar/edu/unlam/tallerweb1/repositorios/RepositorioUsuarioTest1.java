package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioUsuarioTest1 extends SpringTest {

    @Autowired
    RepositorioUsuario repositorioUsuario;

    @Test
    @Transactional
    @Rollback
    public void sePuedeGuardarUnUsuario(){

        Usuario usuario = new Usuario(new DatosRegistro("test@test.com", "12345678", "12345678"));
        repositorioUsuario.guardar(usuario);
        assertThat(usuario.getId()).isNotNull();
    }


    @Test
    @Transactional
    @Rollback
    public void sePuedeBuscarUsuariosPorMail(){

        Usuario usuario = new Usuario(new DatosRegistro("test@test.com", "12345678", "12345678"));
        Usuario usuario2 = new Usuario(new DatosRegistro("prueba@prueba.com", "12345678", "12345678"));

        session().save(usuario);
        session().save(usuario2);

        Usuario buscado = repositorioUsuario.buscar("prueba@prueba.com");
        assertThat(buscado).isNotNull();

    }
}

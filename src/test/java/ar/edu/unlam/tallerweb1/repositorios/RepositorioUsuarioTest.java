package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioUsuarioTest extends SpringTest {


    @Autowired
    RepositorioUsuario repo;

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarUsuarioPorEmail(){
        //preparacion
        Usuario usuario1 = new Usuario(new DatosRegistro("test@test.com", "12345", " 12345"));
        Usuario usuario2 = new Usuario(new DatosRegistro("otro@otro.com", "12345", " 12345"));

        session().save(usuario1);
        session().save(usuario2);

        //ejecucion
        Usuario usuarioBuscado = repo.buscar("test@test.com");

        //comprobacion
        assertThat(usuarioBuscado).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void puedoGuardarUsuarios(){
        //preparacion
        Usuario usuario1 = new Usuario(new DatosRegistro("test1@test.com", "12345", " 12345"));

        //ejecucion
        repo.guardar(usuario1);

        Usuario usuarioGuardado = (Usuario) session()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("email", "test1@test.com"))
                .uniqueResult();

        //comprobacion
        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuario1.getId()).isNotNull();
    }
}

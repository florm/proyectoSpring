package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.TablaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario registrar(DatosRegistro datosRegistro) {
        if(lasClavesSonDistintas(datosRegistro)){
            throw new ClavesDistintasException();
        }
        if(laClaveTieneLongitudIncorrecta(datosRegistro)){
            throw new ClaveLongitudIncorrectaException();
        }
        if(repositorioUsuario.buscar(datosRegistro.getEmail()) != null){
            throw new UsuarioYaExisteException();
        }
        Usuario nuevoUsuario = new Usuario(datosRegistro);
        repositorioUsuario.guardar(nuevoUsuario);
        return nuevoUsuario;
    }

    private boolean lasClavesSonDistintas(DatosRegistro datosRegistro) {
        return !datosRegistro.getClave().equals(datosRegistro.getRepiteClave());
    }

    private boolean laClaveTieneLongitudIncorrecta(DatosRegistro datosRegistro) {
        return datosRegistro.getClave().length() < 8;
    }
}

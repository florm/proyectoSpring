package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ServicioEscuelaImpl implements ServicioEscuela {

    private RepositorioEscuela repositorioEscuela;

    @Autowired
    public ServicioEscuelaImpl(RepositorioEscuela repositorioEscuela){
        this.repositorioEscuela = repositorioEscuela;
    }

    @Override
    public List<Escuela> buscarPor(String nombre) {
        return repositorioEscuela.buscarPor(nombre);
    }
}

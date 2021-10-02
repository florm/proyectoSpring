package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.controladores.DatosEscuela;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuela;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioEscuelaImpl implements ServicioEscuela{

    private RepositorioEscuela repositorioEscuela;

    @Autowired
    public ServicioEscuelaImpl(RepositorioEscuela repositorioEscuela){
        this.repositorioEscuela = repositorioEscuela;
    }

    @Override
    public Escuela guardar(DatosEscuela datosEscuela) {
        if(datosEscuela.getNombre() == null) throw new CampoVacioException();

        Escuela escuela = new Escuela(datosEscuela);
        repositorioEscuela.guardar(escuela);
        return escuela;
    }

    @Override
    public List<Escuela> listarTodas() {
        return repositorioEscuela.buscarTodas();
    }
}

package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RepositorioEscuelaImpl implements RepositorioEscuela{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEscuelaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Escuela escuela) {
        sessionFactory.getCurrentSession().save(escuela);
    }

    @Override
    public Escuela buscarPor(Long id) {
        return sessionFactory.getCurrentSession().get(Escuela.class, id);
    }

    @Override
    public List<Escuela> buscarPor(String nombreBuscado) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Escuela.class)
                .add(Restrictions.eq("nombre", nombreBuscado))
                .list();

//        return sessionFactory.getCurrentSession()
//                .createCriteria(Escuela.class)
//                .add(Restrictions.like("nombre", nombreBuscado, MatchMode.ANYWHERE))
//                .list();
    }

    @Override
    public List<Escuela> buscarTodas() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Escuela.class)
                .list();
    }
}

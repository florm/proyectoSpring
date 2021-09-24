package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioEscuelaImpl implements RepositorioEscuela {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioEscuelaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long guardar(Escuela escuela) {
        sessionFactory.getCurrentSession().save(escuela);
        return escuela.getId();
    }

    @Override
    public Escuela buscarPor(Long id) {
        Escuela escuela = sessionFactory.getCurrentSession().get(Escuela.class, id);
        return escuela;
    }

    @Override
    public List<Escuela> buscarPor(String nombreBuscado) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Escuela.class)
                .add(Restrictions.eq("nombre", nombreBuscado))
                .list();
    }
}

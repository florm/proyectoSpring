package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Factura;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioFacturaImpl implements RepositorioFactura{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioFacturaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Factura> buscarPor(Cliente cliente1) {
        return sessionFactory.getCurrentSession().createCriteria(Factura.class)
                .add(Restrictions.eq("cliente", cliente1))
                .list();
    }
}

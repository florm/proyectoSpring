package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Producto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioProductoImpl implements RepositorioProducto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioProductoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Producto guardar(Producto producto) {
        sessionFactory.getCurrentSession().save(producto);
        return producto;
    }

    @Override
    public List<Producto> listarProductosConStockMayor(Integer stock) {
        return sessionFactory.getCurrentSession().createCriteria(Producto.class)
                .add(Restrictions.gt("stock", stock))
                .list();
    }
}

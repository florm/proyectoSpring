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


    /*
    *
    * select * from Factura where Cliente = Cliente1 -NO existe
    * */
    @Override
    public List<Factura> buscarPor(Cliente cliente1) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Factura.class)
                .add(Restrictions.eq("cliente", cliente1))
                .list();
    }

    /*
    * /*
     * select * from Factura f
     * join Cliente clienteBuscado on f.ClientId = clienteBuscado.Id
     * where clienteBuscado.id = 1
    * */
    @Override
    public List<Factura> buscarPorIdCliente(Long clienteId) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Factura.class)
                .createAlias("cliente", "clienteBuscado")
                .add(Restrictions.eq("clienteBuscado.id", clienteId))
                .list();
    }
//
//    @Override
//    public List<Factura> buscarPorNombreCuenta(String nombreCuenta) {
//        return sessionFactory.getCurrentSession()
//                .createCriteria(Factura.class)
//                .createAlias("cliente", "clienteBuscado")
//                .createAlias("clienteBuscado.cuenta", "cuentaBuscada")
//                .add(Restrictions.eq("cuentaBuscada.nombre", nombreCuenta))
//                .list();
//    }

}

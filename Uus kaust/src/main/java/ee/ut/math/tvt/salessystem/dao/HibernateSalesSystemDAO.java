package ee.ut.math.tvt.salessystem.dao;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateSalesSystemDAO{

    //private final List<StockItem> stockItemList;
    //private final List<SoldItem> soldItemList;

    /**private final EntityManagerFactory emf;
    private final EntityManager em;

    public HibernateSalesSystemDAO() {
        // if you get ConnectException / JDBCConnectionException then you
        // probably forgot to start the database before starting the application
        emf = Persistence.createEntityManagerFactory ("pos");
        em = emf.createEntityManager ();
    }


    // TODO implement missing methods
    public void close () {
        em.close ();
        emf.close ();
    }

    @Override
    public List<StockItem> findStockItems() {
        return em.createQuery("from StockItem").getResultList();
        //return new ArrayList<>();
    }

    @Override
    public StockItem findStockItem(long id) {
        return em.find(StockItem.class,id);
        //return em.createQuery("",StockItem.class).getSingleResult();
        //return null;
    }

    @Override
    public void saveStockItem(StockItem stockItem) {
        beginTransaction();
        em.persist(stockItem);
        commitTransaction();
    }

    @Override
    public void saveSoldItem(SoldItem item) {
        //em.setProperty(Integer.toString(Math.toIntExact(item.getId())),item.getQuantity());
        beginTransaction();
        em.persist(item);
        commitTransaction();
    }

    @Override
    public void beginTransaction () {
        em.getTransaction().begin();
    }
    @Override
    public void rollbackTransaction () {
        em.getTransaction().rollback();
    }
    @Override
    public void commitTransaction() {
        em.getTransaction (). commit ();
    }**/

}

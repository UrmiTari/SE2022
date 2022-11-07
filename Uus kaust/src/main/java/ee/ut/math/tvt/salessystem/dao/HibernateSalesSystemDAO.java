package ee.ut.math.tvt.salessystem.dao;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateSalesSystemDAO implements SalesSystemDAO {

    //private final List<StockItem> stockItemList;
    //private final List<SoldItem> soldItemList;

    private final EntityManagerFactory emf;
    private final EntityManager em;

    /**public HibernateSalesSystemDAO() {
        List<StockItem> items = new ArrayList<StockItem>();
        items.add(new StockItem(1L, "Lays chips", "Potato chips", 11.0, 5));
        items.add(new StockItem(2L, "Chupa-chups", "Sweets", 8.0, 8));
        items.add(new StockItem(3L, "Frankfurters", "Beer sauseges", 15.0, 12));
        items.add(new StockItem(4L, "Expensive Beer", "Student's dream", 10.0, 100));
        items.add(new StockItem(4L, "Not Free Beer", "Student's delight", 3.0, 100));
        this.stockItemList = items;
        this.soldItemList = new ArrayList<>();
    }**/
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
        return null;
    }

    @Override
    public StockItem findStockItem(long id) {
        return null;
    }

    @Override
    public void saveStockItem(StockItem stockItem) {

    }

    @Override
    public void saveSoldItem(SoldItem item) {

    }

    @Override
    public void beginTransaction () {
        em.getTransaction (). begin ();
    }
    @Override
    public void rollbackTransaction () {
        em.getTransaction (). rollback ();
    }
    @Override
    public void commitTransaction () {
        em.getTransaction (). commit ();
    }


    /**@Override
    public List<StockItem> findStockItems() {
        return stockItemList;
    }

    @Override
    public StockItem findStockItem(long id) {
        for (StockItem item : stockItemList) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    @Override
    public void saveSoldItem(SoldItem item) {
        /**if (findStockItem(item.getId()).getQuantity() - item.getQuantity() >= 0){
         soldItemList.add(item);
         //item.setQuantity(findStockItem(item.getId()).getQuantity() - item.getQuantity());
         findStockItem(item.getId()).setQuantity(findStockItem(item.getId()).getQuantity() - item.getQuantity());
         }**//**
        soldItemList.add(item);

    }

    @Override
    public void saveStockItem(StockItem stockItem) {
        stockItemList.add(stockItem);
    }

    @Override
    public void beginTransaction() {

    }

    @Override
    public void rollbackTransaction() {
    }

    @Override
    public void commitTransaction() {
    }**/
}

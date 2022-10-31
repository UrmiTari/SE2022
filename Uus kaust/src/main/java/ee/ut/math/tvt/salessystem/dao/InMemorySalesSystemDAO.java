package ee.ut.math.tvt.salessystem.dao;

import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;

import java.util.ArrayList;
import java.util.List;

public class InMemorySalesSystemDAO implements SalesSystemDAO {

    private final List<StockItem> stockItemList;
    private final List<SoldItem> soldItemList;

    public InMemorySalesSystemDAO() {
        List<StockItem> items = new ArrayList<StockItem>();
        items.add(new StockItem(1L, "Lays chips", "Potato chips", 11.0, 5));
        items.add(new StockItem(2L, "Chupa-chups", "Sweets", 8.0, 8));
        items.add(new StockItem(3L, "Frankfurters", "Beer sauseges", 15.0, 12));
        items.add(new StockItem(4L, "Expensive Beer", "Student's dream", 10.0, 100));
        items.add(new StockItem(4L, "Not Free Beer", "Student's delight", 3.0, 100));
        this.stockItemList = items;
        this.soldItemList = new ArrayList<>();
    }

    @Override
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
        if (findStockItem(item.getId()).getQuantity() - item.getQuantity() >= 0){
            soldItemList.add(item);
            //item.setQuantity(findStockItem(item.getId()).getQuantity() - item.getQuantity());
            findStockItem(item.getId()).setQuantity(findStockItem(item.getId()).getQuantity() - item.getQuantity());
        }

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
    }
}

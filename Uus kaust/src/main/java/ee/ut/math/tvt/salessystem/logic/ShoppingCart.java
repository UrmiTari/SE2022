package ee.ut.math.tvt.salessystem.logic;

import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    //private static final Logger log = LogManager.getLogger(ShoppingCart.class); //does not work :(
    private final SalesSystemDAO dao;
    private final List<SoldItem> items = new ArrayList<>();

    public ShoppingCart(SalesSystemDAO dao) {
        this.dao = dao;
    }

    /**
     * Add new SoldItem to table.
     */
    public void addItem(SoldItem item) {
        // TODO In case such stockItem already exists increase the quantity of the existing stock
        // TODO verify that warehouse items' quantity remains at least zero or throw an exception
        if (getAll().size() == 0){
            if (item.getStockItem().getQuantity() - item.getQuantity() >= 0){
                items.add(item);
            }
            else {
                throw new SalesSystemException("Dont have enough stock");
            }
        }
        else {
            for (SoldItem soldItem : items){
                long i = soldItem.getStockItem().getId();
                System.out.println(i);
                long ii = item.getStockItem().getId();
                System.out.println(ii);
                //soldItem.getId();
                if (i == ii){
                    if (soldItem.getStockItem().getQuantity() - item.getQuantity() >= 0){
                        //System.out.println("yay");
                        soldItem.setQuantity(soldItem.getQuantity() + item.getQuantity());
                        //soldItem.getStockItem().setQuantity(soldItem.getQuantity() + item.getQuantity());
                        break;
                    }
                    else {
                        throw new SalesSystemException("Dont have enough stock");
                    }
                        //soldItem.setQuantity(soldItem.getQuantity()+item.getQuantity());
                }
                else{
                    if (soldItem.getStockItem().getQuantity() - item.getQuantity() >= 0){
                        items.add(item);
                        break;
                    }
                    else {
                        throw new SalesSystemException("Dont have enough stock");
                    }
                }
            }
        }

        /**try {
            dao.findStockItem(item.getId())
        } catch (Exception e) {
            e.printStackTrace();
        }**/
        //log.debug("Added " + item.getName() + " quantity of " + item.getQuantity());
    }

    public List<SoldItem> getAll() {
        return items;
    }

    public void cancelCurrentPurchase() {
        items.clear();
    }

    public void submitCurrentPurchase() {
        // TODO decrease quantities of the warehouse stock

        // note the use of transactions. InMemorySalesSystemDAO ignores transactions
        // but when you start using hibernate in lab5, then it will become relevant.
        // what is a transaction? https://stackoverflow.com/q/974596
        //log.info("Begin transaction");
        dao.beginTransaction();
        try {
            for (SoldItem item : items) {
                dao.saveSoldItem(item);
            }
            dao.commitTransaction();
            items.clear();
        } catch (Exception e) {
            dao.rollbackTransaction();
            throw e;
        }
    }
}

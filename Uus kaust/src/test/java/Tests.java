//import static org.junit.Assert.assertEquals;

import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class Tests {
    //List mockedList = mock(List.class);
    private SalesSystemDAO dao;
    private ShoppingCart shoppingCart;
    private StockItem bread;
    @Test
    public void testAddingItemBeginsAndCommitsTransaction () {
        //check that methods
        //beginTransaction and commitTransaction are both called exactly once and
        //that order
        //verify(, calls(1)).beginTransaction();   //find in shoppingcart.java
        //verify(, times(1)).commitTransaction();
        bread = new StockItem(5L,"Bread","Fresh and fluffy", 0.59,10);
        //dao = new InMemorySalesSystemDAO();
        //shoppingCart = new ShoppingCart(dao);
        shoppingCart = new ShoppingCart(dao);
        dao = Mockito.mock(InMemorySalesSystemDAO.class);
        verify(dao,times(1)).beginTransaction();
        verify(dao,times(1)).commitTransaction();
    }
    @Test
    public void testAddingNewItem () {
        //check that a new item is saved through the DAO
        bread = new StockItem(5L,"Bread","Fresh and fluffy", 0.59,10);
        //dao = new InMemorySalesSystemDAO();
        //mock(dao.saveStockItem(bread));
        dao = Mockito.mock(InMemorySalesSystemDAO.class);
        //dao.saveStockItem(bread);
        //shoppingCart = new ShoppingCart(dao);
        verify(dao).saveStockItem(bread);
    }
    @Test
    public void testAddingExistingItem () {
        //check that adding a new item increases the quantity
        //and the saveStockItem method of the DAO is not called

        //verify(, calls(0)).saveStockItem();
        bread = new StockItem(5L,"Bread","Fresh and fluffy", 0.59,10);
        //dao = new InMemorySalesSystemDAO();
        //shoppingCart = new ShoppingCart(dao);
        dao = Mockito.mock(InMemorySalesSystemDAO.class);

    }
    @Test
    public void testAddingItemWithNegativeQuantity () {
        //check that adding an item with
        //negative quantity results in an exception

    }

}

//import static org.junit.Assert.assertEquals;

import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class Tests {
    //List mockedList = mock(List.class);
    private SalesSystemDAO dao;
    private ShoppingCart shoppingCart;
    private StockItem bread;

    @Before
    public void setUp() {
        bread = new StockItem(5L,"Bread","Fresh and fluffy", 0.59,10);
        dao = new InMemorySalesSystemDAO();
    }

    @Test
    public void testAddingItemBeginsAndCommitsTransaction () {
        //check that methods
        //beginTransaction and commitTransaction are both called exactly once and
        //that order
        //verify(, calls(1)).beginTransaction();   //find in shoppingcart.java
        //verify(, times(1)).commitTransaction();
        InMemorySalesSystemDAO daoMock = Mockito.spy(new InMemorySalesSystemDAO());
        InOrder inOrder = inOrder(daoMock);
        daoMock.saveStockItem(item1);
        //shoppingCart = new ShoppingCart(dao);
        //dao = Mockito.mock(InMemorySalesSystemDAO.class);
        inOrder.verify(daoMock,times(1)).beginTransaction();
        inOrder.verify(daoMock,times(1)).commitTransaction();
    }
    @Test
    public void testAddingNewItem () {
        //check that a new item is saved through the DAO
        //dao = new InMemorySalesSystemDAO();
        //mock(dao.saveStockItem(bread));
        //dao = Mockito.mock(InMemorySalesSystemDAO.class);
        dao.saveStockItem(bread);
        StockItem found = dao.findStockItem(bread.getId());
        assertEquals(bread,found);
        //shoppingCart = new ShoppingCart(dao);
        //verify(dao).saveStockItem(bread);
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
    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithNegativeQuantity () {
        //check that adding an item with
        //negative quantity results in an exception
        StockItem tea = new StockItem(10L, "Tea", "black tea", 0.5, -10)
    }

    @Test
    public void testAddingItemWithQuantityTooLarge () {

    }

    @Test
    public void testAddingItemWithQuantitySumTooLarge () {

    }
    @Test
    public void testSubmittingCurrentPurchaseDecreasesStockItemQuantity () {

    }
    @Test
    public void testSubmittingCurrentPurchaseBeginsAndCommitsTransaction () {

    }
    @Test
    public void testSubmittingCurrentOrderCreatesHistoryItem () {

    }
    @Test
    public void testSubmittingCurrentOrderSavesCorrectTime () {

    }
    @Test
    public void testCancellingOrder () {

    }
    @Test
    public void testCancellingOrderQuanititesUnchanged () {

    }
}

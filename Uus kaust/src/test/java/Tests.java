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
    private StockItem tea;
    private StockItem coco;
    private StockItem buns;

    @Before
    public void setUp() {
        bread = new StockItem(1L,"Bread","Fresh and fluffy", 0.59,10);
        tea = new StockItem(2L, "Tea", "black tea", 0.5, 10);
        coco = new StockItem(3L, "coco", "warm", 1.5, 10);
        buns = new StockItem(4L, "buns", "golden", 0.25, 10);
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
        StockItem tea = new StockItem(10L, "Tea", "black tea", 0.5, -10);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithQuantityTooLarge () {
        SoldItem bread2 = new SoldItem(bread, 1000);
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        shoppingCart.addItem(bread2);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithQuantitySumTooLarge () {
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        SoldItem bread2 = new SoldItem(bread, 2);
        shoppingCart.addItem(bread2);
        SoldItem bread3 = new SoldItem(bread, 3);
        shoppingCart.addItem(bread3);
    }
    @Test
    public void testSubmittingCurrentPurchaseDecreasesStockItemQuantity () {
        ShoppingCart shoppingCart = new ShoppingCart(dao);

    }
    @Test
    public void testSubmittingCurrentPurchaseBeginsAndCommitsTransaction () {
        InMemorySalesSystemDAO daoMock = Mockito.spy(new InMemorySalesSystemDAO());
        InOrder inOrder = inOrder(daoMock);
        ShoppingCart shoppingCart = new ShoppingCart(daoMock);
        shoppingCart.submitCurrentPurchase();
        inOrder.verify(daoMock,times(1)).beginTransaction();
        inOrder.verify(daoMock,times(1)).commitTransaction();
    }
    @Test
    public void testSubmittingCurrentOrderCreatesHistoryItem () {
        InMemorySalesSystemDAO daoMock = Mockito.spy(new InMemorySalesSystemDAO());
        ShoppingCart shoppingCart = new ShoppingCart(daoMock);
    }
    @Test
    public void testSubmittingCurrentOrderSavesCorrectTime () {
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        SoldItem bread2 = new SoldItem(bread, 1);
        shoppingCart.addItem(bread2);
    }
    @Test
    public void testCancellingOrder () {
        ShoppingCart shoppingCart = new ShoppingCart(dao);
        SoldItem bread2 = new SoldItem(bread, 2);
        SoldItem tea2 = new SoldItem(tea, 3);
        shoppingCart.addItem(bread2);
        shoppingCart.addItem(tea2);
        shoppingCart.cancelCurrentPurchase();
        SoldItem coco2 = new SoldItem(coco, 1);
        SoldItem buns2 = new SoldItem(buns, 4);
        shoppingCart.addItem(coco2);
        shoppingCart.addItem(buns2);

        shoppingCart.submitCurrentPurchase();
    }
    @Test
    public void testCancellingOrderQuanititesUnchanged () {

    }
}

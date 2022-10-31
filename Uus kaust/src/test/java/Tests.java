//import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.*;

public class Tests {
    List mockedList = mock(List.class);
    @Test
    public void testAddingItemBeginsAndCommitsTransaction () {
        //check that methods
        //beginTransaction and commitTransaction are both called exactly once and
        //that order
        //verify(, calls(1)).beginTransaction();   //find in shoppingcart.java
        //verify(, times(1)).commitTransaction();
    }
    @Test
    public void testAddingNewItem () {
        //check that a new item is saved through the DAO
    }
    @Test
    public void testAddingExistingItem () {
        //check that adding a new item increases the quantity
        //and the saveStockItem method of the DAO is not called

        //verify(, calls(0)).saveStockItem();
    }
    @Test
    public void testAddingItemWithNegativeQuantity () {
        //check that adding an item with
        //negative quantity results in an exception

    }

}

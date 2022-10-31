import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tests {
    @Test
    public void testAddingItemBeginsAndCommitsTransaction () {
        //check that methods
        //beginTransaction and commitTransaction are both called exactly once and
        //that order

    }
    @Test
    public void testAddingNewItem () {
        //check that a new item is saved through the DAO
    }
    @Test
    public void testAddingExistingItem () {
        //check that adding a new item increases the quantity
        //and the saveStockItem method of the DAO is not called
    }
    @Test
    public void testAddingItemWithNegativeQuantity () {
        //check that adding an item with
        //negative quantity results in an exception
    }

}

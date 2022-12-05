package ee.ut.math.tvt.salessystem.ui.controllers;

import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    private final SalesSystemDAO dao;
    private static final Logger log = LogManager.getLogger(StockController.class);
    //@FXML
    //private Button addItem;
    @FXML
    private TableView<StockItem> warehouseTableView;
    @FXML
    private TextField barCodeField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField location;
    @FXML
    private Button addToWarehouse;
    @FXML
    private Button cancelPurchase;
    @FXML
    private Button newPurchase;
    @FXML
    private Button submitPurchase;


    public StockController(SalesSystemDAO dao) {
        this.dao = dao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //log.info("initialize");
        //cancelPurchase.setDisable(true);
        //submitPurchase.setDisable(true);
        warehouseTableView.setItems(FXCollections.observableList(dao.findStockItems()));
        disableProductField(true);

        refreshStockItems();
        // TODO refresh view after adding new items
        this.barCodeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    fillInputsBySelectedStockItem();
                }
            }
        });
    }
    private void fillInputsBySelectedStockItem() {
        StockItem stockItem = getStockItemByBarcode();
        if (stockItem != null) {
            nameField.setText(stockItem.getName());
            priceField.setText(String.valueOf(stockItem.getPrice()));
        } else {
            resetProductField();
        }
    }

    @FXML
    public void refreshButtonClicked() {
        refreshStockItems();
    }

    private void refreshStockItems() {
        warehouseTableView.setItems(FXCollections.observableList(dao.findStockItems()));
        warehouseTableView.refresh();
    }

    private void createNotification(String message){
        Alert notification = new Alert(Alert.AlertType.NONE,
                message,
                ButtonType.OK);
        notification.setTitle("Notification!");
        notification.showAndWait();
    }

    public void addProductButtonClicked(){
        long id = 0;

        try {
            enableInputs();
        } catch (SalesSystemException e) {
            log.error(e.getMessage(), e);
        }

        try{
            id = Long.parseLong(barCodeField.getText());
        } catch (NumberFormatException e) {
            createNotification("Barcode should be a number!");
            return;
        }

        StockItem searchedItem = dao.findStockItem(id);

        String name2 = nameField.getText();
        String loc = location.getText();
        double price2 = 0;
        int quantity2 = 0;

        try {
            price2 = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e){
            createNotification("Price should be a number!");
            return;
        }

        try {
            quantity2 = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e){
            createNotification("Amount should be a number!");
            return;
        }
        //Check if the item that is added to the warehouse is already there
        if (searchedItem != null)
            searchedItem.setQuantity(searchedItem.getQuantity() + Integer.parseInt(quantityField.getText()));
        else {
            StockItem newItem = new StockItem(id, name2, "", price2, quantity2);
            dao.saveStockItem(newItem);
        }
        barCodeField.clear();
        quantityField.clear();
        nameField.clear();
        priceField.clear();
        location.clear();

        log.info("Product added to the warehouse.");
    }

    public void deleteButtonClicked(){ deleteStockItem();}

    private void deleteStockItem(){
        int selectedID = warehouseTableView.getSelectionModel().getSelectedIndex();
        if (selectedID >= 0){
            warehouseTableView.getItems().remove(selectedID);
            warehouseTableView.getSelectionModel().clearSelection();
            log.info("Product deleted");
        } else if (warehouseTableView.getItems().size() == 0){
            createNotification("The table is empty. Nothing to delete.");
        } else{
            createNotification("Select a table row to delete it.");
        }
    }

    /** Event handler for the <code>new purchase</code> event. */
    /**@FXML
    protected void newPurchaseButtonClicked() {
        log.info("New sale process started");
        try {
            enableInputs();
        } catch (SalesSystemException e) {
            log.error(e.getMessage(), e);
        }
    }**/
    // switch UI to the state that allows to proceed with the purchase
    private void enableInputs() {
        resetProductField();
        disableProductField(false);
        cancelPurchase.setDisable(false);
        submitPurchase.setDisable(false);
        newPurchase.setDisable(true);
    }
    private void resetProductField() {
        barCodeField.setText("");
        quantityField.setText("1");
        nameField.setText("");
        priceField.setText("");
    }
    private void disableProductField(boolean disable) {
        this.addToWarehouse.setDisable(disable);
        this.barCodeField.setDisable(disable);
        this.quantityField.setDisable(disable);
        this.nameField.setDisable(disable);
        this.priceField.setDisable(disable);
    }
    private StockItem getStockItemByBarcode() {
        try {
            long code = Long.parseLong(barCodeField.getText());
            return dao.findStockItem(code);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    @FXML
    public void addToWarehouseEventHandler() {

        // add chosen item to the shopping cart.
        StockItem stockItem = getStockItemByBarcode();
        if (stockItem != null) {
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException e) {
                quantity = 1;
            }
            //shoppingCart.addItem(new SoldItem(stockItem, quantity));**/
            dao.saveStockItem(stockItem);
            warehouseTableView.refresh();
        }
    }

}

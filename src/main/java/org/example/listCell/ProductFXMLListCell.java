package org.example.listCell;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import org.example.App;
import org.example.Producto;
import org.example.controller.ProductCellController;

public class ProductFXMLListCell extends ListCell<Producto> {

    @Override
    public void updateItem(Producto producto, boolean empty) {
        super.updateItem(producto, empty);
        if (empty || producto == null) {
            setText(null);
            setGraphic(null);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("productFXMLListCell.fxml"));
                Parent root = loader.load();
                ProductCellController productCellController = loader.getController();
                productCellController.setDatosProductos(producto.getNombre());
                setGraphic(root);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void setDatosProductos() {
    }
}
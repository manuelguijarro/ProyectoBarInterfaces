package org.example.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProductCellController {
    @FXML
    private Label nombreProducto;


    public void setDatosProductos(String nombreProductoCell) {
    /*
    Image image = new Image(imgUsuario);
    imagenUsuarioId = new ImageView(image);
    imagenUsuarioId.setFitHeight(100);
    imagenUsuarioId.setFitWidth(200);
    imagenUsuarioId.setVisible(true);*/
        nombreProducto.setText(nombreProductoCell);
    }
}
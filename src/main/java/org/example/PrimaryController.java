package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.example.listCell.ProductFXMLListCell;

public class PrimaryController implements Initializable {
    @FXML
    private Label mensajeMesaLabel;
    @FXML
    private Label totalLabel;

    private List<Producto> productos;
    private List<Mesa> mesas;
    @FXML
    private Button botonReservarMesa;
    private LinkedList<Button>botonesMesa;
    @FXML
    private ListView<Producto>productosMesa;
    @FXML
    private Button mesa1;
    @FXML
    private Button mesa2;
    @FXML
    private Button mesa3;
    @FXML
    private Button mesa4;
    @FXML
    private Button mesa5;
    @FXML
    private Button mesa6;
    @FXML
    private Button mesa7;
    @FXML
    private Button mesa8;
    @FXML
    private Button mesa9;
    @FXML
    private Button mesa10;
    @FXML
    private Button mesa11;

    @FXML
    private Button mesa12;

    @FXML
    private Button botonCervezaId;
    private int idMesaGlobal;
    private ObservableList<Producto> productosDisponibleMesa = FXCollections.observableArrayList();
    public void cargarProductos(){
        //tableView.getItems().clear();

        ConnectorDB connectorDB = new ConnectorDB();
        productos = new LinkedList<>();
        for (Producto producto: connectorDB.listadoProducto()) {
            //tableView.getItems().add(producto);
            productos.add(producto);
        }
        System.out.println();
    }
    public void cargarMesas(){
        //tableView.getItems().clear();
        mesas = new LinkedList<>();
        for(int i = 1; i < 13; i++){
            mesas.add(new Mesa(i,"mesa"+i,false,0));
        }
    }
    public void cargarBotonesMesa(){
        botonesMesa = new LinkedList<>();
        botonesMesa.add(mesa1);
        botonesMesa.add(mesa2);
        botonesMesa.add(mesa3);
        botonesMesa.add(mesa4);
        botonesMesa.add(mesa5);
        botonesMesa.add(mesa6);
        botonesMesa.add(mesa7);
        botonesMesa.add(mesa8);
        botonesMesa.add(mesa9);
        botonesMesa.add(mesa10);
        botonesMesa.add(mesa11);
        botonesMesa.add(mesa12);
    }
    public void cargarEventosBotones(){
        for (Button boton:botonesMesa) {
            boton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String botonid = boton.getId();
                    String[] idMesa = botonid.split("mesa");
                    idMesaGlobal = Integer.parseInt(idMesa[1])-1;
                    Mesa mesa = mesas.get(Integer.parseInt(idMesa[1])-1);
                    mensajeMesaLabel.setText("Mesa seleccionada: " + mesa.getId());

                }
            });
        }
    }


    private void cargarBotonReservar() {
        botonReservarMesa.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!mesas.get(idMesaGlobal).isEstado()){
                    botonesMesa.get(idMesaGlobal).setStyle("-fx-background-color:red");
                    mesas.get(idMesaGlobal).setEstado(true);
                }else{
                    botonesMesa.get(idMesaGlobal).setStyle("-fx-background-color:green");
                    mesas.get(idMesaGlobal).setEstado(false);
                }
            }
        });
    }
    private void cargarListaProducto() {
        try {
            obtenerProductos();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void obtenerProductos() throws IOException, InterruptedException {

        productosDisponibleMesa.setAll(productos);
        productosMesa.setItems(productosDisponibleMesa);
        productosMesa.setCellFactory(param -> new ProductFXMLListCell());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarProductos();
        cargarMesas();
        cargarBotonesMesa();
        cargarEventosBotones();
        cargarBotonReservar();
        botonCervezaId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cargarListaProducto();
            }
        });
    }
}

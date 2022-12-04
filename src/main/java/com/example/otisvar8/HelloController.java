package com.example.otisvar8;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("System component model");
        rootItem.setExpanded(true);

        treeView.setRoot(rootItem);
        treeView.setEditable(true);
        treeView.setCellFactory(TextFieldTreeCell.forTreeView());

        TreeItem<String> fruit = new TreeItem<>("fruit");
        TreeItem<String> drink = new TreeItem<>("drink");
        TreeItem<String> salad = new TreeItem<>("salad");

        fruit.getChildren().add(new TreeItem<String>("Apple"));
        fruit.getChildren().add(new TreeItem<String>("Orange"));

        drink.getChildren().addAll(
                new TreeItem<>("Cola"),
                new TreeItem<>("Fanta"));

        rootItem.getChildren().addAll(fruit,drink,salad);




    }

    public void editItem(ContextMenuEvent contextMenuEvent) {
        ContextMenu controlMenu = new ContextMenu();
        treeView.setContextMenu(controlMenu);

        MenuItem addComponent = new MenuItem("Add component");
        MenuItem removeComponent = new MenuItem("Remove component");

        controlMenu.getItems().add(addComponent);
        controlMenu.getItems().add(removeComponent);

        addComponent.setOnAction(event -> {
            treeView.getSelectionModel().getSelectedItem().getChildren().add(new TreeItem<>("New component"));
        });

        removeComponent.setOnAction(event -> {
            TreeItem<String> itemToDelete = treeView.getSelectionModel().getSelectedItem();
            itemToDelete.getParent().getChildren().remove(itemToDelete);
        });
    }
}
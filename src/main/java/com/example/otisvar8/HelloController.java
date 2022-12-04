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

        TreeItem<String> body = new TreeItem<>("Корпус");

        TreeItem<String> dataProcessing = new TreeItem<>("Микросхема обработки данных");

        TreeItem<String> plugConnect = new TreeItem<>("Подсистема разъемов подключения");
        plugConnect.getChildren().addAll(
                new TreeItem<>("Разъем питания"),
                new TreeItem<>("Разъем приема данных"),
                new TreeItem<>("Разъем передачи данных"));

        TreeItem<String> keys = new TreeItem<>("Подсистема клавиш клавиатуры");
        keys.getChildren().addAll(
                new TreeItem<>("Алфавитно-цифровые клавиши"),
                new TreeItem<>("Специальные клавиши"),
                new TreeItem<>("Функциональные клавиши"),
                new TreeItem<>("Клавиши управления питанием"),
                new TreeItem<>("Клавиши числовой клавиатуры"));

        TreeItem<String> clickHandling = new TreeItem<>("Подсистема обработки нажатий");
        clickHandling.getChildren().addAll(
                new TreeItem<>("Колпачок клавиши"),
                new TreeItem<>("Контакт на мембране"),
                new TreeItem<>("Мембранный контролер"));

        rootItem.getChildren().addAll(body,dataProcessing,plugConnect,keys,clickHandling);
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
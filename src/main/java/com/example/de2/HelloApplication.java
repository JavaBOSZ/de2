package com.example.de2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        List<String> storage = new ArrayList<>();

        stage.setTitle("Lista Plików");

        DirectoryChooser directoryChooser = new DirectoryChooser();

        Button selectDirectoryButton = new Button("Wybierz Katalog");
        ListView<String> fileList = new ListView<>();

        selectDirectoryButton.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(stage);

            if (selectedDirectory != null) {
                File[] files = selectedDirectory.listFiles();

                fileList.getItems().clear();
                if (files != null) {
                    for (File file : files) {
                        storage.add(file.getName());
                    }
                    storage.forEach(nameF->{
                        fileList.getItems().add(nameF);
                    });
                }
            }
        });

        TextField textField = new TextField();
        textField.setPrefWidth(350);
        Button go = new Button("go");
        go.setOnAction(e -> {
            List<String> list = storage.stream().filter(s -> s.contains(textField.getText())).toList();
            fileList.getItems().clear();
            fileList.getItems().addAll(list);
        });
        HBox hBox = new HBox(textField , go);
        VBox vbox = new VBox(selectDirectoryButton, fileList, hBox);
        Scene scene = new Scene(vbox, 400, 300);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
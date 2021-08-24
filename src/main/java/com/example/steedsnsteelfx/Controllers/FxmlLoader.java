package com.example.steedsnsteelfx.Controllers;

import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;

public class FxmlLoader {

    private Pane view;

    public Pane getPage(String strFileName) {

        URL fileURL = HelloApplication.class.getResource(strFileName);

        try {
            view = new FXMLLoader().load(fileURL);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return view;
    }
}
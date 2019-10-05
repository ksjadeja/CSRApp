package com;

import com.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("./FXML/LoginPage.fxml"));
        LoginController loginController=new LoginController();
        loader.setController(loginController);
        primaryStage.setTitle("Authenticate");
        primaryStage.setScene(new Scene(loader.load(),800, 750));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

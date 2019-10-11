package com.controller;

import com.Dao.ConnectionClass;
import com.bean.CompanyBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public Button loginButton;
    @FXML
    public ChoiceBox loginChoiceBox;
    @FXML
    public Label label;

    private Stage stage;


    public void initialize(){
        loginButton.setOnAction(e->onClick_LoginButton());
        loginChoiceBox.getItems().addAll("company");
        loginChoiceBox.setValue("company");
    }

    public void onClick_LoginButton() {
        String loginType = loginChoiceBox.getValue().toString();
        System.out.println("started");
        if (loginType.equals("company")) {
            CompanyBean companyBean = new CompanyBean();
            String userName = username.getText();
            String pass = password.getText();
            Connection con = ConnectionClass.getConnection();
            try {
                Statement statement = con.createStatement();
                ResultSet res = statement.executeQuery("SELECT * FROM company_credentials where username='" + userName + "' AND password='" + password.getText()+"';");
                if (res.next()) {
                    Controller.username=userName;
                    label.setText("Successful");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/FXML/Main.fxml"));
                    Parent root = loader.load();

                    ((Stage) loginButton.getScene().getWindow()).close();
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("CSR App");
                    primaryStage.setScene(new Scene(root, 800, 750));
//                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } else {
                    System.out.println("failed");
                    label.setText("Invalid Inputs");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            label.setText("Invalid Inputs");
        }
    }
}

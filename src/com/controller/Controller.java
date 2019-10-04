package com.controller;

import com.Dao.ConnectionClass;
import com.bean.CompanyBean;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TextField userTextField;
    @FXML
    TextField passwordTextField;
    public void initialize()
    {

    }

    public void onClick_LoginButton()
    {
        String loginType = choiceBox.getValue();
        if(loginType.equals("Company"))
        {
            CompanyBean companyBean = new CompanyBean();
            String userName = userTextField.getText();
            String password = passwordTextField.getText();
            Connection con = ConnectionClass.getConnection();
            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM company where username="+userName+" AND password ="+password);
                while(rs.next())
                {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(loginType.equals("NGO")){

        }else{

        }
    }
}

package com.controller;

import com.Dao.ConnectionClass;
import com.util.DisplayBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    public VBox searchListVBox;

    public void initialize(){
        try {
            Connection conn = ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM projects");
            DisplayBox displayBox;
            List<Text> list = new ArrayList<>();
            list.add(new Text("social_services"));
            list.add(new Text("environment"));
            while (res.next()){
                displayBox = new DisplayBox(list,res.getString("photo1"),res.getString("project_title"),new Text(res.getString("project_intro")),"projects");
                searchListVBox.getChildren().add(displayBox);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

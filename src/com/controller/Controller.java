package com.controller;

import com.Dao.ConnectionClass;
import com.bean.CompanyBean;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    public ChoiceBox searchChoiceBox;
    public CheckBox education;
    public CheckBox environment;
    public CheckBox socialServices;
    public CheckBox healthcare;
    public CheckBox innovation;
    public CheckBox research;
    public CheckBox renewableEnergy;
    public CheckBox ruralDevelopment;
    public Button submitCategories;

    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    TextField userTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    public VBox searchListVBox;

    public void initialize() {
//        try {
//            searchChoiceBox =new ChoiceBox();
        searchChoiceBox.getItems().addAll("NGO", "Individuals");
//            searchChoiceBox.setValue("Products");
//            searchChoiceBox.setVisible(true);
//            Connection conn = ConnectionClass.getConnection();
//            Statement statement = conn.createStatement();
//            ResultSet res = statement.executeQuery("SELECT * FROM projects");
//            DisplayBox displayBox;
//            List<Text> list = new ArrayList<>();
//            list.add(new Text("social_services"));
//            list.add(new Text("environment"));
//            while (res.next()) {
//                displayBox = new DisplayBox(list, res.getString("photo1"), res.getString("project_title"), new Text(res.getString("project_intro")), "projects");
//                searchListVBox.getChildren().add(displayBox);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    }
    public void onClick_LoginButton(){
        String loginType = choiceBox.getValue();
        if (loginType.equals("Company")) {
            CompanyBean companyBean = new CompanyBean();
            String userName = userTextField.getText();
            String password = passwordTextField.getText();
            Connection con = ConnectionClass.getConnection();
            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM company where username=" + userName + " AND password =" + password);
                while (rs.next()) {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (loginType.equals("NGO")) {

        } else {

        }
    }

    public void submitButtonCategories(MouseEvent mouseEvent) {
        searchListVBox.getChildren().clear();
        String type = "";
        type = searchChoiceBox.getValue().toString();
        String tableName = "";
        if (type.equals("NGO")) {
            tableName = "ngos";
        } else {
            tableName = "projects";
        }
        Connection con = ConnectionClass.getConnection();

        int cnt = 0;
        String str = "select * from " + tableName + " where ";
        if (research.isSelected()) {
            if (cnt == 0) {
                str = str.concat("research=1");
                cnt++;
            } else
                str = str.concat("or research=1");
        }
        if (education.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" education=1");
                cnt++;
            } else {
                str = str.concat(" or education=1");
            }
        }
        if (environment.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" environment=1");
                cnt++;
            } else
                str = str.concat(" or environment=1");
        }
        if (healthcare.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" healthcare=1");
                cnt++;
            } else
                str = str.concat(" or healthcare=1");
        }
        if (innovation.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" innovation=1");
                cnt++;
            } else
                str = str.concat(" or innovation=1");
        }
        if (socialServices.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" social_services=1");
                cnt++;
            } else
                str = str.concat(" or social_services=1");
        }
        if (renewableEnergy.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" renewable_energy=1");
                cnt++;
            } else
                str = str.concat(" or renewable_energy=1");
        }
        if (ruralDevelopment.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" rural_development=1");
                cnt++;
            } else
                str = str.concat(" or rural_development=1");
        }
        System.out.println(str);

        Connection connection = ConnectionClass.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(str);
            DisplayBox displayBox2;
            List<Text> list = new ArrayList<>();
//            list.add(new Text("social_services"));
//            list.add(new Text("environment"));
            while (rs.next()) {
                if(rs.getInt("education")==1)
                {
                    list.add(new Text("education"));
                }
                if(rs.getInt("social_services")==1)
                {
                    list.add(new Text("social_services"));
                }
                if(rs.getInt("environment")==1)
                {
                    list.add(new Text("environment"));
                }
                if(rs.getInt("healthcare")==1)
                {
                    list.add(new Text("healthcare"));
                }
                if(rs.getInt("innovation")==1)
                {
                    list.add(new Text("innovation"));
                }
                if(rs.getInt("research")==1)
                {
                    list.add(new Text("research"));
                }
                if(rs.getInt("renewable_energy")==1)
                {
                    list.add(new Text("renewable_energy"));
                }
                if(rs.getInt("rural_development")==1)
                {
                    list.add(new Text("rural_development"));
                }
                if(tableName.equals("ngos"))
                {
                    displayBox2 = new DisplayBox(list, rs.getString("logo"), rs.getString("name"), new Text(rs.getString("intro")), "ngos");
                    searchListVBox.getChildren().add(displayBox2);
                    list.clear();
                }else{
                    displayBox2 = new DisplayBox(list, rs.getString("logo"), rs.getString("project_title"), new Text(rs.getString("project_intro")), "projects");
                    searchListVBox.getChildren().add(displayBox2);
                    list.clear();
                }
//                searchListVBox.getChildren().add(displayBox2);
    public TextField userTextField;

    @FXML
    public VBox searchListVBox;

    public static ObservableList<DisplayBox> ngoList;
    public static ObservableList<DisplayBox> projectsList;
    public static ObservableList<DisplayBox> eventsList;



    public void initialize() {
        try {
            searchChoiceBox.getItems().addAll("NGO", "Individuals");
            Connection conn = ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM projects");
            DisplayBox displayBox;
            List<Text> list = new ArrayList<>();
            list.add(new Text("social_services"));
            list.add(new Text("environment"));
            while (res.next()) {
                displayBox = new DisplayBox(list, res.getString("photo1"), res.getString("project_title"), new Text(res.getString("project_intro")), "projects");
                searchListVBox.getChildren().add(displayBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void submitButtonCategories(MouseEvent mouseEvent) {
        String type = "";
        type = searchChoiceBox.getValue().toString();
        String tableName = "";
        if (type.equals("NGO")) {
            tableName = "ngos";
        } else {
            tableName = "projects";
        }
        Connection con = ConnectionClass.getConnection();

        int cnt = 0;
        String str = "select * from " + tableName + " where ";
        if (research.isSelected()) {
            if (cnt == 0) {
                str = str.concat("research=1");
                cnt++;
            } else
                str = str.concat("or research=1");
        }
        if (education.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" education=1");
                cnt++;
            } else {
                str = str.concat(" or education=1");
            }
        }
        if (environment.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" environment=1");
                cnt++;
            } else
                str = str.concat(" or environment=1");
        }
        if (healthcare.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" helthcare=1");
                cnt++;
            } else
                str = str.concat(" or helthcare=1");
        }
        if (innovation.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" innovation=1");
                cnt++;
            } else
                str = str.concat(" or innovation=1");
        }
        if (socialServices.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" social_services=1");
                cnt++;
            } else
                str = str.concat(" or social_services=1");
        }
        if (renewableEnergy.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" renewable_energy=1");
                cnt++;
            } else
                str = str.concat(" or renewable_energy=1");
        }
        if (ruralDevelopment.isSelected()) {
            if (cnt == 0) {
                str = str.concat(" rural_development=1");
                cnt++;
            } else
                str = str.concat(" or rural_development=1");
        }
        System.out.println(str);

        Connection connection = ConnectionClass.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(str);
            while (rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
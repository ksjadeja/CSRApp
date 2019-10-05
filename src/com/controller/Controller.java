package com.controller;

import com.Dao.ConnectionClass;
import com.bean.CompanyBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Controller {
  public ChoiceBox<String> searchChoiceBox;
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
  ChoiceBox<String> choiceBox;
  @FXML
  TextField userTextField;
  @FXML
  TextField passwordTextField;
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

    public void submitButtonCategories(MouseEvent mouseEvent) {
        String type = searchChoiceBox.getValue();
        String tableName="";
        if(type.equals("NGO")) {
            tableName = "ngos";
        }
        else {
            tableName = "products";
        }
            Connection con = ConnectionClass.getConnection();

                int cnt=0;
                String str="select * from "+tableName+" where ";
                   if(research.isSelected())
                   {
                       str = str.concat("research=1");
                   }
                   if(education.isSelected())
                   {
                       str = str.concat("or education=1");
                   }
                   if(environment.isSelected())
                   {
                       str = str.concat("or environment=1");
                   }
                    if(healthcare.isSelected())
                    {
                        str = str.concat("or healthcare=1");
                    }
                    if(innovation.isSelected())
                    {
                        str = str.concat("or innovation=1");
                    }
                    if(socialServices.isSelected())
                    {
                        str = str.concat("or socialServices=1");
                    }
                    if(renewableEnergy.isSelected())
                    {
                        str = str.concat("or renewableEnergy=1");
                    }
                    if(ruralDevelopment.isSelected())
                    {
                        str = str.concat("or ruralDevelopment=1");
                    }
                    System.out.println(str);

            Connection connection = ConnectionClass.getConnection();
            try {
                Statement statement = con.createStatement();
                statement.executeQuery(str);
            } catch (SQLException e) {
                e.printStackTrace();
            }

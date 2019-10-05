package com.controller;

import com.Dao.ConnectionClass;
import com.bean.CompanyBean;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.util.DisplayBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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

    public static String username;


    @FXML
    public VBox searchListVBox;
    @FXML
    public Text user;

    public static ObservableList<DisplayBox> ngosList;
    public static ObservableList<DisplayBox> projectsList;
    public static ObservableList<DisplayBox> eventsList;

    String category[] = new String[8];

    List<Text> categoryList = new ArrayList<>();


    @FXML
    public VBox ngosVBox,eventsVBox,projectsVBox;

    public void initialize() {
        user.setText(username);
        initDashboard();

        ngosList = FXCollections.observableArrayList();
        projectsList = FXCollections.observableArrayList();
        eventsList = FXCollections.observableArrayList();

        submitCategories.setOnAction(e->{

            int i=0;

            if (education.isSelected()) {
                category[i]="education";categoryList.add(new Text("education"));i++;
            }
            if (environment.isSelected()) {
                category[i]="environment";categoryList.add(new Text("environment"));i++;
            }
            if (socialServices.isSelected()) {
                category[i]="social_services";categoryList.add(new Text("social_services"));i++;
            }
            if (healthcare.isSelected()) {
                category[i]="healthcare";categoryList.add(new Text("healthcare"));i++;
            }
            if (innovation.isSelected()) {
                category[i]="innovation";categoryList.add(new Text("innovation"));i++;
            }
            if (research.isSelected()) {
                category[i]="research";categoryList.add(new Text("research"));i++;
            }
            if (renewableEnergy.isSelected()) {
                category[i]="renewable_energy";categoryList.add(new Text("renewable_energy"));i++;
            }
            if (ruralDevelopment.isSelected()) {
                category[i]="rural_development";categoryList.add(new Text("rural_development"));i++;
            }

            if(searchChoiceBox.getValue().toString().contentEquals("NGO")){
                initSearch("ngos");
            }else{
                initSearch("individual_projects");
            }

        });

        searchChoiceBox.getItems().addAll("NGO", "Individuals");
        user.setText(username);

        projectsList.addListener((ListChangeListener.Change<?extends DisplayBox> change)-> {
            while (change.next()){
                if (change.wasAdded()){
                    projectsVBox.getChildren().add(change.getAddedSubList().get(0));
                }
        }
        });
        ngosList.addListener((ListChangeListener.Change<?extends DisplayBox> change)-> {
            while (change.next()){
                if (change.wasAdded()){
                    ngosVBox.getChildren().add(change.getAddedSubList().get(0));
                }
            }
        });
        eventsList.addListener((ListChangeListener.Change<?extends DisplayBox> change)-> {
            while (change.next()){
                if (change.wasAdded()){
                    eventsVBox.getChildren().add(change.getAddedSubList().get(0));
                }
            }
        });

    }



    public void initDashboard(){

        try {
            List<String> ngolist=new ArrayList<>();
            DisplayBox displayBox;
            Connection conn =ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res =statement.executeQuery("SELECT * FROM companies WHERE name='"+username+"';");
            System.out.println("just started");
            if (res.next()){
                try {
                    System.out.println("Dashboard started");

                    JSONObject jsonObject = new JSONObject(res.getString("projects"));
                    int count=jsonObject.getInt("count");
                    JSONArray jsonArray =jsonObject.getJSONArray("projects");
                    for(int i=0;i<count;i++){
                        if(jsonArray.getJSONObject(i).getString("type").equals("ngos")){
                            displayBox=new DisplayBox(categoryList,res.getString("name"),new Text(res.getString("intro")),"ngos");
                            ngosList.add(displayBox);
                        }else {
                            displayBox=new DisplayBox(categoryList,res.getString("name"),new Text(res.getString("intro")),"individual_projects");
                            projectsList.add(displayBox);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public List<Text> getList(String table,String name){
        List<Text> list = new ArrayList<>();
//        String category[]=new String[8];
//        int i=0;

        Connection conn = ConnectionClass.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet res;

            for (int j=0;j<category.length;j++){
                res=statement.executeQuery("SELECT * from "+table+" where name='"+name+"' and "+category[j]+"=1;");
                list.add(new Text(category[j]));
                System.out.println("list: "+list.get(j));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void initSearch(String table){
        searchListVBox.getChildren().clear();
        List<Text> lst = new ArrayList<>();

        Connection conn = ConnectionClass.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet res;
            for (int j=0;j<category.length;j++){
                res=statement.executeQuery("SELECT * from "+table+" where "+category[j]+"=1;");
                while (res.next()){
                    if(table.equals("ngos")){
                        searchListVBox.getChildren().addAll(new DisplayBox(categoryList,res.getString("name"),new Text(res.getString("intro")),"ngos"));
                    } else {
                        searchListVBox.getChildren().addAll(new DisplayBox(categoryList,res.getString("project_name"),new Text(res.getString("features")),"individual_projects"));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

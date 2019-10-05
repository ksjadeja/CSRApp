package com.controller;

import com.Dao.ConnectionClass;
import com.bean.CompanyBean;
import com.util.CompanyPieChart;
import com.util.NGOGraph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.util.DisplayBox;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public VBox chartVBox;

    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TextField userTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    public VBox searchListVBox;

    public void initialize() {
        searchChoiceBox.getItems().addAll("NGO", "Individuals");

    }
    public void submitButtonCategories() {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadCharts() {
        NGOGraph graph = new NGOGraph(new CategoryAxis(),new NumberAxis());
       // PieChart pieChart = new PieChart();
        String userName ="MICROSOFT";
        try {
            graph.getData().add(graph.createData());

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            Connection con = ConnectionClass.getConnection();
            Statement stmt;
            stmt = con.createStatement();
            JSONObject jsonObject = null;
            JSONObject innerJson = null;
            JSONArray jsonArray = null;
            int amt = 0;
            String name = "";
            int x = 0;
            ResultSet rs = stmt.executeQuery("SELECT * FROM companies WHERE name='"+userName  +"';");
            if (rs.next()) {
                jsonObject = new JSONObject(rs.getString("projects"));
                x = jsonObject.getInt("count");
                System.out.println("x is :"+x);
                jsonArray = jsonObject.getJSONArray("projects");
                for (int i = 0; i < x; i++) {
                    System.out.println("Hola i "+i);
                    innerJson = (JSONObject) jsonArray.get(i);
                    amt = innerJson.getInt("amt");
                    name = innerJson.getString("name");
                    name = name.concat("(" + innerJson.getString("type") + ")");
                    pieChartData.add(new PieChart.Data(name, amt));
                    System.out.println(pieChartData.get(0));
                }
                PieChart pieChart = new PieChart(pieChartData);
                pieChart.setTitle("Project - Contribution");
                pieChart.setLegendSide(Side.LEFT);
                pieChart.setLabelsVisible(true);

                Label caption = new Label("");
                caption.setTextFill(Color.DARKBLUE);
                caption.setStyle("-fx-font: 24 arial;");
                caption.depthTestProperty();
//                for (final PieChart.Data data : pieChart.getData()) {
//                    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                            e -> {
//                                double total = 0;
//                                for (PieChart.Data d : pieChart.getData()) {
//                                    total += d.getPieValue();
//                                }
//                                caption.setTranslateX(e.getSceneX());
//                                caption.setTranslateY(e.getSceneY());
//                                String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
//                                caption.setText(text);
//                                caption.setVisible(true);
////                                System.out.println("c x"+caption.getTranslateX());
//                                System.out.println("mouse entered");
//                            }
//                    );
//                }
                for (final PieChart.Data data : pieChart.getData()) {
                    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                            new EventHandler<MouseEvent>() {
                                @Override public void handle(MouseEvent e) {
                                    caption.setTranslateX(e.getSceneX());
                                    caption.setTranslateY(e.getSceneY());
                                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                                    caption.setVisible(true);
                                    System.out.println("mouse enter");
                                }
                            });
                }
//                2
                ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();
                String name2 = "Akshaya Foundation";
                ResultSet rs2 = stmt.executeQuery("SELECT * from  ngos where name= '"+name2+"' ;");

                if(rs2.next())
                {
                    jsonObject = new JSONObject(rs2.getString("companies"));
                    x=jsonObject.getInt("count");
                    jsonArray = jsonObject.getJSONArray("companies");
                    for(int i=0;i<x;i++)
                    {
                        innerJson = ((JSONObject) jsonArray.get(i));
                        amt = innerJson.getInt("amt");
                        name = innerJson.getString("name");
                        pieChartData2.add(new PieChart.Data(name,amt));
                    }
                }
                PieChart pieChart2 = new PieChart(pieChartData2);
                pieChart2.setTitle("Team - Contribution");
                pieChart2.setLegendSide(Side.LEFT);
                pieChart2.setLabelsVisible(true);

                chartVBox.getChildren().addAll(graph,pieChart,pieChart2);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
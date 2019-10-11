package com.controller;

import com.Dao.ConnectionClass;
import com.util.NGOGraph;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.util.DisplayBox;
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
    int x=0;
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
    TextField passwordTextField;
    public static ObservableList<DisplayBox> ngosList;
    public static ObservableList<DisplayBox> projectsList;
    public static ObservableList<DisplayBox> eventsList;
    @FXML
    public VBox searchListVBox;
    String category[] = new String[8];
    @FXML
    public VBox ngoVBox;
    public VBox eventsVBox,projectsVBox;
    @FXML
    public Text user;
    List<Text> categoryList = new ArrayList<>();
    private List<Text> ngoCategoryList;

    public void initialize() {
        searchChoiceBox.getItems().addAll("NGO", "Individuals");
//        ngoVBox= new VBox();
        ngoVBox.setSpacing(100);
        user.setText(username);
        //initDashboard();
//        ngosList = new ObservableList<DisplayBox>();
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
//                initSearch("ngos");
            }else{
//                initSearch("individual_projects");
            }
        });
        user.setText(username);

        ngosList.addListener((ListChangeListener.Change<?extends DisplayBox> change)-> {
            while (change.next()){
                if (change.wasAdded()){
                    System.out.println("yeah your add-on was listened");
                    System.out.println("Added item  "+change.getAddedSubList().get(0));
                    change.getAddedSubList().get(0).setPrefHeight(100);
                    change.getAddedSubList().get(0).setPrefWidth(100);
                    change.getAddedSubList().get(0).setVisible(true);
                    ngoVBox.getChildren().add(change.getAddedSubList().get(0));
                    ngoVBox.setVisible(true);
                    System.out.println("children  "+ ngoVBox.getChildren());

                    ngoVBox.setSpacing(50);
//                    ngoVBox.getChildren().addAll(change.getAddedSubList());
                }
            }
        });
        projectsList.addListener((ListChangeListener.Change<?extends DisplayBox> change)-> {
            while (change.next()){
                if (change.wasAdded()){
                    projectsVBox.getChildren().add(change.getAddedSubList().get(0));
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
        initDashboard();
    }
    public List<Text> initSearch(String table){
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
                        if(res.getInt(category[j])==1)
                            ngoCategoryList.add(new Text(category[j]));
//                        searchListVBox.getChildren().addAll(new DisplayBox(categoryList,res.getString(""),res.getString("name"),new Text(res.getString("intro")),"ngos"));
                    } else {
                        searchListVBox.getChildren().addAll(new DisplayBox(categoryList,res.getString("project_name"),new Text(res.getString("features")),"individual_projects"));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ngoCategoryList;
    }
    public void initDashboard(){

        try {
            System.out.println("IniiDashBoard()");
            System.out.println("username "+username);
            List<String> ngolist=new ArrayList<>();
            DisplayBox displayBox=null;
            Connection conn =ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res =statement.executeQuery("SELECT * FROM companies WHERE name='"+username+"';");
            JSONObject jsonObject=null;
            JSONArray jsonArray=null;
            JSONObject innerJson=null;
            if (res.next()){
                try {
                    System.out.println("Inside try /");
                    jsonObject = new JSONObject(res.getString("projects"));
                    int count=jsonObject.getInt("count");
                    jsonArray = jsonObject.getJSONArray("projects");
                    for(int i=0;i<count;i++){
                        System.out.println("cnt > 0");
                        innerJson = (JSONObject) jsonArray.get(i);
                        if(innerJson.getString("type").equals("NGO")){
                            System.out.println("It's NGO");
                            ngolist.add((innerJson.getString("name")));
//                            initSearch();
//                            getMyNgoCategories(innerJson.getString("name"),"ngos");
//                            displayBox=
//                                    new DisplayBox(res.getString("name"),new Text(res.getString("intro")),"ngos");
                            System.out.println("Display box created "+ngosList);
                            boolean add = ngosList.add(new DisplayBox(getMyNgoCategories(innerJson.getString("name"),"ngos"),res.getString("name"), new Text(res.getString("intro")), "ngos"));
                            System.out.println("Added to list bool "+add);
                            System.out.println("list "+ngosList);
//                            ngoVBox.getChildren().add(displayBox);
//                            boolean add = ngosList.add(displayBox);
//                            System.out.println(" bool "+add);
//                            ngoVBox.getChildren().add(displayBox);
                        }else {
//                            displayBox=new DisplayBox(addCategories(res,"projects",1),res.getString("logo"),res.getString("project_title"),new Text(res.getString("project_intro")),"projects");
//                            projectsList.add(displayBox);
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

    private List<Text> getMyNgoCategories(String name,String tableName) {
        List<Text> list = new ArrayList<>();
        try {

            Connection con = ConnectionClass.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs=null;
            if(tableName.equals("ngos"))
                rs = stmt.executeQuery("SELECT * FROM  "+tableName+" where name = '"+name+"';");
            else
                rs = stmt.executeQuery("SELECT * FROM  "+tableName+" where project_name = '"+name+"';");
            if(rs.next())
            {
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
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
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
                if(tableName.equals("ngos"))
                {
                    displayBox2 = new DisplayBox(list,rs.getString("name"), new Text(rs.getString("intro")), "ngos");
                    searchListVBox.getChildren().add(displayBox2);
                    list.clear();
                }else{
                    displayBox2 = new DisplayBox(list, rs.getString("project_title"), new Text(rs.getString("project_intro")), "projects");
                    searchListVBox.getChildren().add(displayBox2);
                    list.clear();
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

    public void loadCharts() {
        if (x == 0) {
            x++;
            NGOGraph graph = new NGOGraph(new CategoryAxis(), new NumberAxis());
            // PieChart pieChart = new PieChart();
            String userName = "MICROSOFT";
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM companies WHERE name='" + userName + "';");
                if (rs.next()) {
                    jsonObject = new JSONObject(rs.getString("projects"));
                    x = jsonObject.getInt("count");
                    System.out.println("x is :" + x);
                    jsonArray = jsonObject.getJSONArray("projects");
                    for (int i = 0; i < x; i++) {
                        System.out.println("Hola i " + i);
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
                                    @Override
                                    public void handle(MouseEvent e) {
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
                    ResultSet rs2 = stmt.executeQuery("SELECT * from  ngos where name= '" + name2 + "' ;");

                    if (rs2.next()) {
                        jsonObject = new JSONObject(rs2.getString("companies"));
                        x = jsonObject.getInt("count");
                        jsonArray = jsonObject.getJSONArray("companies");
                        for (int i = 0; i < x; i++) {
                            innerJson = ((JSONObject) jsonArray.get(i));
                            amt = innerJson.getInt("amt");
                            name = innerJson.getString("name");
                            pieChartData2.add(new PieChart.Data(name, amt));
                        }
                    }
                    PieChart pieChart2 = new PieChart(pieChartData2);
                    pieChart2.setTitle("Team - Contribution");
                    pieChart2.setLegendSide(Side.LEFT);
                    pieChart2.setLabelsVisible(true);

                    chartVBox.getChildren().addAll(graph, pieChart, pieChart2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

package com.util;

import com.Dao.ConnectionClass;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.Infinity;

public class DisplayBox extends VBox {

    FlowPane flowPane;
//    ImageView logo;
    Text name;
    Text intro;
    HBox hBox;
    VBox vBox;
    Button colab;
    Accordion accordion;
    TitledPane titledPane;

    public DisplayBox(List<Text> tags, String title, Text intro, String table){
        flowPane = new FlowPane();
        flowPane.getChildren().addAll(tags);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        this.setPrefWidth(100);
        this.setPrefHeight(100);
        this.setVisible(true);
//        logo=new ImageView(image+".jpeg");
//        initt();
        this.name=new Text(title);
        this.intro=intro;

//        titledPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
////                accordion.getPanes().clear();
//                System.out.println("Accordion here");
//                HBox hBox1 = new HBox();
//                HBox hBox2 = new HBox();
//
//                try {
//                    Connection conn = ConnectionClass.getConnection();
//                    Statement statement = conn.createStatement();
//                    ResultSet res = statement.executeQuery("SELECT * from "+ table +" where project_title='"+title+"';");
//                    if(res.next()){
//                        ImageView img1 = new ImageView(res.getString("photo1")+".jpg");
//                        ImageView img2 = new ImageView(res.getString("photo2")+".jpg");
//                        Text text1 = new Text(res.getString("project_desc"));
//                        Text text2 = new Text(res.getString("project_desc"));
//                        text1.setFont(new Font("Arial Italic",13));
//                        text1.setFont(new Font("Arial Bold",13));
//                        TextFlow tf1 = new TextFlow();tf1.setTextAlignment(TextAlignment.JUSTIFY);tf1.getChildren().add(text1);
//                        TextFlow tf2 = new TextFlow();tf2.setTextAlignment(TextAlignment.JUSTIFY);tf2.getChildren().add(text2);
//                        System.out.println("pro desc:"+res.getString("project_desc"));
//                        hBox1.getChildren().addAll(img1,tf1);
//                        hBox2.getChildren().addAll(img2,tf2);
//                        titledPane.setContent(new VBox(hBox1,hBox2,colab));
//                    }
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        });
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        try {
            Connection conn = ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res=null;
            if (table.equals("ngos"))
                res = statement.executeQuery("SELECT * from " + table + " where name='" + title + "';");
        else{
                res = statement.executeQuery("SELECT * from " + table + " where project_title='" + title + "';");
        }
            if(res.next()){
//                ImageView img1 = new ImageView(res.getString("photo1")+".jpg");
//                ImageView img2 = new ImageView(res.getString("photo2")+".jpg");
//                img1.setFitHeight(200);
//                img1.setFitWidth(200);
//                img2.setFitHeight(200);
//                img2.setFitWidth(200);
                Text text1 = new Text(res.getString("intro"));
                Text text2 = new Text(res.getString("desc"));
                text1.setFont(new Font("Arial Italic",13));
                text1.setFont(new Font("Arial Bold",13));
                TextFlow tf1 = new TextFlow();tf1.setTextAlignment(TextAlignment.JUSTIFY);tf1.getChildren().add(text1);
                TextFlow tf2 = new TextFlow();tf2.setTextAlignment(TextAlignment.JUSTIFY);tf2.getChildren().add(text2);

//                tf2.setPadding(new Insets(30,10,30,400));
                hBox2.setSpacing(50);
                System.out.println("pro desc:"+res.getString("project_desc"));
                hBox1.getChildren().addAll(tf1);
                hBox2.getChildren().addAll(tf2);
                colab=new Button("Colab");
                colab.setOnAction(e->onClickColab());
                titledPane = new TitledPane("read more",new VBox(hBox1,hBox2,colab));
                accordion = new Accordion(titledPane);
//                accordion.setPrefWidth(1080);
                accordion.setMaxWidth(-Infinity);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        initt();
    }
    public DisplayBox(String title, Text intro, String table){
//        flowPane = new FlowPane();
//        flowPane.getChildren().addAll(tags);

//        logo=new ImageView(image+".jpeg");
        initt();
        this.name=new Text(title);
        this.intro=intro;

//        titledPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
////                accordion.getPanes().clear();
//                System.out.println("Accordion here");
//                HBox hBox1 = new HBox();
//                HBox hBox2 = new HBox();
//
//                try {
//                    Connection conn = ConnectionClass.getConnection();
//                    Statement statement = conn.createStatement();
//                    ResultSet res = statement.executeQuery("SELECT * from "+ table +" where project_title='"+title+"';");
//                    if(res.next()){
//                        ImageView img1 = new ImageView(res.getString("photo1")+".jpg");
//                        ImageView img2 = new ImageView(res.getString("photo2")+".jpg");
//                        Text text1 = new Text(res.getString("project_desc"));
//                        Text text2 = new Text(res.getString("project_desc"));
//                        text1.setFont(new Font("Arial Italic",13));
//                        text1.setFont(new Font("Arial Bold",13));
//                        TextFlow tf1 = new TextFlow();tf1.setTextAlignment(TextAlignment.JUSTIFY);tf1.getChildren().add(text1);
//                        TextFlow tf2 = new TextFlow();tf2.setTextAlignment(TextAlignment.JUSTIFY);tf2.getChildren().add(text2);
//                        System.out.println("pro desc:"+res.getString("project_desc"));
//                        hBox1.getChildren().addAll(img1,tf1);
//                        hBox2.getChildren().addAll(img2,tf2);
//                        titledPane.setContent(new VBox(hBox1,hBox2,colab));
//                    }
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        });
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        try {
            Connection conn = ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res=null;
            if (table.equals("ngos"))
                res = statement.executeQuery("SELECT * from " + table + " where name='" + title + "';");
            else{
                res = statement.executeQuery("SELECT * from " + table + " where project_title='" + title + "';");
            }
            if(res.next()){
//                ImageView img1 = new ImageView(res.getString("photo1")+".jpg");
//                ImageView img2 = new ImageView(res.getString("photo2")+".jpg");
//                img1.setFitHeight(200);
//                img1.setFitWidth(200);
//                img2.setFitHeight(200);
//                img2.setFitWidth(200);
                Text text1 = new Text(res.getString("project_desc"));
                Text text2 = new Text(res.getString("project_desc"));
                text1.setFont(new Font("Arial Italic",13));
                text1.setFont(new Font("Arial Bold",13));
                TextFlow tf1 = new TextFlow();tf1.setTextAlignment(TextAlignment.JUSTIFY);tf1.getChildren().add(text1);
                TextFlow tf2 = new TextFlow();tf2.setTextAlignment(TextAlignment.JUSTIFY);tf2.getChildren().add(text2);

//                tf2.setPadding(new Insets(30,10,30,400));
                hBox2.setSpacing(50);
                System.out.println("pro desc:"+res.getString("project_desc"));
                hBox1.getChildren().addAll(tf1);
                hBox2.getChildren().addAll(tf2);
                colab=new Button("Colab");
                colab.setOnAction(e->onClickColab());
                titledPane = new TitledPane("read more",new VBox(hBox1,hBox2,colab));
                accordion = new Accordion(titledPane);
//                accordion.setPrefWidth(1080);
                accordion.setMaxWidth(-Infinity);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

//        init();
    }

    private void initt(){
//        if(f==0)
//        {
//            flowPane.setVgap(10);
//            flowPane.setHgap(10);
//        }

//        logo.setFitWidth(300);
//        logo.setFitHeight(300);

//        name=new Text();
        name.setFont(new Font("Times New Roman Bold",14));
        vBox=new VBox();
//        intro = new Text();
        vBox.getChildren().addAll(name,intro);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(5,5,5,5));

        hBox=new HBox();
        hBox.getChildren().addAll(vBox);
        hBox.setSpacing(15);
        hBox.setPadding(new Insets(10,10,10,10));
        accordion = new Accordion();
//        if(f==0)
            this.getChildren().addAll(flowPane,hBox,accordion);
//        else
//            this.getChildren().addAll(hBox,accordion);
//
    }

    public void onClickColab(){
        //TODO load more info page
    }

}

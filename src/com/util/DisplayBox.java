package com.util;

import com.Dao.ConnectionClass;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
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

//    public DisplayBox(List<Text> tags, String image, String title, Text intro, String table){
        if(!tags.isEmpty()){
            flowPane = new FlowPane();
            flowPane.getChildren().addAll(tags);
            flowPane.setVgap(10);
            flowPane.setHgap(10);
        }


//        logo=new ImageView(image);
        this.name=new Text(title);
        this.intro=intro;

        HBox hBox1 = new HBox();
                HBox hBox2 = new HBox();
        try {
            Connection conn = ConnectionClass.getConnection();
            Statement statement = conn.createStatement();
            ResultSet res;
            if(table.equals("ngos")) {
                res= statement.executeQuery("SELECT * from "+ table +" where name='"+title+"';");
            }else{
                res= statement.executeQuery("SELECT * from "+ table +" where project_name='"+title+"';");

            }

            if(res.next()){
//                ImageView img1 = new ImageView(res.getString("photo1"));
//                ImageView img2 = new ImageView(res.getString("photo2"));
//                img1.setFitHeight(200);img2.setFitWidth(200);
                Text text1,text2;
                if(table.equals("ngos")) {
                    text1= new Text(res.getString("intro"));
                    text2 = new Text(res.getString("desc"));
                }else{
                    text1 = new Text(res.getString("desc"));
                    text2 = new Text(res.getString("features"));
                }
                text1.setFont(new Font("Arial Italic",13));
                text1.setFont(new Font("Arial Bold",13));
                TextFlow tf1 = new TextFlow();tf1.setTextAlignment(TextAlignment.JUSTIFY);tf1.getChildren().add(text1);
                TextFlow tf2 = new TextFlow();tf2.setTextAlignment(TextAlignment.JUSTIFY);tf2.getChildren().add(text2);
//                hBox1.getChildren().addAll(img1,tf1);
//                hBox2.getChildren().addAll(img2,tf2);
                hBox1.getChildren().addAll(tf1);
                hBox2.getChildren().addAll(tf2);
                colab=new Button("Colab");
                colab.setOnAction(e->onClickColab());
                titledPane = new TitledPane("read more",new VBox(hBox1,hBox2,colab));
                accordion = new Accordion(titledPane);
                accordion.setPrefWidth(1080);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        init();

        if(flowPane==null){
            System.out.println("its flaw");
//            getChildren().addAll(flowPane,hBox,accordion);
//            System.out.println(getChildren());
        }else if(hBox==null){
            System.out.println("bhox 0");
//            getChildren().addAll(hBox,accordion);
        }else {
            System.out.println("accordion 0");
        }
    }

    private void init(){


//        logo.setFitWidth(300);
//        logo.setFitHeight(300);

        name.setFont(new Font("Times New Roman Bold",14));

        vBox=new VBox();
        vBox.getChildren().addAll(name,intro);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(5,5,5,5));

        hBox=new HBox();
        hBox.getChildren().addAll(vBox);
//        hBox.getChildren().addAll(logo,vBox);

        hBox.setSpacing(15);
        hBox.setPadding(new Insets(10,10,10,10));


    }

    public void onClickColab(){
        //TODO load more info page
    }

}

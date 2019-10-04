package com.util;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class DisplayBox extends VBox {

    FlowPane flowPane;
    ImageView logo;
    Text name;
    Text intro;
    HBox hBox;
    VBox vBox;
    Button colab;
    Accordion accordion;

    public DisplayBox(List<Text> tags, String image, String name, Text intro){
        this.flowPane.getChildren().addAll(tags);
        logo=new ImageView("../images/"+image);
        this.name=new Text(name);
        this.intro=intro;
        accordion.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        init();
    }

    private void init(){
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        logo.setFitWidth(100);
        logo.setFitHeight(100);

        name.setFont(new Font("Times New Roman Bold",14));

        vBox.getChildren().addAll(name,intro);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(5,5,5,5));

        hBox.getChildren().addAll(logo,vBox);
        hBox.setSpacing(15);
        hBox.setPadding(new Insets(10,10,10,10));

//        moreInfo.setOnAction(e->onClickMoreInfo());
//
//        getChildren().addAll(flowPane,hBox,moreInfo);

    }

    public void onClickMoreInfo(){
        //TODO load more info page
    }

}

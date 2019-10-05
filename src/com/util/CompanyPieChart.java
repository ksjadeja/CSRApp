package com.util;

import com.Dao.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyPieChart extends PieChart {

    public CompanyPieChart()
    {
        super();
    }
    public void createPieChartForCompany(String companyName)
    {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Connection con = ConnectionClass.getConnection();
        Statement stmt;

        {
            try {
                stmt = con.createStatement();
                JSONObject jsonObject=null;
                JSONObject innerJson=null;
                JSONArray jsonArray=null;
                int amt=0;
                String name="";
                int x=0;
                ResultSet rs = stmt.executeQuery("SELECT * FROM companies WHERE COMPANY_title='"+x+"';");
                if(rs.next())
                {
                    jsonObject = new JSONObject(rs.getString("projects"));
                    x = jsonObject.getInt("count");
                    jsonArray= jsonObject.getJSONArray("projects");
                    for(int i=0;i<x;i++)
                    {
                        innerJson = (JSONObject) jsonArray.get(i);
                        amt =innerJson.getInt("amt");
                        name = innerJson.getString("name");
                        name = name.concat("("+innerJson.getString("type")+")");
                        pieChartData.add(new PieChart.Data(name,amt));
                    }
                }
//                this.setTitle("Project - Contribution");
//                this.setLegendSide(Side.LEFT);
//                final Label caption = new Label("");
//                caption.setTextFill(Color.DARKORANGE);
//                caption.setStyle("-fx-font: 24 arial;");
//
//                for (final PieChart.Data data : this.getData()) {
//                    data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED,
//                            e -> {
//                                caption.setTranslateX(e.getSceneX());
//                                caption.setTranslateY(e.getSceneY());
//                                caption.setText(String.valueOf(data.getPieValue()) + "%");
//                            });
//                    data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED,
//                            e -> {
////                                caption.setTranslateX(e.getSceneX());
////                                caption.setTranslateY(e.getSceneY());
////                                caption.setText(String.valueOf(data.getPieValue()) + "%");
//                                caption.setVisible(false);
//                            });
//                }
            } catch (SQLException | JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

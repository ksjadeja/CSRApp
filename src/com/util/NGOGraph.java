package com.util;

import com.Dao.ConnectionClass;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class NGOGraph extends BarChart<String,Number> {
    Series<String, Number> dataSeries1;

    public NGOGraph(CategoryAxis xAxis, NumberAxis yAxis) {
        super(xAxis, yAxis);
        xAxis.setLabel("NGOs");
        yAxis.setLabel("Total");

    }
    public Series<String, Number> createData() throws JSONException {
        dataSeries1 = new Series<>();
        dataSeries1.setName("Amount");
        try {
            Connection con = ConnectionClass.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from ngos");
           int x=1;
           int amt=0;
           JSONObject innerJson;
           JSONArray jsonArray;
            while(rs.next())
            {
                JSONObject jsonObject = new JSONObject(rs.getString("companies"));
                x = jsonObject.getInt("count");

                jsonArray = jsonObject.getJSONArray("companies");
                amt=0;
                for(int i=0;i<x;i++)
                {
                    innerJson = (JSONObject) jsonArray.get(i);
                    amt+=innerJson.getInt("amt");
                }
                dataSeries1.getData().add(new Data(rs.getString("name"), amt));
                amt=0;
            }
        }catch(SQLException e)
        {
                e.printStackTrace();
        }
        return dataSeries1;
    }
}

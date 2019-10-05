package com.Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    public static Connection connection;

    public static Connection getConnection(){
        String dbName = "csr_app";
        String userName = "root";
        String password = "root";
        String driverName = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }

}


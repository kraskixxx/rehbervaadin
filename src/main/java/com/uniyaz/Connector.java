package com.uniyaz;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    final static String JDBC_CONNECTION_STR = "jdbc:mysql://127.0.0.1:3306/rehber";
    final static String USERNAME = "root";
    final static String PASSWORD = "1c2e3n4g5i6z";

    private static Connector connector;

    private Connector(){

    }

    public static Connector getConnectorInstance(){
        if(connector == null){
            connector = new Connector();
            return connector;
        }
        else
            return connector;
    }

    public static Connection makeConnection(){
        try {
            Connection conn = DriverManager.getConnection(JDBC_CONNECTION_STR, USERNAME, PASSWORD);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

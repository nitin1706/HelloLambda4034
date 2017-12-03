package com.nitin1706.practice.demo_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

public class Hello_1 implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input + "\n");
        Gson gson = new Gson();
        ButtonData buttonData = gson.fromJson(input.toString(), ButtonData.class);
        insertDataUsingJDBC(buttonData.getClickType()+buttonData.getSerialNumber(), context);
        return buttonData.getClickType();
    }
    
    public static void insertDataUsingJDBC(String data, Context context) {
        String DB_URL = "jdbc:mysql://test4034.cdwlcf5rek3j.us-east-1.rds.amazonaws.com/default_db";
        String USER = "shaktiman";
        String PASS = "gangadhar=Shaktiman";
        
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            context.getLogger().log("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            context.getLogger().log("Connected database successfully...");
            
            context.getLogger().log("Inserting records into the table...");
            stmt = conn.createStatement();
            String sql1 = "insert into item_feed (feed) values(\"" + data + "\")";
            stmt.executeUpdate(sql1);
            
            System.out.println("Inserted records into the table...");
        }
        catch (SQLException se) {
            context.getLogger().log("SQLException + 1");
            se.printStackTrace();
        }
        catch (Exception e) {
            context.getLogger().log("Exception + 1");
            e.printStackTrace();
        }
        finally
        {
            try {
                if (stmt != null)
                    conn.close();
            }
            catch (SQLException se) {
                context.getLogger().log("SQL Exception");
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException se) {
                context.getLogger().log("SQLException + 2");
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

package com.bridgelabs;

import java.sql.*;
import java.util.Enumeration;

public class DataBaseClass {
    static String url = "jdbc:mysql://localhost:3306/payroll_Employewage";
    static String userName="root";
    static String password="Hariprasad@12";
    public static void fetchData(String quaryFetch) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("The error in the class -" + e);
        }
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            Statement st = connection.createStatement();
            System.out.println("The fecting the data is started  \n");
            ResultSet rs =st.executeQuery(quaryFetch);
            //The result we want in the table formatte so we use RS
            //fetch the data
            System.out.println("The result set - " + rs);
            while (rs.next()) {
                String userData = rs.getInt(1)+" "+rs.getNString(2) + "  " + rs.getDate(3)+" "+ rs.getDouble(4 ) + " "+ rs.getString(5);
                System.out.println(userData);
            }
            System.out.println("Closing point of the Fetch method");
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("The error at the Connection - " + e);
        }
    }
    public static void set(String quary){
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            Statement st = connection.createStatement();
            System.out.println("--------The quary point for the updation ------ \n");
            int count =st.executeUpdate(quary);
            System.out.println("The no row affected + "+ count);
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("error - "+ e);
        }

    }


    public static void main(String[] args) {
        fetchData("select * from payroll_Employewage");
        set("update payroll_Employewage set salary =3000000.0 where id = 4 ");
        fetchData("select * from payroll_Employewage");
    }
}
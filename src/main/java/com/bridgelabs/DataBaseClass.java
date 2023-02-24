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
    public static void set(){
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            // Statement st = connection.createStatement();

            String quary ="update payroll_Employewage set salary = ? where id = ? ";
            // " insert into employee_payroll(name,salary,start) values(?,?,?)";
            System.out.println("--------The quary point for the updation ------ \n");
            PreparedStatement st = connection.prepareStatement(quary);
            st.setDouble(1,3600000);
            st.setInt(2,4);

            int count = st.executeUpdate();
            System.out.println("The no row affected + "+ count);
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("error - "+ e);
        }

    }
    public static void main(String[] args) {
        fetchData("SELECT * FROM payroll_Employewage WHERE startDate BETWEEN CAST('2021-01-01'AS DATE) AND DATE(NOW())");
//        set();
//        fetchData("select * from payroll_Employewage");
    }
}
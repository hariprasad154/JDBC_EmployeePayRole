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
                String userData = rs.getInt(1)+" "+rs.getNString(2) + "  " + rs.getDate(3)+" "+ rs.getDouble(4 ) + " "+ rs.getString(5) + " " + rs.getDouble(6) + " "+ rs.getDouble(7);
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

    public static void fetchMethods(String quary) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("The error in the class -" + e);
        }
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            Statement st = connection.createStatement();
            System.out.println("The fecting the data is started  \n");
            ResultSet rs =st.executeQuery(quary);
            //The result we want in the table formatte so we use RS
            //fetch the data
            System.out.println("The result set - " + rs);
            while (rs.next()) {
                Double userData =rs.getDouble(1 ) ;
                System.out.println(userData);
            }
            System.out.println("Closing point of the Fetch method");
            st.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("The error at the Connection - " + e);
        }
    }


    public static void insertDataUsingPreparedStatment(){
        try {
            Connection connection =DriverManager.getConnection(url,userName,password);
            // Statement st = connection.createStatement();
            int id=6;
            String name ="Rajeswari";
            double salary = 45000;
            Date startDate = new Date(2022,8,20);
            String gender ="F";
            Double Taxpay= salary*0.10;
            Double basepay=salary-salary*0.10;

            String quary =" insert into payroll_Employewage(id,name,startDate,salary,Gender,BasicPay,TaxPay) values(?,?,?,?,?,?,?)";
            System.out.println("--------The quary point for the updation ------ \n");
            PreparedStatement st = connection.prepareStatement(quary);
            st.setInt(1,id);
            st.setString(2,name);
            st.setDate(3,startDate);
            st.setString(5,gender);
            st.setDouble(4,salary);
            st.setDouble(7,Taxpay);
            st.setDouble(6,basepay);

            int count = st.executeUpdate();
            System.out.println("The no row affected + "+ count);
            st.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("error - "+ e);
        }

    }


    public static void main(String[] args) {
      //  fetchData("SELECT * FROM payroll_Employewage WHERE startDate BETWEEN CAST('2021-01-01'AS DATE) AND DATE(NOW())");
//        set();
        insertDataUsingPreparedStatment();
        //fetchMethods("SELECT SUM(salary) FROM payroll_Employewage WHERE Gender = 'F' ");
        fetchData("select * from payroll_Employewage");
    }
}
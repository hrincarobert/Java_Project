package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class DB {

    public static Connection DBConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql:///sbs","root", "student");
            System.out.print("Database is connected !");

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Do not connect to DB - Error:"+e);

        }
        return conn;
    }
    public static void addProductToDB(String id,String detail,String comp,int quan)
    {
        Connection conn=DBConnection();
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String data_curenta = currentDate.format(formatter);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO stock VALUES ('"+id+"','"+detail+"','"+comp+"',"+quan+" ,'"+data_curenta+"' );");
            JOptionPane.showMessageDialog(null, "Product added to database");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
    public static void updateProductToDB(String id,String detail,String comp,int quan)
    {
        Connection conn=DBConnection();
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String data_curenta = currentDate.format(formatter);
            Statement statement = conn.createStatement();
            int status=statement.executeUpdate("UPDATE stock set Details = '"+detail+"', Company = '"+comp+"', Quantity = "+quan+" , date= '"+data_curenta+"' WHERE ProductID = '"+id+"';");
            if(status==1)
                JOptionPane.showMessageDialog(null,  "Product updated");
            else
                JOptionPane.showMessageDialog(null,  "ProductID not found!");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }

    public static void updateFunds(String funds,String username)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            int status=statement.executeUpdate("UPDATE customer set funds = '"+funds+"' WHERE username = '"+username+"';");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }
    public static void updateQuantityStock(String id,double quan)
    {
        Connection conn=DBConnection();
        try {

            Statement statement = conn.createStatement();
            int status=statement.executeUpdate("UPDATE stock set Quantity = '"+quan+"' WHERE ProductID = '"+id+"';");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }
    public static void deleteProductToDB(String id)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            int status=statement.executeUpdate("DELETE from stock WHERE ProductID = '"+id+"';");
            if(status==1)
                JOptionPane.showMessageDialog(null,  "Product deleted");
            else
                JOptionPane.showMessageDialog(null,  "ProductID not found!");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }
    public static void searchProduct(String id)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
            if (!rs.next())
                JOptionPane.showMessageDialog(null,"No product found with this id!");
            else
                JOptionPane.showMessageDialog(null, "ProductID: "+id+"\nQuantity: "+rs.getString("Quantity"));

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getFunds(String username) {
        String funds = null;
        Connection conn=DBConnection();

            String query = "SELECT funds FROM customer where username = '"+username+"'";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    funds = resultSet.getString("funds");
                }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }

        return funds;
    }

    public static String getDate(String id) {
        String date = null;
        Connection conn=DBConnection();

        String query = "SELECT date FROM stock where ProductID = '"+id+"'";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                date = resultSet.getString("date");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static int getQuantity(String id) {
        int quan = 0;
        Connection conn=DBConnection();

        String query = "SELECT Quantity FROM stock where ProductID = '"+id+"'";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                quan = resultSet.getInt("Quantity");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return quan;
    }
    public static void searchCashier(String email)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"';");
            if (!rs.next())
                JOptionPane.showMessageDialog(null,"No cashier found with this username!");
            else
                JOptionPane.showMessageDialog(null, "Username: "+email+"\nPassword: "+rs.getString("Password"));

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
    public static boolean verifyLogin(String email,String pass)
    {
        boolean login=false;
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from users WHERE Email = '"+email+"' and Password = '"+pass+"';");
            if (!rs.next())
                login=false;
            else
                login=true;

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return login;
    }
    public static boolean verifyLoginCustomer(String email,String pass)
    {
        boolean login=false;
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select username,password from customer WHERE username = '"+email+"' and password = '"+pass+"';");
            if (!rs.next())
                login=false;
            else
                login=true;

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return login;
    }
    public static void addCashier(String user,String pass)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO users VALUES ('"+user+"','"+pass+"');");
            JOptionPane.showMessageDialog(null, "Cashier added to database");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
    public static void addCustomer(String user,String pass,String money)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO customer VALUES ('"+user+"','"+pass+"', '"+money+"');");
            JOptionPane.showMessageDialog(null, "Customer added to database");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
    public static void deleteCashier(String user,String pass)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            int status=statement.executeUpdate("DELETE from users WHERE Email = '"+user+"' AND Password = '"+pass+"';");
            if(status==1)
                JOptionPane.showMessageDialog(null,  "Cashier deleted");
            else
                JOptionPane.showMessageDialog(null,  "Cashier not found!");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    public static String searchPDetail(String id,int q)
    {
        Connection conn=DBConnection();
        String rt="";
        try {
            int quan;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
            if (!rs.next())
                rt="nill";
            else{
                quan=Integer.parseInt(rs.getString("Quantity"))-q;
                if(quan<0)
                    rt="item is out of stock";
                else
                {
                    rt=rs.getString("Details")+"%"+rs.getString("Company");
                    statement.executeUpdate("UPDATE stock set Quantity = "+quan+" WHERE ProductID = '"+id+"';");
                }

            }

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return rt;
    }
    public static void addSaleToDB(String data_curenta, String prodid, String company, String cost_final)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();

                statement.executeUpdate("INSERT INTO sale VALUES ('"+data_curenta+"','"+prodid+"','"+company+"','"+cost_final+"');");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateHistory(String username, String numeprod, String prodid, String company,String data_curenta,String cost)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();

            statement.executeUpdate("INSERT INTO history VALUES ('"+username+"','"+numeprod+"','"+prodid+"','"+company+"' , '"+data_curenta+"', '"+cost+"');");
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }


    public static ArrayList<String> getSale(String date,String comp)
    {
        String q;
        ArrayList<String> r=new ArrayList<String>();

        if(comp.equals("All"))
            q="Select * from sale WHERE Date = '"+date+"';";
        else
            q="Select * from sale WHERE Date = '"+date+"' AND Company = '"+comp+"';";
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(q);
            while(rs.next())
            {
                r.add(rs.getString("Date"));
                r.add(rs.getString("ProductID"));
                r.add(rs.getString("Company"));
                r.add(rs.getString("Payment"));
            }
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    public static ArrayList<String> showStock(String comp)
    {
        String q;
        ArrayList<String> r=new ArrayList<String>();
        if(comp.equals("All"))
            q="Select * from stock;";
        else
            q="Select * from stock WHERE Company = '"+comp+"';";
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(q);
            while(rs.next())
            {
                r.add(rs.getString("ProductID"));
                r.add(rs.getString("Details"));
                r.add(rs.getString("Company"));
                r.add(rs.getString("Quantity"));
            }
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    public static ArrayList<String> getHistory(String user,String comp)
    {
        String q;
        ArrayList<String> r=new ArrayList<String>();
        if(comp.equals("All"))
            q="Select nume_produs,company,data,cost from history WHERE username = '"+user+"';";
        else
            q="Select nume_produs,company,data,cost from history WHERE username = '"+user+"' and company = '"+comp+"';";
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(q);
            while(rs.next())
            {
                r.add(rs.getString("nume_produs"));
                r.add(rs.getString("company"));
                r.add(rs.getString("data"));
                r.add(rs.getString("cost"));
            }
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    public static String getPDetail(String id,int q)
    {
        Connection conn=DBConnection();
        String rt="";
        try {
            int quan;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
            if (!rs.next())
                rt="nill";
            else{
                quan=Integer.parseInt(rs.getString("Quantity"))-q;
                if(quan<0)
                    rt="item is out of stock";
                else
                {
                    rt=rs.getString("Details")+"%"+rs.getString("Company");
                    statement.executeUpdate("UPDATE stock set Quantity = "+quan+" WHERE ProductID = '"+id+"';");
                }

            }

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return rt;
    }

    public static ArrayList<String> searchP(String id)
    {
        Connection conn=DBConnection();
        ArrayList<String> data=new ArrayList<String>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
            if (rs.next())
            {
                data.add(rs.getString("Details"));
                data.add(rs.getString("Company"));
                data.add(rs.getString("Quantity"));
            }

            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    public static void updateProduct(String id,int quan)
    {
        Connection conn=DBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from stock WHERE ProductID = '"+id+"';");
            int q=0;
            if(rs.next())
            {
                q=Integer.parseInt(rs.getString("Quantity"))+quan;
                statement.executeUpdate("UPDATE stock set Quantity = "+q+" WHERE ProductID = '"+id+"';");

            }
            conn.close();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }

    }
    public static void main(String args[])
    {

    }
}

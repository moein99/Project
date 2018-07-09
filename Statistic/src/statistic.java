/**
 * Created by M.H.GH.K on 7/8/2018.
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class statistic {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/food2";
    static final String USER = "root";
    static final String PASS = null;
    private Statement mystatement;
    private Connection myConnection;
    private String SystemAcc;
    public void getSystemNumberOfFood() {   //az har samane chand ghaza sefaresh dade shode ast !!
        try {
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            String sql = "select  typpe,sum(numberoffood) AS numberfood from orderincludefood natural join orderinghistory group by typpe order by sum(numberoffood) desc";
            ResultSet myTable = mystatement.executeQuery(sql);
            while (myTable.next()) {
                String type = myTable.getString("typpe");
                int numberOfFood = myTable.getInt("numberfood");
                System.out.print("type: " + type);
                System.out.println(" , numberOfFood : " + numberOfFood);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void getSystemHowMuchMoney() {   //az har samane che ghadr kharid shode ast!
        try {
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            String sql = "select typpe,sum(cast(totalCost AS signed)) AS Money from  orderinghistory natural join orderincludefood group by typpe desc";
            ResultSet myTable = mystatement.executeQuery(sql);
            while (myTable.next()) {
                String type = myTable.getString("typpe");
                int numberOfFood = myTable.getInt("Money");
                System.out.print("type: " + type);
                System.out.println(" , Money : " + numberOfFood + "تومان");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////Does not work
    public void eachSystemTopRestaurant() {   //az har samane che ghadr kharid shode ast!
        this.SystemAcc=SystemAcc;
        try {
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            String sql = "select nameOfRestaurant,sum(numberoffood) AS NumberOffFood from  orderinghistory natural join orderincludefood where typpe='اسنپ فود' group by nameOfRestaurant order by NumberOffFood desc";
            ResultSet myTable = mystatement.executeQuery(sql);
            while (myTable.next()) {
                String type = myTable.getString("nameOfRestaurant");
                int numberOfFood = myTable.getInt("NumberOffFood");
                System.out.print("nameOfRestaurant: " + type);
                System.out.println(" , NumberOfFood : " + numberOfFood );
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void TopRestaurant(){ //dar kolle samane ha kodam restaurant bishtarin kharid ra dashte!
        try {
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            String sql = "select typpe, nameOfRestaurant ,sum(numberoffood) AS NumberOffFood from  orderinghistory natural join orderincludefood natural join receptfood group by typpe,nameOfRestaurant order by sum(numberoffood) DESC";
            ResultSet myTable = mystatement.executeQuery(sql);
            while (myTable.next()) {
                String type = myTable.getString("typpe");
                String nameOfRest = myTable.getString("nameOfRestaurant");
                int NumberOfFood=myTable.getInt("NumberOffFood");
                System.out.print("type:" + type);
                System.out.print(",nameOfRestaurant:" + nameOfRest);
                System.out.println(",NumberOfFood:" + NumberOfFood );
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

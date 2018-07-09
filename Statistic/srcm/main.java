/**
 * Created by M.H.GH.K on 7/9/2018.
 */
import java.sql.*;
import java.sql.Connection;
public class main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/food2";
    static final String USER = "root";
    static final String PASS = null;
    static private Statement mystatement;
    static private Connection myConnection;
    public static void main(String[] args){
        ManageDataBase manage=new ManageDataBase();
        statistic statistic=new statistic();

        //////////////All FUNCTION////////////////////:
        //statistic.getSystemHowMuchMoney();
        //statistic.TopRestaurant();
        //statistic.getSystemNumberOfFood();
        //statistic.eachSystemTopRestaurant();///////////////////////////////////////////////////////////////////////////////////////////////////////////Does not work
        //manage.addToDataBase();
        //manage.addToReceptFood();
        //manage.addToReceptFood();
    }
    }




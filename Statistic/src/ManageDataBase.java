
/**
 * Created by M.H.GH.K on 7/7/2018.
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class ManageDataBase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/food2?useUnicode=true&characterEncoding=UTF-8";
    static final String USER = "root";
    static final String PASS = null;
    private Statement mystatement;
    private Connection myConnection;
    private String TYPE;
    private int RESTAURANT_CODE;
    private String NAME_OF_RESTAURANT;
    private int FOOD_CODE;
    private String NAME_OF_FOOD;
    private String PHONE_NUMBER;
    private String TOTAL_COST;
    private int IDOrderingHistory=0; //Trigger In DataBase
    private int ReceptFoodID=0;
    private int IDReceptFood=20; //Trigger In DataBase
    private String DATE;
    private String time;
    private String getTotalCost() {
        //sum=0
        //for:
        //calculate sum=number*(each cost item) + sum
        return "95000";
    }
    private int getIDOrderingHistory() {                ////////////////////////100% is Correct

        try {
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            ResultSet myResaultSet;
            myResaultSet = mystatement.executeQuery("SELECT max(IDOrderingHistory) from food2.OrderingHistory");
            int index = 0;
            if (myResaultSet.next())
                index = myResaultSet.getInt(1);
            IDOrderingHistory = index + 1;
            return IDOrderingHistory;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }
    private int getRecptFoodID() {                ////////////////////////100% is Correct

        try {
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            ResultSet myResaultSet;
            myResaultSet = mystatement.executeQuery("SELECT max(ReceptFoodID) from food2.ReceptFood");
            int index = 0;
            if (myResaultSet.next())
                index = myResaultSet.getInt(1);
            ReceptFoodID = index +1 ;
            return ReceptFoodID;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return 0;
        }
    }
    public void addToOrderingHistory(){
        try{
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();

            TYPE="چنگال";
            RESTAURANT_CODE=16;
            NAME_OF_RESTAURANT="رستوران اقدسیه";
            PHONE_NUMBER="09121471561";
            TOTAL_COST=getTotalCost();
            //DATE;
            // time;
            IDOrderingHistory=getIDOrderingHistory();
            System.out.print(IDOrderingHistory);
            String sql="INSERT INTO `food2`.`orderinghistory` (`IDOrderingHistory`, `typpe`, `phoneNumber`, `nameOfRestaurant`, `RestaurantCode`, `totalCost`) "
                    +"VALUES ('"+IDOrderingHistory+"' , '"+TYPE+"' ,'"+PHONE_NUMBER+"' , '"+NAME_OF_RESTAURANT+"' , '"+RESTAURANT_CODE+"', '"+TOTAL_COST+"')";
            mystatement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void addToReceptFood(){
        try{
            Class.forName(JDBC_DRIVER);
            myConnection = DriverManager.getConnection(URL, USER, PASS);
            mystatement = myConnection.createStatement();
            TYPE="چنگال";
            IDReceptFood=getRecptFoodID();
            FOOD_CODE=10;
            NAME_OF_FOOD="چلو خورش ";
            int numberOfFood=5;

            // for each item is in one factor:
            // -------------------------------------------------------------------------------------------------------------------------------
            String cost="12000";
            String sql="INSERT INTO `food2`.`receptfood` (`ReceptFoodID`, `FoodCode`, `NameOfFood`, `cost`) "
                    +"VALUES ('"+IDReceptFood+"' , '"+FOOD_CODE+"' ,'"+NAME_OF_FOOD+"' , '"+cost+"')";
            mystatement.executeUpdate(sql);

            String sqlOne="INSERT INTO `food2`.`orderincludefood` (`IDOrderingHistory`, `ReceptFoodID`, `numberOfFood`) "
                    +"VALUES ('"+IDOrderingHistory+"' , '"+IDReceptFood+"' ,'"+numberOfFood+"' )";
            mystatement.executeUpdate(sqlOne);
            //-------------------------------------------------------------------------------------------------------------------------------
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void addToDataBase(){
        addToOrderingHistory();
        addToReceptFood();
    }







}

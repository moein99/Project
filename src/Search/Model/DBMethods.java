package Search.Model;

import java.sql.*;

public class DBMethods
{
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/db";
    private final String USER = "root";
    private final String PASS = "moeincarnal77";
    private Connection conn = null;
    DBMethods()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean addUser(int id, String username, String password, String email)
    {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            String sql = "SELECT username, email FROM users";
            ResultSet result = statement.executeQuery(sql);
            while (result.next())
            {
                String temporaryUsername = result.getString("username");
                String temporaryEmail = result.getString("email");
                if (username.equals(temporaryUsername) || temporaryEmail.equals(email))
                {
                    return false;
                }
            }
            String temp = "'%'";
            sql = "INSERT INTO users VALUES (" + id + ", " + temp.replace("%", username) + ", " + temp.replace("%", password) +
                    ", " + temp.replace("%", email) + ");";
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void login(String username, String password)
    {
        Statement statement = null;
        try
        {
            String temp = "'%'";
            statement = conn.createStatement();
            String sql = "select password from users where username = " + temp.replace("%", username);
            ResultSet result = statement.executeQuery(sql);
            while (result.next())
            {
                String pass = result.getString("password");
                if (pass.equals(password))
                {
                    // what ...?!
                }
                else
                {
                    // what ...?!
                }
            }
        }
        catch (java.sql.SQLException e)
        {
            e.printStackTrace();
        }
    }
}

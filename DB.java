import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

public class DB {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/UserDatabase";
    private static final String SERVER_URL = "jdbc:mysql://localhost/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    User user = null;

    Connection conn = null;
    Statement createStatement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;


    // DB - Creating Connection
    public DB() {
        try {
            // Registering the JDBC driver
            Class.forName(DB_DRIVER);
            // Open the MySQL database connection
            conn = DriverManager.getConnection(SERVER_URL, USERNAME, PASSWORD);
            System.out.println("Connection was successful!");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Connection has failed!");
        }


        // Initializing Statement for SQL queries
        if(conn != null) {
            try {
                createStatement = conn.createStatement();
            }catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to initialize createStatement!");
            }
        }else{
            System.out.println("Connection is Null!");
        }


        // Create the whole UserDatabase DB if it's not exists
        try {
            conn = DriverManager.getConnection(SERVER_URL, USERNAME, PASSWORD);
            createStatement = conn.createStatement();
            createStatement.executeUpdate("CREATE DATABASE IF NOT EXISTS UserDatabase");
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to create the UserDatabase!");
        }


        // Create the user Table if not exists
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            createStatement = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS user "
                    + "(id INT ( 11 ) NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR (75) NOT NULL, "
                    + "email VARCHAR(75) NOT NULL UNIQUE, "
                    + "password VARCHAR(175) NOT NULL)";
            createStatement.executeUpdate(sql);
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to create the user Table!");
        }
    }



    // Insert records into the DB method
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong with the addUser method!");
        }
    }



    // User authentication on Login page
    public User getUser(String email, String password) {
        user = null;
        try {
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            rs = preparedStatement.executeQuery();

            if(rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Authentication is failed!");
        }
        return user;
    }


}




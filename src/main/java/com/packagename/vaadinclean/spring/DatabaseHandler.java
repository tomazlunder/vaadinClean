package com.packagename.vaadinclean.spring;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

    public DatabaseHandler(){
    }

    private Connection makeConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/cleanbase", "user1", "awdqseww22"
            );
            System.out.println("DatabaseHander: Connection succesful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander: Database connection failed!");
        }

        return connection;
    }

    public ArrayList<String> getSaltAndHash(String username){
        ArrayList<String> saltAndHash = new ArrayList();
        String pSalt = "";
        String pHash = "";

        Connection connection = makeConnection();
        if(connection == null){
            return null;
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT pSalt,pHash FROM users WHERE userName = ?");

            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            pSalt = rs.getString(1);
            pHash = rs.getString(2);

            saltAndHash.add(0, pSalt);
            saltAndHash.add(1,pHash);

            connection.close();
            return saltAndHash;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#getSaltAndHash: prepareStatementFailed!");
            return null;
        }
    }

    public boolean setSaltAndHash(String username, String salt, String hash){
        Connection connection = makeConnection();
        if(connection == null){
            return false;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET pSalt=?, pHash=? WHERE username = ?");
            preparedStatement.setString(1, salt);
            preparedStatement.setString(2, hash);
            preparedStatement.setString(3, username);

            preparedStatement.execute();
            connection.commit();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#setSaltAndHash: prepareStatementFailed!");
            return false;
        }
    }
}

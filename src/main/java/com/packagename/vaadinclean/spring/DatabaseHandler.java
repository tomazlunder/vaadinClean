package com.packagename.vaadinclean.spring;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

    public DatabaseHandler() {
    }

    private Connection makeConnection() {
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

    public ArrayList<String> getSaltAndHash(String username) {
        ArrayList<String> saltAndHash = new ArrayList();
        String pSalt = "";
        String pHash = "";

        Connection connection = makeConnection();
        if (connection == null) {
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
            saltAndHash.add(1, pHash);

            connection.close();
            return saltAndHash;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#getSaltAndHash: prepareStatementFailed!");
            return null;
        }
    }

    public boolean setSaltAndHash(String username, String salt, String hash) {
        Connection connection = makeConnection();
        if (connection == null) {
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

    public ArrayList<ArrayList<String>> getOpravila() {
        Connection connection = makeConnection();
        if (connection == null) {
            return null;
        }

        ArrayList<ArrayList<String>> toReturn = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM opravilo");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ArrayList<String> pair = new ArrayList<>();
                pair.add(rs.getString(2));
                pair.add(rs.getString(3));
                toReturn.add(pair);
            }
            return toReturn;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#getOpravila: prepareStatementFailed!");
            return null;
        }
    }

    public String getDezurni() {
        Connection connection = makeConnection();
        if (connection == null) {
            return null;
        }

        try {
                /*SELECT u.userName
FROM teden t JOIN users u on t.userID = u.userID
WHERE t.dateStart <= CURRENT_DATE AND t.dateEnd> CURRENT_DATE;*/
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.userName\n" +
                    "FROM teden t JOIN users u on t.userID = u.userID\n" +
                    "WHERE t.dateStart <= CURRENT_DATE AND t.dateEnd> CURRENT_DATE;");

            ResultSet rs = preparedStatement.executeQuery();
            connection.close();


            rs.next();
            return rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#getOpravila: prepareStatementFailed!");
            return null;
        }
    }

    public boolean isDayDone(){
        Connection connection = makeConnection();
        if (connection == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT opravljen FROM dan WHERE date = CURRENT_DATE ");

            ResultSet rs = preparedStatement.executeQuery();
            connection.close();

            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#getOpravila: prepareStatementFailed!");
            return false;
        }
    }

    public void setCurrentDayDone(){
        Connection connection = makeConnection();
        if (connection == null) {
            return;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE dan SET opravljen=TRUE WHERE date = CURRENT_DATE ");
            preparedStatement.execute();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseHander#setCurrentDayDone: prepareStatementFailed!");
        }
    }
}

package com.backend.datalayer;

import com.entity.Role;
import com.utils.JDBCUtils;

import java.io.IOException;
import java.sql.*;

/**
 * This class is User Repository class to connect vs database
 * @author TranAnhTuan
 */
public class UserRepository implements IUserRepository {
    private final JDBCUtils jdbcUtils;
    private Connection connection;

    /**
     * The constructor of class User Repo
     */
    public UserRepository() throws IOException {
        this.jdbcUtils = new JDBCUtils();
    }


    @Override
    public void getListUserByProjectID(int inputProjectID) throws SQLException {
        //Get connection
        connection = jdbcUtils.connect();
        //Create statement
        String sql = "SELECT pau.projectId, u.fullName, pau.Role " +
                "FROM `ProjectAndUser` pau " +
                "JOIN `User` u ON u.id = pau.userId " +
                "WHERE pau.projectId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //Set parameter
        preparedStatement.setInt(1, inputProjectID);
        //Execute query
        ResultSet resultSet = preparedStatement.executeQuery();
        //Handing result
        while (resultSet.next()) {
            int projectID = resultSet.getInt(1);
            String fullName = resultSet.getString(2);
            Role role = Role.valueOf(resultSet.getString(3));
            System.out.printf("%-9s || %-20s || %-10s\n", projectID, fullName, role);
        }
        jdbcUtils.disconnect();
    }

    @Override
    public void getListProjectManager() throws Exception {
        //Get connection
        connection = jdbcUtils.connect();
        //Create statement
        String sql = "SELECT pau.projectId, u.`fullName`, pau.`Role` " +
                "FROM `ProjectAndUser` pau " +
                "JOIN `User` u ON u.id = pau.userId " +
                "WHERE `Role` = 'MANAGER'";
        Statement statement = connection.createStatement();
        //Execute query
        ResultSet resultSet = statement.executeQuery(sql);
        //Handing result
        while (resultSet.next()) {
            int projectID = resultSet.getInt(1);
            String fullName = resultSet.getString(2);
            Role role = Role.valueOf(resultSet.getString(3));
            System.out.printf("%-5s || %-20s || %-10s\n", projectID, fullName, role);
        }
        jdbcUtils.disconnect();
    }

    @Override
    public void login(String email, String password) throws Exception {
        try {
            //Get connection
            connection = jdbcUtils.connect();
            //Create Satement
            String sql = "SELECT `fullName` " +
                    "FROM `User` " +
                    "WHERE `email` = ? AND `password` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //set parameter
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            //execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new Exception(
                        JDBCUtils.getMessage().getProperty("login.emailOrPasswordWrong")
                );
            }
        } finally {
            jdbcUtils.disconnect();
        }
    }

    @Override
    public boolean isProjectIDExist(int id) throws SQLException {
        //Get connect
        connection = jdbcUtils.connect();

        //Create Statement
        String sql = "SELECT * FROM Project WHERE ID = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //set parameter
        preparedStatement.setInt(1,id);
        //Execute query
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            jdbcUtils.disconnect();
            return true;
        }
        jdbcUtils.disconnect();
        return false;
    }

    @Override
    public boolean isHasUserWorkingOnProject(int id) throws SQLException {
        //Get connect
        connection = jdbcUtils.connect();

        //Create Statement
        String sql = "SELECT * FROM `projectanduser` WHERE projectId = ?" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //set parameter
        preparedStatement.setInt(1,id);
        //Execute query
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            jdbcUtils.disconnect();
            return true;
        }
        jdbcUtils.disconnect();
        return false;
    }


}

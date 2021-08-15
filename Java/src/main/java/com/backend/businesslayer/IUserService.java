package com.backend.businesslayer;

import java.sql.SQLException;

public interface IUserService {
    /**
     * The method is return list user by projectID __Question2
     * @author TranAnhTuan
     */
    void getListUserByProjectID(int projectID) throws Exception;

    /**
     * The method return list Projects's manager __Question3
     * @author TranAnhTuan
     */
    void getListProjectManager() throws Exception;

    /**
     * The method to login __Question4
     * @author TranAnhTuan
     */
    void login(String email,String password) throws Exception;

    /**
     * The method check projectID
     * @author TranAnhTuan
     */
    boolean isProjectIDExist(int id) throws SQLException;

    /**
     * The method will check if there is a user working on a project with an input project ID
     *
     * @author TranAnhTuan
     */
    boolean isHasUserWorkingOnProject(int id) throws SQLException;
}

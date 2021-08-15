package com.backend.businesslayer;

import com.backend.datalayer.IUserRepository;
import com.backend.datalayer.UserRepository;
import com.utils.JDBCUtils;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The class is User Service to connect vs User Repository class and throw Exception relate to business logic
 * @author TranAnhTuan
 */
public class UserService implements IUserService {
    private final IUserRepository iUserRepository;

    /**
     * Constructor of User Service class
     */
    public UserService() throws IOException {
        this.iUserRepository = new UserRepository();
    }


    @Override
    public void getListUserByProjectID(int projectID) throws Exception {
        //Handing business logic
        if (!iUserRepository.isProjectIDExist(projectID)) {
            throw new Exception(
                    JDBCUtils.getMessage().getProperty("project.checkIDExist.cannotFindProjectByID") + " " + projectID
            );
        }else if (!iUserRepository.isHasUserWorkingOnProject(projectID)) {
            throw new Exception(
                    JDBCUtils.getMessage().getProperty("project.checkHasUserWorkingOnProject.Null") + " " + projectID
            );
        }
        System.out.printf("%s || %-20s || %-10s\n", "ProjectID", "FullName", "Role");
        iUserRepository.getListUserByProjectID(projectID);
    }

    @Override
    public void getListProjectManager() throws Exception {
        iUserRepository.getListProjectManager();
    }

    @Override
    public void login(String email, String password) throws Exception {
        iUserRepository.login(email,password);
    }

    @Override
    public boolean isProjectIDExist(int id) throws SQLException {
        return iUserRepository.isProjectIDExist(id);
    }

    @Override
    public boolean isHasUserWorkingOnProject(int id) throws SQLException {
        return iUserRepository.isHasUserWorkingOnProject(id);
    }
}

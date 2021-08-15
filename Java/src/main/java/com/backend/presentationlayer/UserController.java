package com.backend.presentationlayer;

import com.backend.businesslayer.IUserService;
import com.backend.businesslayer.UserService;

import java.io.IOException;

public class UserController {
    private final IUserService iUserService;

    public UserController() throws IOException {
        this.iUserService = new UserService();
    }

    public void getListUserByProjectID(int projectID) throws Exception {
        iUserService.getListUserByProjectID(projectID);
    }

    public void getListProjectManager() throws Exception {
        iUserService.getListProjectManager();
    }

    public void login(String email, String password) throws Exception {
        iUserService.login(email,password);
    }
}

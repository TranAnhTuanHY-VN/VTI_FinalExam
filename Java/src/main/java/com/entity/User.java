package com.entity;

public class User {
    private int ID;
    private String fullName;
    private String email;
    private String password;
    private Role role;

    public User(int ID, String fullName, String email, String password, Role role) {
        this.ID = ID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }



    public int getID() {
        return ID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "ID=" + ID + "||" +
                " FullName='" + fullName + '\'' + "||" +
                " Email='" + email + '\'' + "||" +
                " Password='" + password + '\'' ;
    }

}

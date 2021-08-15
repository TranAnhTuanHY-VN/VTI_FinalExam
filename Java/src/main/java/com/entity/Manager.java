package com.entity;

public class Manager extends User {

    private int expInYear;

    public Manager(int ID, String fullName, String email, String password, Role role, int expInYear) {
        super(ID, fullName, email, password, role);
        this.expInYear = expInYear;
    }

    public int getExpInYear() {
        return expInYear;
    }

    @Override
    public String toString() {
        return "||" +"expInYear=" + expInYear;
    }
}

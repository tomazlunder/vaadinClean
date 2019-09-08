package com.packagename.vaadinclean.spring;

import java.util.Base64;

public class TestClass {
    public static void main(String[] args){
        int weeknum = TimeUtils.getStaringDateNumber();
        System.out.println(weeknum);

        SimpleAccessControl simpleAccessControl = new SimpleAccessControl();

    }

    public String hashPassword(String password){
        byte[] salt = Passwords.getNextSalt();
        System.out.println("Salt:" + Base64.getEncoder().encodeToString(salt));

        return Base64.getEncoder().encodeToString(Passwords.hash(password.toCharArray(),salt));
    }
}

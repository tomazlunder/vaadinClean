package com.packagename.vaadinclean.spring;

import java.util.ArrayList;
import java.util.Base64;

public class SimpleAccessControl implements AccessControl {

    @Override
    public boolean checkUsernamePassword(String username, String password) {
        String pSalt;
        String pHash;

        ArrayList<String> saltAndHash = Application.databaseHandler.getSaltAndHash(username);
        if(saltAndHash == null) return false;

        pSalt = saltAndHash.get(0);
        pHash = saltAndHash.get(1);

        return checkPassword(password, pSalt, pHash);
    }

    @Override
    public boolean isUserSignedIn() {
        return false;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public String getPrincipalName() {
        return null;
    }


    @Override
    public boolean setPassword(String username, String newPassword){
            byte[] newSalt = Passwords.getNextSalt();
            String newSaltStr = Base64.getEncoder().encodeToString(newSalt);

            byte[] newSaltedPwd = Passwords.hash(newPassword.toCharArray(), newSalt);
            String newSaltedPwdStr = Base64.getEncoder().encodeToString(newSaltedPwd);

            return Application.databaseHandler.setSaltAndHash(username, newSaltStr, newSaltedPwdStr);
    }


    private boolean checkPassword(String password, String salt, String expected){
        byte[] saltB = Base64.getDecoder().decode(salt);
        byte[] expectedB = Base64.getDecoder().decode(expected);

        return Passwords.isExpectedPassword(password.toCharArray(), saltB, expectedB);
    }

}

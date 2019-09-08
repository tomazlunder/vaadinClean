package com.packagename.vaadinclean.spring;

public interface AccessControl{
    public boolean checkUsernamePassword(String username, String password);

    public boolean isUserSignedIn();

    public boolean isUserInRole(String role);

    public String getPrincipalName();

    public boolean setPassword(String username, String newPassword);
}

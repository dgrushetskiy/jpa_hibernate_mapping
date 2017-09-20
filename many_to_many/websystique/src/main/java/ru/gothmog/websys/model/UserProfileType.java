package ru.gothmog.websys.model;

/**
 * @author gothmog on 17.09.2017.
 */
public enum UserProfileType {

    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}

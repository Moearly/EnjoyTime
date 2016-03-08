package com.martn.enjoytime.bean;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.bean
 * Description: ("用户类")
 * Date 2016/3/8 16:47
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class User {
    static User user;

    String gender;
    int integral;
    int isLogin;
    int isUpload;
    String nickname;
    String profession;
    String uid;
    int userId;
    String userName;

    static {
        user = null;
    }

    private User() {
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(int isUpload) {
        this.isUpload = isUpload;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

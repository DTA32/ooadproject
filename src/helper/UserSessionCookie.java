package helper;

public class UserSessionCookie {

    private static UserSessionCookie instance;
    private Integer user_id;
    private String UserName;

    private UserSessionCookie(){

    }

    public static UserSessionCookie getInstance(){
        if(instance == null){
            instance = new UserSessionCookie();
        }
        return instance;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void clearSession(){
        user_id = null;
        UserName = null;
    }

    public boolean isLoggedIn(){
        return user_id != null;
    }
}

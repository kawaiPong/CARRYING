package kr.hs.emirim.lyn.carrying.Retrofit;

public class User {

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }

    //    private int num;
    private String nickname;
    private String uid;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    private int gender;


    public User(String nickname, String uid,String email, String password, int gender){
        this.nickname=nickname;
        this.uid=uid;
        this.email=email;
        this.password=password;
        this.gender=gender;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


}


package kr.hs.emirim.lyn.carrying.Retrofit;

public class User {


    private int num;
    private String nickname;
    private String uid;
    private int gender;


    public User(int num, String nickname, String uid, int gender){
        this.num=num;
        this.nickname=nickname;
        this.uid=uid;
        this.gender=gender;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    @Override
    public String toString() {
        return "Data_user{" +
                "num=" + num +
                ", nickname='" + nickname + '\'' +
                ", uid='" + uid + '\'' +
                ", gender=" + gender +
                '}';
    }
}


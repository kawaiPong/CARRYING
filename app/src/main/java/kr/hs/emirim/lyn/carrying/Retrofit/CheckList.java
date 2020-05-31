package kr.hs.emirim.lyn.carrying.Retrofit;

public class CheckList {

    // list/ 에서 되는 모든것들
    //:List 로 클래스 이름 하니까 오류남
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getListNum() {
        return listNum;
    }

    public void setListNum(int listNum) {
        this.listNum = listNum;
    }

    private int listNum;
    private int num;
    private String title;
    private String city;
    private String start_date;
    private String finish_date;
    private String uid;
    private String theme;
    private int gender;
    private String weather;

    public CheckList(int listnum, int num, String title, String city, String start_date, String finish_date, String uid, String theme, int gender, String weather) {

        this.listNum = listnum;
        this.num = num;
        this.title = title;
        this.city = city;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.uid = uid;
        this.theme = theme;
        this.gender = gender;
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "CheckList{" +
                "listnum=" + listNum +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", start_date='" + start_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                ", uid='" + uid + '\'' +
                ", theme='" + theme + '\'' +
                ", gender=" + gender +
                ", weather='" + weather + '\'' +
                '}';
    }
}

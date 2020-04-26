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

    private String title;
    private String city;
    private String start_date;
    private String finish_date;
    private String theme;


    @Override
    public String toString() {
        return "List{" +
                "title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", start_date='" + start_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }


    public CheckList(String title, String city, String start_date, String finish_date, String theme) {
        this.title = title;
        this.city = city;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.theme = theme;
    }

}

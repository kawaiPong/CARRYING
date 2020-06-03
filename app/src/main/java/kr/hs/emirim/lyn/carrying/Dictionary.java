package kr.hs.emirim.lyn.carrying;

public class Dictionary {
    private int num;
    private String title;
    private String start_date;
    private String finish_date;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Dictionary(int num, String title, String start_date, String finish_date) {
        this.num = num;
        this.title = title;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", start_date='" + start_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                '}';
    }
}

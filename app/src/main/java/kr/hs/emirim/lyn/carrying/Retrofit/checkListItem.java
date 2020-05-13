package kr.hs.emirim.lyn.carrying.Retrofit;

public class checkListItem {

    private int check_num;
    private String name;
    private int list_num;
    private int status;



    @Override
    public String toString() {
        return "checkListItem{" +
                "check_num=" + check_num +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", list_num=" + list_num +
                '}';
    }

    public checkListItem(int check_num, String name, int status, int list_num) {
        this.check_num = check_num;
        this.name = name;
        this.status = status;
        this.list_num = list_num;
    }




    public int getCheck_num() {
        return check_num;
    }

    public void setCheck_num(int check_num) {
        this.check_num = check_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getList_num() {
        return list_num;
    }

    public void setList_num(int list_num) {
        this.list_num = list_num;
    }


}

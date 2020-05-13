package kr.hs.emirim.lyn.carrying.Retrofit;

public class checkListItem {

    private int check_num;//아이템 숫자
    private String name;//아이템 이름
    private int list_num;//리스트의 넘버
    private int status;//상태



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

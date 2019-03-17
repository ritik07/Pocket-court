package com.example.monster_07.vectoroso;

public class advocate {
    public String in_process;
    public String lose;
    public String win;
    public String total_case;
    public String specialist;
    public String rating;
    public String pic;

    public advocate(){

    }

    public advocate(String in_process, String lose, String win, String total_case, String specialist, String rating, String pic) {
        this.in_process = in_process;
        this.lose = lose;
        this.win = win;
        this.total_case = total_case;
        this.specialist = specialist;
        this.rating = rating;
        this.pic = pic;
    }

    public String getIn_process() {
        return in_process;
    }

    public void setIn_process(String in_process) {
        this.in_process = in_process;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getTotal_case() {
        return total_case;
    }

    public void setTotal_case(String total_case) {
        this.total_case = total_case;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

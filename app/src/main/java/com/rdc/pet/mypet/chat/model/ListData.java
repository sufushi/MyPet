package com.rdc.pet.mypet.chat.model;

public class ListData {

    public static final int send = 1;
    public static final int receiver = 2;

    private String content;
    private String time;
    private int flag;

    public ListData(String content, int flag, String time) {
        super();
        this.content = content;
        this.time = time;
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

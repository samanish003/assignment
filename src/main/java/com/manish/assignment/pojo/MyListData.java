package com.manish.assignment.pojo;

public class MyListData {
    private String name;
    private String flag;
    public MyListData(String description, String flag) {
        this.name = description;
        this.flag = flag;
    }
    public String getDescription() {
        return name;
    }
    public void setDescription(String name) {
        this.name = name;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
}

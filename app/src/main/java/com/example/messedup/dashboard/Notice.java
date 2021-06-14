package com.example.messedup.dashboard;

public class Notice {

    String name, date, time, title, desc, image;

    public Notice() {
    }

    public Notice(String name, String date, String time, String title, String desc, String image) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public Notice(String name, String date, String time, String title, String desc) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.title = title;
        this.desc = desc;
    }

    public Notice(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

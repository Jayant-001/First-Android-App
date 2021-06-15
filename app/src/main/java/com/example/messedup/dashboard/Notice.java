package com.example.messedup.dashboard;

public class Notice {

    String author, date, time, title, desc, image;

    public Notice() {
    }

    public Notice(String author, String date, String time, String title, String desc, String image) {
        this.author = author;
        this.date = date;
        this.time = time;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public Notice(String author, String date, String time, String title, String desc) {
        this.author = author;
        this.date = date;
        this.time = time;
        this.title = title;
        this.desc = desc;
    }


    @Override
    public String toString() {
        return "Notice{" +
                "author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
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

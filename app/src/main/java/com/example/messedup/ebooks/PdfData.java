package com.example.messedup.ebooks;

public class PdfData {

    String title, url, date, branch, author;

    public PdfData() {
    }

    public PdfData(String title, String url, String date, String branch, String author) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.branch = branch;
        this.author = author;
    }

    @Override
    public String toString() {
        return "PdfData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", branch='" + branch + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

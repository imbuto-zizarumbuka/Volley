package com.example.crud_lab;

import java.io.Serializable;

public class Contact implements Serializable {
    String avatar_url;
    String login;
    String html_url;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact() {
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public Contact(String avatar_url, String login, String html_url) {
        this.avatar_url = avatar_url;
        this.login = login;
        this.html_url = html_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
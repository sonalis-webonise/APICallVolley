package com.example.webonise.jsonparserdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.JsonElement;

/**
 * Created by webonise on 23/9/16.
 */
public class UserPost {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public UserPost() {
    }

    public int getPostId() {
        return postId;
    }


    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}

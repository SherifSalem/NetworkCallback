package com.example.sherif.networcallbacks;

/**
 * Created by sherif on 29/08/17.
 */

public class CommentsList{

    private String title;
    private String description;
    private String userId;
    private String id;
    private int img;
    public CommentsList(String title, String description, String user, String ID, int img ) {
        this.title = title;
        this.description = description;
        this.userId = user;
        this.id = ID;
        this.img = img;
    }
    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public int getImg() {
        return img;
    }



    public  String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}






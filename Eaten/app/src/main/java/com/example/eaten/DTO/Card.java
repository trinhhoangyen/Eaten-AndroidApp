package com.example.eaten.DTO;

public class Card {
    private int postId;
    private int accountId;
    private String postName;
    private String content;
    private String address;
    private String displayName;
    private String picture;
    private int reactQuantity;

    public Card(int postId, int accountId, String postName, String content, String address, String displayName, String picture, int reactQuantity) {
        this.postId = postId;
        this.accountId = accountId;
        this.postName = postName;
        this.content = content;
        this.address = address;
        this.displayName = displayName;
        this.picture = picture;
        this.reactQuantity = reactQuantity;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getReactQuantity() {
        return reactQuantity;
    }

    public void setReactQuantity(int reactQuantity) {
        this.reactQuantity = reactQuantity;
    }
}

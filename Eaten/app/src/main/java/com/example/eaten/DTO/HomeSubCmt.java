package com.example.eaten.DTO;

public class HomeSubCmt {
    private int postId;
    private int accountId;
    private String avatarURL;
    private String displayName;
    private String content;

    public HomeSubCmt(int postId, int accountId, String avatarURL, String displayName, String content) {
        this.postId = postId;
        this.accountId = accountId;
        this.avatarURL = avatarURL;
        this.displayName = displayName;
        this.content = content;
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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.example.eaten.DTO;

public class Account {
    private int accountId, yearOfBirth;
    private String email, password, displayName, avatar, gender;

    public Account() {

    }

    public Account(int accountId, int yearOfBirth, String email, String password, String displayName, String avatar, String gender) {
        this.accountId = accountId;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.avatar = avatar;
        this.gender = gender;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getGender() {
        return gender;
    }
}

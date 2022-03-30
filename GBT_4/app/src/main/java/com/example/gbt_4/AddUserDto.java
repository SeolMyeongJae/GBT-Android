package com.example.gbt_4;

import com.google.gson.annotations.SerializedName;

public class AddUserDto {
    @SerializedName("userName")
    private String userName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthYear")
    private int birthYear;

    @SerializedName("smokingYear")
    private int smokingYear;

    @SerializedName("comment")
    private String comment;

    @SerializedName("price")
    private int price;

    @SerializedName("averageSmoking")
    private int averageSmoking;

    @SerializedName("profileImg")
    private String profileImg;

    @SerializedName("popupImg")
    private String popupImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getSmokingYear() {
        return smokingYear;
    }

    public void setSmokingYear(int smokingYear) {
        this.smokingYear = smokingYear;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAverageSmoking() {
        return averageSmoking;
    }

    public void setAverageSmoking(int averageSmoking) {
        this.averageSmoking = averageSmoking;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getPopupImg() {
        return popupImg;
    }

    public void setPopupImg(String popupImg) {
        this.popupImg = popupImg;
    }

    public AddUserDto(String userName, String gender, int birthYear, int smokingYear, String comment, int price, int averageSmoking, String profileImg, String popupImg) {
        this.userName = userName;
        this.gender = gender;
        this.birthYear = birthYear;
        this.smokingYear = smokingYear;
        this.comment = comment;
        this.price = price;
        this.averageSmoking = averageSmoking;
        this.profileImg = profileImg;
        this.popupImg = popupImg;
    }

    @Override
    public String toString() {
        return "AddUserDto{" +
                "userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear=" + birthYear +
                ", smokingYear=" + smokingYear +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", averageSmoking=" + averageSmoking +
                ", profileImg='" + profileImg + '\'' +
                ", popupImg='" + popupImg + '\'' +
                '}';
    }
}

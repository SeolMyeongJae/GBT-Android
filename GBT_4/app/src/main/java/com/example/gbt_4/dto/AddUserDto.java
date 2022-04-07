package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddUserDto {
    @SerializedName("userName")
    private String userName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthYear")
    private Long birthYear;

    @SerializedName("smokingYear")
    private Long smokingYear;

    @SerializedName("comment")
    private String comment;

    @SerializedName("price")
    private Long price;

    @SerializedName("averageSmoking")
    private Long averageSmoking;

    @SerializedName("ranking")
    private Long ranking;

    @SerializedName("profileImg")
    private String profileImg;

    @SerializedName("popupImg")
    private String popupImg;

    @SerializedName("point")
    private Long point;

    @SerializedName("badgeId")
    private Long badgeId;

    public AddUserDto(String userName, String gender, Long birthYear, Long smokingYear, String comment, Long price, Long averageSmoking, Long ranking, String profileImg, String popupImg, Long point, Long badgeId) {
        this.userName = userName;
        this.gender = gender;
        this.birthYear = birthYear;
        this.smokingYear = smokingYear;
        this.comment = comment;
        this.price = price;
        this.averageSmoking = averageSmoking;
        this.ranking = ranking;
        this.profileImg = profileImg;
        this.popupImg = popupImg;
        this.point = point;
        this.badgeId = badgeId;
    }
}

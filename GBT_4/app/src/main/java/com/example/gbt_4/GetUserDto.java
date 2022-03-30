package com.example.gbt_4;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class GetUserDto {

    @SerializedName("id")
    private Long userId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthYear")
    private Date birthYear;

    @SerializedName("smokingYear")
    private Long smokingYear;

    @SerializedName("comment")
    private String comment;

    @SerializedName("price")
    private Long price;

    @SerializedName("averageSmoking")
    private Long averageSmoking;

    @SerializedName("profileImg")
    private String profileImg;

    @SerializedName("popupImg")
    private String popupImg;

    public GetUserDto(Long userId, String userName, String gender, Date birthYear, Long smokingYear, String comment, Long price, Long averageSmoking, String profileImg, String popupImg) {
        this.userId = userId;
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
}

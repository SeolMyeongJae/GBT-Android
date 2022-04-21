package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserDto {
    @SerializedName("userName")
    private String userName;
    @SerializedName("id")
    private Long userId;
    @SerializedName("profileImg")
    private String photo;
}

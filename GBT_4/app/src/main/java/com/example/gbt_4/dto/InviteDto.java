package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InviteDto {

    @SerializedName("title")
    private String title;
    @SerializedName("caller")
    private String caller;
    @SerializedName("callerId")
    private Long callerId;
    @SerializedName("customChallengeId")
    private Long customChallengeId;
    @SerializedName("userID")
    private Long userId;
}

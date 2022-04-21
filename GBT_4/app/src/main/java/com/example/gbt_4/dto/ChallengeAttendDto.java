package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChallengeAttendDto {
    @SerializedName("userId")
    private Long userId;
    @SerializedName("memo")
    private String memo;
}

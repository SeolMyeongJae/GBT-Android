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
@ToString
@AllArgsConstructor
public class TodayAttendDto {
    @SerializedName("userId")
    private Long userId;
    @SerializedName("count")
    private Long count;
}

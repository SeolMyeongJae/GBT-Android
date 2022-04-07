package com.example.gbt_4.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class GetSmokingDto implements Serializable {
    @SerializedName("userId")
    private Long userId;
    @SerializedName("count")
    private Long count;
    @SerializedName("provider")
    private String provider;

    public GetSmokingDto(Long userId, Long count, String provider) {
        this.userId = userId;
        this.count = count;
        this.provider = provider;
    }
}

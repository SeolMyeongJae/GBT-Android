package com.example.gbt_4;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChallengeItem {
    @SerializedName("description")
    private String description;
    @SerializedName("summary")
    private String summary;
    @SerializedName("endDate")
    private Date endDate;
    @SerializedName("startDate")
    private Date startDate;
    @SerializedName("frequency")
    private Long frequency;
    @SerializedName("img")
    private String img;
    @SerializedName("max")
    private Long max;
    @SerializedName("method")
    private String method;
    @SerializedName("title")
    private String title;

    public ChallengeItem(String description, String summary, Date endDate, Date startDate, Long frequency, String img, Long max, String method, String title) {
        this.description = description;
        this.summary = summary;
        this.endDate = endDate;
        this.startDate = startDate;
        this.frequency = frequency;
        this.img = img;
        this.max = max;
        this.method = method;
        this.title = title;
    }
}

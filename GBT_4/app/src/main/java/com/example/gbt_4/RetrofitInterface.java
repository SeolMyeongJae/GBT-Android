package com.example.gbt_4;

import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.AddUserDto;
import com.example.gbt_4.dto.GetOfficialChallengeDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;
import com.example.gbt_4.dto.UserChallengeDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("user/{userId}")
//    Call<GetUserDto> getByUserId();
    Call<GetUserDto> getByUserId(@Path("userId") int userId);

    @POST("user")
    Call<Integer> addUser(@Body AddUserDto addUserDto);
//    Call<PostUserDto> postFunc(@Field("provider") String provider, @Field("userId") int userId);

    @POST("smoking")
    Call<Integer> addSmoking(@Body AddSmokingDto addSmokingDto);

    @GET("smoking/all/this-month/user/{userId}")
    Call<GetSmokingListDto> getMonthCount(@Path("userId") Long userId);

    @GET("smoking/today/{userId}")
    Call<GetSmokingDto> getTodayCount(@Path("userId") Long userId);

    @GET("challenge/all/{userId}")
    Call<List<GetOfficialChallengeDto>> getAllOfficialChallenge(@Path("userId") Long userId);

    @GET("challenge/{id}")
    Call<GetOfficialChallengeDto> getOfficialChallenge(@Path("id") Long id);

    @POST("user-challenge")
    Call<Integer> participateOfficialChallenge(@Body UserChallengeDto userChallengeDto);



}

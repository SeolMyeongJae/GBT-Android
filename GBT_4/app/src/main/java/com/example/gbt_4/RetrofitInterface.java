package com.example.gbt_4;

import com.example.gbt_4.dto.AddCustomChallengeDto;
import com.example.gbt_4.dto.AddSmokingDto;
import com.example.gbt_4.dto.AddUserDto;
import com.example.gbt_4.dto.ChallengeAttendDto;
import com.example.gbt_4.dto.GetCustomChallengeDto;
import com.example.gbt_4.dto.GetOfficialChallengeDto;
import com.example.gbt_4.dto.GetSmokingDto;
import com.example.gbt_4.dto.GetSmokingListDto;
import com.example.gbt_4.dto.GetUserDto;
import com.example.gbt_4.dto.InviteDto;
import com.example.gbt_4.dto.SearchUserDto;
import com.example.gbt_4.dto.TodayAttendDto;
import com.example.gbt_4.dto.UserChallengeDto;
import com.example.gbt_4.dto.UserCustomChallengeDto;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
//유저 인터페이스
    @GET("user/{userId}")
    Call<GetUserDto> getByUserId(@Path("userId") Long userId);

    @POST("user")
    Call<Integer> addUser(@Body AddUserDto addUserDto);

//흡연정보 인터페이스
    @POST("smoking")
    Call<Long> addSmoking(@Body AddSmokingDto addSmokingDto);

    @POST("smoking/attend")
    Call<Integer> todayAttend(@Body TodayAttendDto todayAttendDto);

    @POST("smoking/challenge-attend")
    Call<Integer> challengeAttend(@Body ChallengeAttendDto challengeAttendDto);


    @GET("smoking/today/{userId}")
    Call<GetSmokingDto> getTodayCount(@Path("userId") Long userId);


//공식 챌린지 인터페이스
    @GET("challenge/all/{userId}")
    Call<List<GetOfficialChallengeDto>> getAllOfficialChallenge(@Path("userId") Long userId);

    @GET("challenge/{id}")
    Call<GetOfficialChallengeDto> getOfficialChallenge(@Path("id") Long id);

    @POST("user-challenge")
    Call<Integer> joinOfficialChallenge(@Body UserChallengeDto userChallengeDto);

//커스텀 챌린지 인터페이스
    @GET("custom/all/{userId}")
    Call<List<GetCustomChallengeDto>> getCustomChallengeByUserId(@Path("userId") Long id);
//    Call<ResponseBody> getCustomChallengeByUserId(@Path("userId") Long id);

    //커스텀 챌린지 불러오기
    @GET("custom/{id}")
    Call<GetCustomChallengeDto> getCustomChallengeById(@Path("id") Long id);

    //커스텀 챌린지 생성
    @POST("custom")
    Call<Integer> addCustomChallenge(@Body AddCustomChallengeDto addCustomChallengeDto);

    //커스텀 챌린지 참여하기
    @POST("user-challenge")
    Call<Integer> joinCustomChallenge(@Body UserCustomChallengeDto userCustomChallengeDto);

//커스텀 챌린지 초대 인터페이스
    @GET("user/username/{userName}")
    Call<SearchUserDto> searchUser(@Path("userName") String userName);

    @POST("invite")
    Call<Integer> inviteUser(@Body InviteDto inviteDto);

    @GET("invite/all/user/{userId}")
    Call<ArrayList<InviteDto>> getAllInviteByUserId(@Path("userId") Long userId);

    @GET("invite/{id}")
    Call<InviteDto> getInviteById(@Path("id") Long id);




}

package com.example.gbt_4;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("user/{userId}")
//    Call<GetUserDto> getByUserId();
    Call<GetUserDto> getByUserId(@Path("userId") int userId);

    @POST("user")
    Call<AddUserDto> addUser(@Body AddUserDto addUserDto);
//    Call<PostUserDto> postFunc(@Field("provider") String provider, @Field("userId") int userId);

    @FormUrlEncoded
    @PUT("user/{id}")
    Call<ResponseBody> putUser(@Path("id") String id, @Field("data") String data);
}

//    // @GET( EndPoint-자원위치(URI) )
//    // DataClass > 요청 GET에 대한 응답데이터를 받아서 DTO 객체화할 클래스 타입 지정
//    // getName > 메소드 명. 자유롭게 설정 가능, 통신에 영향x
//    // @Path("post") String post > 매개변수. 매개변수 post가 @Path("post")를 보고 @GET 내부 {post}에 대입
//    @GET("smoking/get/{userId}")  // 모든 유저의 id값만 받아오는 메서드(id 중복체크를 위해)
//    Call<GetUserDto> getName(@Path("userId") String post);
//
////    @POST("smoking/save")
////    Call<PostUserDto> createUser(@  Body PostUserDto postUserDto);
//
//    @FormUrlEncoded
//    @POST("smoking/save")
//    Call<PostUserDto> createUser(@Field("userId") int userId, @Field("provider") String userName );

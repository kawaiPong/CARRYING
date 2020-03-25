package kr.hs.emirim.lyn.carrying.Retrofit;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitExService {

//    String URL = "http://192.168.9.40:1234";
    String URL= "http://192.168.219.142:4000";


    @GET("/user/readUser/{email}")
    Call<User> getData(@Path("email") String email);

    @GET("/user/existEmail/{email}")
    Call<User>getDataEmail(@Path("email")String email);


    @POST("/user/addUser/{uid}/{nickname}/{email}/{password}/{gender}")
    Call<User> postData(@Path("uid") String uid,@Path("nickname") String nickname,@Path("email") String email,@Path("password") String password,@Path("gender")int gender );

    @POST("/user/updatePassword/{email}/{password}")
    Call<User> postUpdataPassword(@Path("email") String email,@Path("password") String password);

    @PUT("/posts/1")
    Call<User> putData(@Body User param);


    @DELETE("/posts/1")
    Call<ResponseBody> deleteData();


}


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

    String URL = "http://192.168.1.88:1234";

    @GET("/user/read/{uid}")
    Call<User> getData(@Path("uid") String uid);

    @GET("/posts")
    Call<List<User>> getData2(@Query("userId") String userId);

    @POST("/user/addUser")
    Call<User> postData(@Query("nickname") String nickname,@Query("uid") String uid,@Query("email") String email,@Query("password") String password, @Query("gender") int gender );


    @PUT("/posts/1")
    Call<User> putData(@Body User param);


    @DELETE("/posts/1")
    Call<ResponseBody> deleteData();


}


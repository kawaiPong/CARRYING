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

//    @GET("/user/readUser/{email}")
//    Call<User> getData(@Path("email") String email);

    //로그인할 때
    @GET("/user/readUser/{uid}")
    Call<User> getData(@Path("uid") String email);

    @GET("/user/existEmail/{email}")
    Call<User>getDataEmail(@Path("email")String email);

    //회원정보수정때 쓸
    @GET("/user/existNickname/{nickname}")
    Call<User>getDataNickname(@Path("nickname")String nickname);

    //회원가입
    @POST("/user/addUser/{uid}/{nickname}/{email}/{password}/{gender}")
    Call<User> postData(@Path("uid") String uid,@Path("nickname") String nickname,@Path("email") String email,@Path("password") String password,@Path("gender")int gender );

    //비밀번호 수정
    @POST("/user/updatePassword/{email}/{password}")
    Call<User> postUpdataPassword(@Path("email") String email,@Path("password") String password);

    //
    @POST("/user/updateUser/{uid}/{nickname}/{email}/{password}/{gender}")
    Call<User> postUpdateUser(@Path("uid") String uid,@Path("nickname") String nickname,@Path("email") String email,@Path("password") String password,@Path("gender")int gender );

    //새로운 list 만들기
    @POST("/list/addList/{title}/{city}/{start_date}/{finish_date}/{uid}/{theme}")
    Call<CheckList> postCreateList(@Path("title") String title,
                              @Path("city") String city,
                              @Path("start_date") String start_date,
                              @Path("city") String finish_date,
                              @Path("uid") String uid,
                              @Path("theme") String theme);


    //한 명의 회원의 전체 체크리스트 불러오기
    @GET("/list/readAllList/{uid}")
    Call<List<CheckList>> readAllList(@Path("uid")String uid);

    //list에서 item하나 추가
    @POST("/item/deleteCheckListItem/{list_num}/{name}")
    Call<checkListItem> deleteOneItem(@Path("list_num")int list_num, @Path("name")String name);


    @PUT("/posts/1")
    Call<User> putData(@Body User param);


    @DELETE("/posts/1")
    Call<ResponseBody> deleteData();


}


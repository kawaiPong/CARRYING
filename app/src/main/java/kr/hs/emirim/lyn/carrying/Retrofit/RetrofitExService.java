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
    Call<User> getData(@Path("uid") String uid);

    @GET("/user/existEmail/{email}")
    Call<User>getDataEmail(@Path("email")String email);

    //회원정보수정때 쓸
    @GET("/user/existNickname/{nickname}")
    Call<User>getDataNickname(@Path("nickname")String nickname);

    //회원가입
    @POST("/user/addUser/{uid}/{nickname}/{email}/{password}/{gender}")
    Call<User> postData(@Path("uid") String uid,@Path("nickname") String nickname,@Path("email") String email,@Path("password") String password,@Path("gender")int gender );

    //비밀번호 수정
    @POST("/user/updatePassword/{uid}/{password}")
    Call<User> postUpdataPassword(@Path("uid") String uid,@Path("password") String password);

    //
    @POST("/user/updateUser/{uid}/{nickname}/{email}/{password}/{gender}")
    Call<User> postUpdateUser(@Path("uid") String uid,@Path("nickname") String nickname,@Path("email") String email,@Path("password") String password,@Path("gender")int gender );

    //새로운 list 만들기
    @POST("/list/addList/{city}/{start_date}/{finish_date}/{uid}/{gender}/{theme}/{season}")
    Call<CheckList> postCreateList(@Path("city") String city,
                              @Path("start_date") String start_date,
                              @Path("finish_date") String finish_date,
                              @Path("uid") String uid,
                              @Path("gender") int gender,
                              @Path("theme") String theme,
                              @Path("season") String season);


    //list 수정
    @POST("list/updateSelectedList/{num}/{start_date}/{finish_date}/{gender}/{theme}/{season}")
    Call<CheckList> updateSelectedList(@Path("num") String num,
                                   @Path("start_date") String start_date,
                                   @Path("finish_date") String finish_date,
                                   @Path("gender") int gender,
                                   @Path("theme") String theme,
                                   @Path("season") String season);

    //한 명의 회원의 전체 체크리스트 불러오기
    @GET("/list/readAllList/{uid}")
    Call<List<CheckList>> readAllList(@Path("uid")String uid);

    //해당 체크리스트의 모든아이템들 가져오기
    @GET("/item/readCheckListItems/{list_num}")
    Call<List<checkListItem>> readAllItem(@Path("list_num")int list_num);

    //list에서 item하나 추가
    @POST("/item/addCheckListItem/{list_num}/{name}")
    Call<checkListItem> plusOneItem(@Path("list_num")int list_num, @Path("name")String name);

    @POST("/list/deleteSelectedList/{num}")
    Call<CheckList> deleteList(@Path("num")int num);

    //item 체크한거 저장
    @POST("/item/updateCheckListItem/{list_num}/{check_num}")
    Call<checkListItem> checkOneItem(@Path("list_num")int list_num,@Path("check_num")int check_num);


    @PUT("/posts/1")
    Call<User> putData(@Body User param);


    @DELETE("/posts/1")
    Call<ResponseBody> deleteData();


}


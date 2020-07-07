package kr.hs.emirim.lyn.carrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-54-180-93-190.ap-northeast-2.compute.amazonaws.com:3000")

            //                            .baseUrl("http://192.168.219.142:4000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //FirebaseAuth.getInstance().signOut();
                checkCurrentUser(user);
            }
        }, 1800);
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    public void checkCurrentUser(FirebaseUser user) {
        // [START check_current_user]
        if (user != null) {
            //uid로 로그인하기
            //readUser/:uid로 넘어가기
            String user_uid = user.getUid();
            Log.d("user_uid", user_uid);
            apiService.getData(user_uid).enqueue(new Callback<User>() {//drawer에 닉네임이랑 이메일 뜨게하기 위한 작업
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    User du = response.body();
                    Log.d("sowon drawer modify","됨 ok : "+ du.toString());
                    Log.d("data.getNickname() : ", du.getNickname() + "");
                    Intent intent = new Intent(SplashActivity.this, Main_List.class);
                    intent.putExtra("gender",du.getGender());
                    intent.putExtra("uid",du.getUid());
                    intent.putExtra("num","1");
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onFailure(@NonNull Call<User> call,@NonNull Throwable t) {
                    Log.d("mytag Main", "안됨 fail : " + t.toString());
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);

                    finish();
                }
            });
        } else {
            // No user is signed in
            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
            startActivity(intent);

                finish();
            }

    }
}

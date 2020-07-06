package kr.hs.emirim.lyn.carrying.Login;

import androidx.appcompat.app.AppCompatActivity;

import kr.hs.emirim.lyn.carrying.R;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindPassword extends AppCompatActivity {
//비밀번호 변경 클래스
    static int checkButton_pressed = 0;
    static String email="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        Button back = (Button)findViewById(R.id.backbtn);
        Button Re=(Button)findViewById(R.id.joinbtn);
        Button check=(Button)findViewById(R.id.okNickname);
        EditText pw=(EditText)findViewById(R.id.et_password);
        EditText pwr=(EditText)findViewById(R.id.et_passwordre);
        EditText eemail=(EditText)findViewById(R.id.et_eamil);

        Intent intent=getIntent();
        String userUid=intent.getStringExtra("uid");

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-180-93-190.ap-northeast-2.compute.amazonaws.com:3000")
//                .baseUrl("http://192.168.219.142:4000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);




        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                checkButton_pressed =1;
                email=eemail.getText().toString();
                Call<User> apiCall = apiService.getDataEmail(email);
                apiCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User du = response.body();
                        Log.d("mytag ","됨 ok : "+ du.toString());
                        Log.d("data.getUserId() 닉네임 : ", du.getNickname() + "");
                        Toast.makeText(getApplicationContext(), "확인된 이메일", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("ChangePassword", "안됨 fail : " + t.toString());
                        Toast.makeText(getApplicationContext(), "해당 이메일이 없습니다.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        Re.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      if ((pw.getText().toString()).equals(pwr.getText().toString())) {

                                          Call<User> apiCall = apiService.postUpdataPassword(userUid, pw.getText().toString());

                                          apiCall.enqueue(new Callback<User>() {
                                              @Override
                                              public void onResponse(Call<User> call, Response<User> response) {
                                                  User du = response.body();
//                                                  Log.d("mytag ", "됨 ok : " + du.toString());

                                                  Toast.makeText(getApplicationContext(), "변경되었습니다.", Toast.LENGTH_LONG).show();
                                                  finish();
                                              }

                                              @Override
                                              public void onFailure(Call<User> call, Throwable t) {
                                                  Log.d("ChangePassword", "안됨 fail : " + t.toString());
                                                  Toast.makeText(getApplicationContext(), "변경 실패", Toast.LENGTH_LONG).show();
                                              }


                                          });

                                      }
                                      else{
                                          Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();

                                      }
                                  }


                              });

        ///

    }


}

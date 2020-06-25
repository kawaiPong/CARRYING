package kr.hs.emirim.lyn.carrying.Login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import kr.hs.emirim.lyn.carrying.R;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    final List<String> listGender = new ArrayList<>();

    private FirebaseAuth auth;
    private FirebaseUser user;
    Spinner spinner;
    String[] item;
    String uid;


    int gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        listGender.add("여성");
        listGender.add("남성");
        listGender.add("둘다 선택함");
        listGender.add("둘다 선택하지 않음");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-125-110-97.ap-northeast-2.compute.amazonaws.com:3000")
                .client(okHttpClient)
//                .baseUrl("http://localhost:1234").
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
        
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        item = new String[]{"    여성", "    남성", "    둘 다 선택", "    선택하지 않음"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);


        Button back = (Button) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView login=(TextView)findViewById(R.id.textView1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });



        Button joinbtn = (Button) findViewById(R.id.joinbtn);
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = spinner.getSelectedItem().toString();

                if(text.contains("남성"))gender=1;
                else if(text.contains("여성"))gender=2;
                else if(text.contains("둘 다 선택"))gender=3;
                else if(text.contains("선택하지 않음"))gender=0;


                EditText NickNameE = (EditText) findViewById(R.id.et_name_changeU);
                EditText EmailE = (EditText) findViewById(R.id.et_eamil);
                EditText PasswordE = (EditText) findViewById(R.id.et_password);
                EditText CheckPasswordE = (EditText) findViewById(R.id.et_passwordre);

                String NickName = NickNameE.getText().toString().trim();
                String Email = EmailE.getText().toString().trim();
                String Password = PasswordE.getText().toString().trim();
                String CheckPassword = CheckPasswordE.getText().toString().trim();
                int gender=1;


                if ((NickName.length() == 0) ||
                        (Email.length() == 0)||
                        (CheckPassword.length() < 8))
                         {
                    Toast.makeText(getApplicationContext(), "모든 항목이 채워져있는지 확인해주세요", Toast.LENGTH_LONG).show();

                }
                else if((Password.length() < 8) ){
                    Toast.makeText(getApplicationContext(), "비밀번호는 8자리 이상 입력해주세요", Toast.LENGTH_LONG).show();

            } else {
                    if (Password.equals(CheckPassword)) {

                        Log.d("password equals", "비밀번호 맞음");


                        auth.createUserWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(RegisterActivity.this, task -> {
                                    if (task.isSuccessful()) {
                                        user = auth.getCurrentUser(); //master엔 없음
                                        uid = user.getUid();
                                        Log.d("Uid : ", uid);


                                        Call<User> apiCall = apiService.postData(uid,NickName,Email,Password,gender);
                                        Log.d("sowon register",uid+":"+NickName+":"+Email+":"+Password+":"+gender+":");
                                        apiCall.enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                User du = response.body();
                                                Log.d("회원가입", "성공");
                                            }
                                            @Override
                                            public void onFailure(Call<User> call, Throwable t) {
                                                Log.d("mytag", "안됨 fail : " + t.toString());
                                                Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();

                                            }
                                        });
                                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    }
                                });


                    }
                    else{
                        Toast.makeText(getApplicationContext(), "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }//oncreate

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}

package kr.hs.emirim.lyn.carrying.Login;

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

import com.google.firebase.auth.FirebaseAuth;

import kr.hs.emirim.lyn.carrying.R;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth auth;
    Spinner spinner;
    String[] item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.9.40:1234")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
        Call<User> apiCall = apiService.getData("132");

        apiCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User du = response.body();
                Log.d("mytag 됨", du.toString());
                Log.d("data.getUserId() 닉네임 : ", du.getNickname() + "");

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("mytag", "안됨 fail : " + t.toString());
            }
        });




        auth = FirebaseAuth.getInstance();

        String email = "ghddnjf2901@gmail.com".trim();
        String pw = "dnjs290112sb*".trim();

        auth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(RegisterActivity.this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        item = new String[]{"    남성", "    여성", "    둘 다 선택", "    둘 다 선택하지 않음"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);

//        buttonLister();

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


                EditText NickNameE = (EditText) findViewById(R.id.et_name);
                EditText EmailE = (EditText) findViewById(R.id.et_eamil);
                EditText PasswordE = (EditText) findViewById(R.id.et_password);
                EditText CheckPasswordE = (EditText) findViewById(R.id.et_passwordre);


                String NickName = NickNameE.getText().toString().trim();
                String Email = EmailE.getText().toString().trim();
                String Password = PasswordE.getText().toString().trim();
                String CheckPassword = CheckPasswordE.getText().toString().trim();

                if ((NickName.length() == 0) ||
                        (Email.length() == 0) ||
                        (Password.length() == 0) ||
                        (CheckPassword.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "모든 항목이 채워져있는지 확인해주세요", Toast.LENGTH_LONG).show();

                } else {
                    if (Password.equals(CheckPassword)) {

                        Call<User> apiCall = apiService.postData(NickName,Email,Password,"34554",1);

                        apiCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User du = response.body();
                                Log.d("mytag 됨", du.toString());
                                Log.d("data.getUserId() 닉네임 : ", du.getNickname() + "");

                            }
                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("mytag", "안됨 fail : " + t.toString());
                            }
                        });



                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

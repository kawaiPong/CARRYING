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
import com.google.firebase.auth.FirebaseUser;

import kr.hs.emirim.lyn.carrying.R;
import kr.hs.emirim.lyn.carrying.create_list;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private final static String TAG = "RegisterActivity";

    private FirebaseAuth auth;

    Spinner spinner;
    String[] item;

    EditText et_name;
    EditText et_email;
    EditText et_password;
    EditText et_checkPassword;
    TextView loginBtn;
    Button registerBtn;

    String nickname;
    String email;
    String password;
    String checkPassword;
    int gender;

    String uid;

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

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        item = new String[]{"    남성", "    여성", "    둘 다 선택", "    둘 다 선택하지 않음"};

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

        loginBtn=(TextView)findViewById(R.id.textView1);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        registerBtn = (Button) findViewById(R.id.joinbtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_name = (EditText) findViewById(R.id.et_name);
                et_email = (EditText) findViewById(R.id.et_eamil);
                et_password = (EditText) findViewById(R.id.et_password);
                et_checkPassword = (EditText) findViewById(R.id.et_passwordre);

                nickname = et_name.getText().toString().trim();
                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();
                checkPassword = et_checkPassword.getText().toString().trim();

                Log.d(TAG, nickname + ", " + email + ", " + password + ", " + checkPassword + ", " + spinner.isSelected());

                if ((nickname.length() == 0) ||
                        (email.length() == 0) ||
                        (password.length() == 0) ||
                        (checkPassword.length() == 0) ||
                        !(spinner.isSelected())) {
                    Toast.makeText(getApplicationContext(), "모든 항목이 채워져있는지 확인해주세요", Toast.LENGTH_LONG).show();

                } else {
                    if (password.equals(checkPassword)) {
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegisterActivity.this, task -> {
                                    if (task.isSuccessful()) {
                                        uid = auth.getCurrentUser().getUid();
                                        Log.d(TAG + ": UID", uid);
                                        
                                        Call<User> apiCall = apiService.postData("강주영","2101",1);

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
                                        Intent intent = new Intent(RegisterActivity.this, create_list.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                });
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
        gender = i;
        spinner.setSelected(true);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

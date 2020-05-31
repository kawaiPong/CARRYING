package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import kr.hs.emirim.lyn.carrying.Login.FindPassword;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Change_info extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        Main_List mActivity=(Main_List)Main_List.Main_List;

        Button Change_btn=(Button)findViewById(R.id.Change_btn);
        Button back=(Button)findViewById(R.id.backbtn_change);
        Button NicknameOk_btn=(Button)findViewById(R.id.okNickname);
        Button EmailOk_btn=(Button)findViewById(R.id.okEmail);

        EditText nicknameE=(EditText)findViewById(R.id.et_name_changeU);
        EditText emailE=(EditText)findViewById(R.id.et_email_change);
        EditText passwordE=(EditText)findViewById(R.id.et_password);
        EditText passwordreE=(EditText)findViewById(R.id.et_passwordre);
//
//
//        String nickname=nicknameE.getText().toString();
//        String email=emailE.getText().toString();
       String password=passwordE.getText().toString();
       String passwordre=passwordreE.getText().toString();

        int gender=1;
        final String[] uid = new String[1];
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        item = new String[]{"    남성", "    여성", "    둘 다 선택", "    둘 다 선택하지 않음"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);



        Intent intent=getIntent();
        String userUid=intent.getStringExtra("uid");

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-180-82-41.ap-northeast-2.compute.amazonaws.com:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);



        Call<User> apiCall = apiService.getData(userUid);
        apiCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User du = response.body();
                Log.d("mytag ","됨 ok : "+ du.toString());
                Log.d("mytag uid : ", du.getUid() + "");
                uid[0] =du.getUid();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("mytag Changeuser", "안됨 fail : " + t.toString());
                Toast.makeText(getApplicationContext(), "해당 이메일이 없습니다.", Toast.LENGTH_LONG).show();
            }
        });


        Change_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    if ((password).equals(passwordre)) {
                        Call<User> apiCall2 = apiService.postUpdateUser(uid[0], nicknameE.getText().toString(), emailE.getText().toString(), passwordE.getText().toString(), gender);
                        apiCall2.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User du = response.body();
                                uid[0] = du.getUid();
                                Toast.makeText(getApplicationContext(), "회원정보가 수정되었습니다.", Toast.LENGTH_LONG).show();
                                mActivity.finish();
                                Intent intent=new Intent(Change_info.this, Main_List.class);
                                intent.putExtra("email",emailE.getText().toString());
                                intent.putExtra("num","1");
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("mytag ChangeUser", "안됨 fail : " + t.toString());
                                Toast.makeText(getApplicationContext(), "회원정보 수정 실패", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();

                    }




            }
        });


        NicknameOk_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Call<User> apiCall = apiService.getDataNickname(nicknameE.getText().toString());
                apiCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User du = response.body();
                        //Log.d("mytag ChangeUser", "됨  : " + du.toString());
                        Toast.makeText(getApplicationContext(), "사용중인 닉네임 입니다.", Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "닉네임 사용 가능", Toast.LENGTH_LONG).show();
                        //Log.d("mytag ChangeUser", "안됨 fail : " + t.toString());
                    }
                });
            }
        });

        EmailOk_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Call<User> apiCall = apiService.getDataEmail(emailE.getText().toString());
                apiCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User du = response.body();
                        //Log.d("mytag ChangeUser", "됨  : " + du.getEmail());
                        Toast.makeText(getApplicationContext(), "이미 사용중인 이메일입니다.", Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        //Log.d("mytag ChangeUser", "안됨 fail : " + t.toString());
                        Toast.makeText(getApplicationContext(), "이메일 사용 가능", Toast.LENGTH_LONG).show();
                    }
                });
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

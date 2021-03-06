package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class deletePopUp extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pop_up);

        intent=getIntent();

        int title=intent.getExtras().getInt("title");
        int gender=intent.getExtras().getInt("gender");
        String userUid=intent.getExtras().getString("uid");

        Log.d("deletePopup sowon",title+":"+gender+":"+userUid);

        Button cross=(Button)findViewById(R.id.cross);
        Button delete = (Button) findViewById(R.id.delete_popup);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Context context = v.getContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-54-180-93-190.ap-northeast-2.compute.amazonaws.com:3000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
                Call<CheckList> apiCall = apiService.deleteList(title);
                apiCall.enqueue(new Callback<CheckList>() {
                    @Override
                    public void onResponse(Call<CheckList> call, Response<CheckList> response) {
                        Log.d("sowon","실패");
                        finish();


                    }

                    @Override
                    public void onFailure(Call<CheckList> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "리스트 삭제 실패.", Toast.LENGTH_LONG).show();
                        finish();
                    }

                });


            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
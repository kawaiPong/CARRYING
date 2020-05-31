package kr.hs.emirim.lyn.carrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import kr.hs.emirim.lyn.carrying.Login.FindPassword;
import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import kr.hs.emirim.lyn.carrying.Retrofit.checkListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class check_list extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);


        intent=getIntent();
        String user_uid=intent.getExtras().getString("userUid");

        Log.d("mytage check_list uid",user_uid+"");

        Button back=(Button)findViewById(R.id.back_checkList);
        Button plus_item=(Button)findViewById(R.id.plus_item_btn);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-180-82-41.ap-northeast-2.compute.amazonaws.com:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
        apiService.readAllItem(18).enqueue(new Callback<List<checkListItem>>() { //uid사용자의 전체 리스트를 불러오기 위한 작업
            //근데 CheckList에 있는 값을 다 반환하는지는 모르겠음
            @Override
            public void onResponse(@NonNull Call<List<checkListItem>> call, @NonNull Response<List<checkListItem>> response) {
                Log.d("mytag","성공");

                List<checkListItem> du = response.body();

                if (du != null) {
                    checkList_item[] data = new checkList_item[du.size()];//자동으로 해줌
                    for (int i = 0; i < du.size(); i++) {
                        data[i] = new checkList_item(du.get(i).getName());
                        Log.d("mytag",""+du.get(i).getName()+":"+i+"번째");
//                        //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
//                        mArrayList.add(data[i]); // RecyclerView의 마지막 줄에 삽입
                    }
                    Log.e("getData2 end", "======================================");
//                    mAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<checkListItem>> call, Throwable t) {

            }


        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        plus_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(check_list.this, plusItemPopUp.class);
                startActivity(intent);

            }
        });


    }

}

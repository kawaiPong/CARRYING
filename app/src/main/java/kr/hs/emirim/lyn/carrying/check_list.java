package kr.hs.emirim.lyn.carrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    private CustomAdapterItem mAdapter;

    private ArrayList<checkListItem> mArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);


        intent=getIntent();
        String title=intent.getExtras().getString("title");
        String user_uid=intent.getExtras().getString("userUid");
        int listNum=intent.getExtras().getInt("listNum");
        String seasonStr=intent.getExtras().getString("season");
        String themeStr=intent.getExtras().getString("theme");

        int theme=Integer.parseInt(themeStr);
        int season  = Integer.parseInt(seasonStr);

        Log.d("mytag check_list intent",user_uid+":"+listNum+":"+season+":"+theme);

        Button back=(Button)findViewById(R.id.back_checkList);
        Button plus_item=(Button)findViewById(R.id.plus_item_btn);
        Button theme_1=(Button)findViewById(R.id.theme1);
        Button theme_2=(Button)findViewById(R.id.theme2);
//        theme_1.setVisibility(View.INVISIBLE);
        theme_1.setVisibility(View.VISIBLE);
        theme_2.setVisibility(View.VISIBLE);


        if(theme==10&&season==10)theme_2.setVisibility(View.INVISIBLE);
        switch(season) {
            case 10:
                theme_2.setText("# 기본계절");
                break;
            case 5:
                theme_2.setText("# 봄/가을");
                break;

            case 6:
                theme_2.setText("# 여름");
                break;

            case 7:
                theme_2.setText("# 겨울");
                break;

            default:
                theme_2.setText("# 기본");
                break;
        }

        switch(theme) {
            case 10:
                theme_1.setText("# 기본테마");
                break;
            case 0:
                theme_1.setText("# 온천");
                break;

            case 1:
                theme_1.setText("# 등산");
                break;

            case 2:
                theme_1.setText("# 테마파크");
                break;

            case 3:
                theme_1.setText("# 문화체험");
                break;

            case 4:
                theme_1.setText("# 출장");
                break;

            default:
                theme_1.setText("# 기본");
                break;
        }



        TextView title_activity=(TextView)findViewById(R.id.listTitle);

        title_activity.setText(title);

        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.checkBoxList);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();
        mAdapter = new CustomAdapterItem(mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-180-82-41.ap-northeast-2.compute.amazonaws.com:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
        apiService.readAllItem(listNum).enqueue(new Callback<List<checkListItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<checkListItem>> call, @NonNull Response<List<checkListItem>> response) {
                Log.d("mytag CL","성공");

                List<checkListItem> du = response.body();

                if (du != null) {
                    checkListItem[] data = new checkListItem[du.size()];//자동으로 해줌
                    for (int i = 0; i < du.size(); i++) {
                        data[i] = new checkListItem(du.get(i).getCheck_num(),du.get(i).getName(),du.get(i).getStatus(),du.get(i).getList_num());
//                        Log.d("mytag",""+du.get(i).getName()+":"+i+"번째");
//                        Log.d("mytag",""+data[i].toString());
                        mArrayList.add(data[i]); // RecyclerView의 마지막 줄에 삽입
                    }
                    Log.e("getData2 end", "======================================");
                    mAdapter.notifyDataSetChanged();

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
                intent.putExtra("listNum",listNum);
                startActivity(intent);

            }
        });


    }

}

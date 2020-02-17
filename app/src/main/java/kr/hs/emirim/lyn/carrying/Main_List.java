package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Main_List extends AppCompatActivity {

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__list);
        Button plus_btn = (Button)findViewById(R.id.plus);
        Button hamburger=(Button)findViewById(R.id.hamburger);
        intent=getIntent();
        String num=intent.getStringExtra("num");

        if(!(num.equals("1"))){
            String City=intent.getStringExtra("city");
            String start_date=intent.getStringExtra("start_date");
            String finish_date=intent.getStringExtra("finish_date");

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);


            mArrayList = new ArrayList<>();
            mAdapter = new CustomAdapter( mArrayList);
            mRecyclerView.setAdapter(mAdapter);


            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                    mLinearLayoutManager.getOrientation());
            mRecyclerView.addItemDecoration(dividerItemDecoration);



            Dictionary data = new Dictionary(City,start_date, finish_date);

            //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
            mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

            mAdapter.notifyDataSetChanged();
        }


        plus_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "create List", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Main_List.this, create_list.class);
                startActivity(intent);
            }
        });

        hamburger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "햄버거 액티비티 아직 안만듬", Toast.LENGTH_LONG).show();
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
//                drawer.openDrawer(Gravity.LEFT);
            }
        });

    }
    }

//package kr.hs.emirim.lyn.carrying;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import kr.hs.emirim.lyn.carrying.Login.SignInActivity;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.google.android.material.navigation.NavigationView;
//
//import java.util.ArrayList;
//
////public class Main_List extends AppCompatActivity {
//////    DrawerLayout drawerLayout;
////    private ArrayList<Dictionary> mArrayList;
////    private CustomAdapter mAdapter;
////    private int count = -1;
////    Intent intent;
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main__list);
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
////        Button hamburger=(Button)findViewById(R.id.hamburger);
////        Button plus_btn=(Button)findViewById(R.id.plus);
////        intent=getIntent();
////        String num=intent.getStringExtra("num");
////
////        if(num.equals("2")){
////            Log.d("들어옴","들어옴");
////            String City=intent.getStringExtra("city");
////            String start_date=intent.getStringExtra("start_date");
////            String finish_date=intent.getStringExtra("finish_date");
////            Log.d("들어옴1","들어옴1"+start_date);
////            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
////            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
////             mRecyclerView.setLayoutManager(mLinearLayoutManager);
////            Log.d("들어옴2","들어옴2");
////
////
////            mArrayList = new ArrayList<>();
////            mAdapter = new CustomAdapter( mArrayList);
////             mRecyclerView.setAdapter(mAdapter);
////
////            Log.d("들어옴3","들어옴3");
////            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
////                    mLinearLayoutManager.getOrientation());
////            mRecyclerView.addItemDecoration(dividerItemDecoration);
////
////            Log.d("들어옴","들어옴");
////
////            Dictionary data = new Dictionary(City,start_date, finish_date);
////            //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
////            mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
////            mAdapter.notifyDataSetChanged();
////            Log.d("들어옴","들어옴");
////        }
////
////
////        hamburger.setOnClickListener(new View.OnClickListener(){
////            @Override
////            public void onClick(View v) {
//////                Toast.makeText(getApplicationContext(), "햄버거 액티비티 아직 안만듬", Toast.LENGTH_LONG).show();
////                drawer.openDrawer(GravityCompat.START) ;
////
////            }
////        });
////
////        plus_btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(Main_List.this, create_list.class);
////                startActivity(intent);
////            }
////        });
////
//////        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
//////        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//////            @Override
//////            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//////                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//////                switch(menuItem.getItemId()) {
//////                    case R.id.test1:
//////// replace the FrameLayout with new Fragment
//////                        fragmentTransaction.replace(R.id.fragment_container, new TestFragment());
//////                        fragmentTransaction.commit(); // save the changes
//////                        break;
//////                    case R.id.test2:
//////// replace the FrameLayout with new Fragment
//////                        fragmentTransaction.replace(R.id.fragment_container, new TestFragment2());
//////                        fragmentTransaction.commit(); // save the changes
//////                        break;
//////                }
//////                drawer.closeDrawer(Gravity.LEFT);
//////
//////                return true;
//////            }
//////        });
////
////
////// create a FragmentTransaction to begin the transaction and replace the Fragment
//////        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//////// replace the FrameLayout with new Fragment
//////        fragmentTransaction.replace(R.id.fragment_container, new TestFragment());
//////        fragmentTransaction.commit(); // save the changes
////
////
////    }
////}
//
//
//public class Main_List extends AppCompatActivity {
//    //    DrawerLayout drawerLayout;
//    private ArrayList<Dictionary> mArrayList;
//    private CustomAdapter mAdapter;
//    private int count = -1;
//    Intent intent;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main__list);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
//        Button hamburger=(Button)findViewById(R.id.hamburger);
//        Button plus_btn=(Button)findViewById(R.id.plus);
//        intent=getIntent();
//        String num=intent.getStringExtra("num");
//
//        if(num.equals("2")){
//            String City=intent.getStringExtra("city");
//            String start_date=intent.getStringExtra("start_date");
//            String finish_date=intent.getStringExtra("finish_date");
//
//             RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
//            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(mLinearLayoutManager);
//
//
//            mArrayList = new ArrayList<>();
//            mAdapter = new CustomAdapter( mArrayList);
//             mRecyclerView.setAdapter(mAdapter);
//
//
//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                    mLinearLayoutManager.getOrientation());
//            mRecyclerView.addItemDecoration(dividerItemDecoration);
//
//
//
//            Dictionary data = new Dictionary(City,start_date, finish_date);
//
//            //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
//            mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
//
//            mAdapter.notifyDataSetChanged();
//        }
//
//
//        hamburger.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(), "햄버거 액티비티 아직 안만듬", Toast.LENGTH_LONG).show();
//
//                drawer.openDrawer(GravityCompat.START) ;
//
//            }
//        });
//        plus_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Main_List.this, create_list.class);
//
//            }
//        });
//
//
////
////        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
////        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
////            @Override
////            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
////                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                switch(menuItem.getItemId()) {
////                    case R.id.test1:
////// replace the FrameLayout with new Fragment
////                        fragmentTransaction.replace(R.id.fragment_container, new TestFragment());
////                        fragmentTransaction.commit(); // save the changes
////                        break;
////                    case R.id.test2:
////// replace the FrameLayout with new Fragment
////                        fragmentTransaction.replace(R.id.fragment_container, new TestFragment2());
////                        fragmentTransaction.commit(); // save the changes
////                        break;
////                }
////                drawer.closeDrawer(Gravity.LEFT);
////
////                return true;
////            }
////        });
////
////
////// create a FragmentTransaction to begin the transaction and replace the Fragment
////        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////// replace the FrameLayout with new Fragment
////        fragmentTransaction.replace(R.id.fragment_container, new TestFragment());
////        fragmentTransaction.commit(); // save the changes
//
//
//    }
//}


package kr.hs.emirim.lyn.carrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        Button hamburger=(Button)findViewById(R.id.hamburger);
        Button plus_btn=(Button)findViewById(R.id.plus);
        intent=getIntent();
        String num=intent.getStringExtra("num");

        if(num.equals("2")){
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


        hamburger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "햄버거 액티비티 아직 안만듬", Toast.LENGTH_LONG).show();

                drawer.openDrawer(GravityCompat.START) ;

            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_List.this, create_list.class);
                startActivity(intent);
            }
        });



        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                switch(menuItem.getItemId()) {
//                    case R.id.test1:
//// replace the FrameLayout with new Fragment
//                        fragmentTransaction.replace(R.id.fragment_container, new TestFragment());
//                        fragmentTransaction.commit(); // save the changes
//                        break;
//                    case R.id.test2:
//// replace the FrameLayout with new Fragment
//                        fragmentTransaction.replace(R.id.fragment_container, new TestFragment2());
//                        fragmentTransaction.commit(); // save the changes
//                        break;
//                }
//                drawer.closeDrawer(Gravity.LEFT);
//
//                return true;
//            }
//        });


// create a FragmentTransaction to begin the transaction and replace the Fragment
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//// replace the FrameLayout with new Fragment
//        fragmentTransaction.replace(R.id.fragment_container, new TestFragment());
//        fragmentTransaction.commit(); // save the changes


    }
}
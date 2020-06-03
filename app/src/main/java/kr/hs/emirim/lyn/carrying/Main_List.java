package kr.hs.emirim.lyn.carrying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.hs.emirim.lyn.carrying.Login.FindPassword;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;
import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

//import com.google.android.material.navigation.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Main_List extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseUser user;

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;
    Intent intent;

    public static Activity Main_List;

    //날씨 api
    private static final String TAG = "api";
    public static final int LOAD_SUCCESS = 101;

    private String SEARCH_URL="https://api.openweathermap.org/data/2.5/weather?q=";
    private String API_KEY="&appid=ea055b19951a1369222227e310411249";
    private String City="Seoul";
    private String Country="kr";
    private String REQUEST_URL = SEARCH_URL+City+","+Country+API_KEY;

    private ProgressDialog progressDialog = null;
    private SimpleAdapter adapter = null;
    private List<HashMap<String,String>> photoinfoList = null;
    private EditText searchKeyword = null;
    static String current_temp=new String();
    static String current_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__list);

        Main_List=Main_List.this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
        Button hamburger=(Button)findViewById(R.id.hamburger);
        Button plus_btn=(Button)findViewById(R.id.plus);
        Button logout_btn=(Button)findViewById(R.id.logOut);
        Button editInfo_btn=(Button)findViewById(R.id.EditInfo);
        Button pwChange=(Button)findViewById(R.id.PasswordChange);
        Button compose=(Button)findViewById(R.id.deleteBtn);
        TextView userName=(TextView)findViewById(R.id.userName);
        TextView userEmail=(TextView)findViewById(R.id.userEmail);
        TextView today_date=(TextView)findViewById(R.id.today_date);


        TextView now_temp=(TextView)findViewById(R.id.now_temp);
        ImageView now_des_sunny=(ImageView)findViewById(R.id.description_sunny);
        ImageView now_des_cloudy=(ImageView)findViewById(R.id.description_cloudy);
        ImageView now_des_rainy=(ImageView)findViewById(R.id.description_rainy);
        ImageView now_des_snowy=(ImageView)findViewById(R.id.description_snowy);

        now_des_sunny.setVisibility(View.INVISIBLE);
        now_des_cloudy.setVisibility(View.INVISIBLE);
        now_des_rainy.setVisibility(View.INVISIBLE);
        now_des_snowy.setVisibility(View.INVISIBLE);

        ImageView Cloth_padding=(ImageView)findViewById(R.id.cloth_padding);
        ImageView Cloth_sweatShirts=(ImageView)findViewById(R.id.cloth_mantoman);
        ImageView Cloth_cardigan=(ImageView)findViewById(R.id.cloth_cardigan);
        ImageView Cloth_ts=(ImageView)findViewById(R.id.cloth_tshirt);
        ImageView Cloth_ns=(ImageView)findViewById(R.id.cloth_nasi);
        ImageView Cloth_zp=(ImageView)findViewById(R.id.cloth_zipup);
        ImageView Cloth_coat=(ImageView)findViewById(R.id.cloth_coat);

        Cloth_padding.setVisibility(View.INVISIBLE);
        Cloth_sweatShirts.setVisibility(View.INVISIBLE);
        Cloth_cardigan.setVisibility(View.INVISIBLE);
        Cloth_ts.setVisibility(View.INVISIBLE);
        Cloth_ns.setVisibility(View.INVISIBLE);
        Cloth_zp.setVisibility(View.INVISIBLE);
        Cloth_coat.setVisibility(View.INVISIBLE);


        TextView recom_weather=(TextView)findViewById(R.id.recom_weather);


        intent=getIntent();
        String user_uid=intent.getExtras().getString("uid");
        int userGender = intent.getExtras().getInt("gender");

        Log.d("sowon mytag Main_List","됨 ok : "+user_uid+":"+userGender + ":" + intent.getExtras().getInt("num"));
        String num=intent.getExtras().getString("num");
//        Log.d("sowon Main_List","됨 ok : "+num+"과"+user_uid);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-54-180-82-41.ap-northeast-2.compute.amazonaws.com:3000")
//                .baseUrl("http://192.168.219.142:4000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
//        Call<User> apiCall = apiService.getDataEmail(user_uid);
        apiService.getData(user_uid).enqueue(new Callback<User>() {//drawer에 닉네임이랑 이메일 뜨게하기 위한 작업
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                User du = response.body();
                Log.d("sowon drawer modify","됨 ok : "+ du.toString());
                Log.d("data.getNickname() : ", du.getNickname() + "");

                userEmail.setText(du.getEmail());
                userName.setText(du.getNickname());

            }
            @Override
            public void onFailure(@NonNull Call<User> call,@NonNull Throwable t) {
                Log.d("mytag Main", "안됨 fail : " + t.toString());
            }
        });


        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        mArrayList = new ArrayList<>();
        mAdapter = new CustomAdapter(mArrayList,user_uid,userGender);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);



        Log.d("mytag","레트로핏 전");
        Call<List<CheckList>> apiCallList = apiService.readAllList(user_uid);
        apiService.readAllList(user_uid).enqueue(new Callback<List<CheckList>>() { //uid사용자의 전체 리스트를 불러오기 위한 작업
            //근데 CheckList에 있는 값을 다 반환하는지는 모르겠음
            @Override
            public void onResponse(@NonNull Call<List<CheckList>> call, @NonNull Response<List<CheckList>> response) {
                Log.d("mytag","성공");

                List<CheckList> du = response.body();
                Log.d("mytag","성공 : "+du.toString());

                if (du != null) {
                    Dictionary[] data = new Dictionary[du.size()];//자동으로 해줌
                    for (int i = 0; i < du.size(); i++) {
                        data[i] = new Dictionary(du.get(i).getNum(),du.get(i).getTitle(),du.get(i).getStart_date(), du.get(i).getFinish_date());
                        Log.d("mytag",""+du.get(i).getNum() + du.get(i).getTitle()+du.get(i).getStart_date()+du.get(i).getFinish_date());
                        //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                        mArrayList.add(data[i]); // RecyclerView의 마지막 줄에 삽입
                    }
                    Log.e("getData2 end", "======================================");
                    mAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CheckList>> call,@NonNull Throwable t) {

            }

        });



        /*if(num.equals("2")){//
            //서버 연결하기 전 임시로 intent 한거라 수정해야함
            //intent 필요없이 uid로 @GET 해서 리스트 가져와야함
            String City=intent.getStringExtra("city");
            String start_date=intent.getStringExtra("start_date");
            String finish_date=intent.getStringExtra("finish_date");
            String userEmail2=intent.getStringExtra("userEmail");
//            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
//            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(mLinearLayoutManager);
//
//
//            mArrayList = new ArrayList<>();
//            mAdapter = new CustomAdapter( mArrayList);
//            mRecyclerView.setAdapter(mAdapter);
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
        }
*/

        getJSON();//api

        Log.d("sowon","getJSON()함수 끝");



        hamburger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get (cal.YEAR);
                int month = cal.get (cal.MONTH) + 1 ;
                int date = cal.get (cal.DATE) ;

                today_date.setText(year+"."+month+"."+date);

                now_temp.setText(current_temp);
                if(current_description.equals("clear sky"))now_des_sunny.setVisibility(View.VISIBLE);
                else if(current_description.contains("cloud"))now_des_cloudy.setVisibility(View.VISIBLE);
                else if(current_description.contains("rain")||current_description.contains("mist"))now_des_rainy.setVisibility(View.VISIBLE);
                else if(current_description.contains("snow"))now_des_snowy.setVisibility(View.VISIBLE);
                int temp=Integer.parseInt(current_temp);


                if(temp<=4){
                    recom_weather.setText("패딩, 목도리 등 두꺼운 겨울 옷을 준비하면 좋아요");
                    Cloth_padding.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }
                else if(temp>=5&&temp<=11){
                    recom_weather.setText("코트, 니트 등 따듯한 옷을 준비하면 좋아요");
                    Cloth_coat.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }
                else if(temp>=12&&temp<=16){
                    recom_weather.setText("후드집업, 가디건 등 걸칠 수 있는 옷을 준비하면 좋아요");
                    Cloth_zp.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }
                else if(temp>=17&&temp<=19){
                    recom_weather.setText("얇은 니트, 얇은 가디건 등 가볍게 걸칠 수 있는 옷을 준비하면 좋아요");
                    Cloth_cardigan.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }
                else if(temp>=20&&temp<=22){
                    recom_weather.setText("긴팔, 얇은 가디건 등 가벼운 옷을 준비하면 좋아요");
                    Cloth_sweatShirts.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }
                else if(temp>=23&&temp<=27){
                    recom_weather.setText("반팔이나 얇은 셔츠 등 얇은 옷을 준비하면 좋아요");
                    Cloth_ts.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }
                else if(temp>=28){
                    recom_weather.setText("민소매, 반팔 등 더울 수 있으니 얇은 옷을 준비하면 좋아요");
                    Cloth_ns.setVisibility(View.VISIBLE);
                    Log.d("sowon","visible");
                }

                drawer.openDrawer(GravityCompat.START) ;

            }
        });

        pwChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_List.this, FindPassword.class);
                intent.putExtra("uid",user_uid);
                finish();
                startActivity(intent);
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_List.this, create_list.class);
                intent.putExtra("gender",userGender);
                intent.putExtra("uid",user_uid);
                startActivity(intent);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();

                auth.signOut();
                Log.d(TAG, "로그아웃 버튼");
                Intent intent = new Intent(Main_List.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        editInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_List.this,Change_info.class);
                intent.putExtra("uid",user_uid);
                startActivity(intent);
            }
        });





    }//oncreate




    public void getJSON(){
        Log.d("sowon","getJSON()함수 시작");

        Log.d(TAG, "getJSON");

        Thread thread = new Thread(new Runnable() {

            public void run() {

                String result;

                try {

                    Log.d(TAG, REQUEST_URL);
                    URL url = new URL(REQUEST_URL);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();


                    httpsURLConnection.setReadTimeout(3000);
                    httpsURLConnection.setConnectTimeout(3000);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.setUseCaches(false);
                    httpsURLConnection.connect();


                    int responseStatusCode = httpsURLConnection.getResponseCode();

                    InputStream inputStream;
                    if (responseStatusCode == HttpsURLConnection.HTTP_OK) {

                        inputStream = httpsURLConnection.getInputStream();
                    } else {
                        inputStream = httpsURLConnection.getErrorStream();
                    }


                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line;


                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();
                    httpsURLConnection.disconnect();

                    result = sb.toString().trim();
                    // Log.d(TAG,result);

                } catch (Exception e) {
                    result = e.toString();
                }



                jsonParser(result);


            }

        });
        thread.start();
    }






    public boolean jsonParser(String jsonString) {

        Log.d("sowon","parser 함수 시작");


        Log.d(TAG, "jsonParser");
        if (jsonString == null) return false;

        try {
            Log.d(TAG, "안뇽");
            JSONObject jsonObject = new JSONObject(jsonString);
            Log.d(TAG, "안뇽안뇽");
            JSONArray list =jsonObject.getJSONArray("weather");
            JSONObject weather=list.getJSONObject(0);
            String des=weather.getString("description");
            JSONObject temp= jsonObject.getJSONObject("main");
            Double notemp=temp.getDouble("temp");
            notemp-=273.15;
            int int_now_temp=notemp.intValue();
            current_temp=String.valueOf(int_now_temp);
            current_description=des;
            Log.d(TAG,current_description);//날씨 설명 clear sky같은거
            Log.d(TAG,current_temp);//현재 온도

            Log.d(TAG, "안뇽안뇽안뇽");


            return true;
        } catch (JSONException e) {

            Log.d(TAG, e.toString() );

        }



        return false;

    }




}
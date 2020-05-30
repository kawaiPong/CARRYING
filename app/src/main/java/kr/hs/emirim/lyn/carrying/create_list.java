package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;
import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class create_list extends AppCompatActivity implements View.OnClickListener {

    Button start_date;
    Button finish_date;
    public int sy=0, sm=0, sd=0;//출발날짜
    public int fy=0, fm=0,fd=0;//도착날짜
    public int Today_year,Today_month,Today_date;

    Calendar cal=Calendar.getInstance();

    final Context context = this;
    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private ImageButton btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        Intent intent=getIntent();
        String userUid=intent.getStringExtra("uid");//서버와 접촉할때 사용
        int userGender = intent.getExtras().getInt("gender");
        Log.d("mytag create_list","됨 ok : "+userUid+":"+userGender);

        EditText City=(EditText) findViewById(R.id.city);;

        Today_year=cal.get(Calendar.YEAR);
        Today_month=cal.get(Calendar.MONTH);
        Today_date=cal.get((Calendar.DAY_OF_MONTH)+1);

        start_date=findViewById(R.id.start_date);
        finish_date=findViewById(R.id.finish_date);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

////        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.addHash);
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//
//        mArrayList = new ArrayList<>();
//        mAdapter = new CustomAdapter( mArrayList);
//        mRecyclerView.setAdapter(mAdapter);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                mLinearLayoutManager.getOrientation());
//        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Button add_btn=(Button)findViewById(R.id.add);
        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_list.this, Main_List.class);
                if((City.getText().toString().length()==0)||sd==0||fd==0){
                    Toast.makeText(getApplicationContext(), "빈칸이 있습니다.", Toast.LENGTH_LONG).show();
                    Log.d("mytag ","빈칸확인");
                }
                else{//여기가 ㄹㅇ중요함

                    Log.d("mytag ","레트로핏시작전");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://ec2-54-180-82-41.ap-northeast-2.compute.amazonaws.com:3000")
//                .baseUrl("http://192.168.219.142:4000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
                    Log.d("mytag ","요청 전");

                    Call<CheckList> apiCall = apiService.postCreateList(
                            City.getText().toString()+"",//city
                            sy+"-"+sm+"-"+sd,
                            fy+"-"+fm+"-"+fd,
                            userUid+"",
                            userGender,
                            "1",
                            "1"
                    );

                    Log.d("mytag 이렇게 들어감",City.getText().toString()+""+
                            sy+"-"+sm+"-"+sd+
                            fy+"-"+fm+"-"+fd+
                            userUid+""+
                            "0");
                    Log.d("mytag ","요청 후"+apiCall.toString());

                    apiCall.enqueue(new Callback<CheckList>() {
                        @Override
                        public void onResponse(Call<CheckList> call, Response<CheckList> response) {
                            CheckList du = response.body();
                            Log.d("mytag ","성공");
                            Toast.makeText(getApplicationContext(), "확인된 이메일", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<CheckList> call, Throwable t) {
                            Log.d("mytag", "안됨 fail : " + t.toString());
                            Toast.makeText(getApplicationContext(), "해당 이메일이 없습니다.", Toast.LENGTH_LONG).show();
                        }
                    });


//                    intent.putExtra("email",userEmail);
//                    intent.putExtra("num","2");
//                    intent.putExtra("city",City.getText().toString());
////                    intent.putExtra("city","오사카");
//                    intent.putExtra("start_date",sy+"-"+sm+"-"+sd);
//                    intent.putExtra("finish_date",fy+"-"+fm+"-"+fd);
                    intent.putExtra("uid",userUid);
                    startActivity(intent);


                }
            }
        });

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate1();

            }
        });

        finish_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate2();
            }
        });

        btnAlert = (ImageButton) findViewById(R.id.btn_alert);
        // 클릭 이벤트
        btnAlert.setOnClickListener(this);

    } //onCreate()

    public void onClick(View v) {
        show();
    }

    void show(){

        final List<String> ListItems = new ArrayList<>();
            ListItems.add("온천");
            ListItems.add("등산");
            ListItems.add("테마파크");
            ListItems.add("문화체험");
            ListItems.add("봄, 가을");
            ListItems.add("여름");
            ListItems.add("겨울");

        final CharSequence[] items =  ListItems.toArray(new String[ListItems.size()]);
        final List SelectedItems  = new ArrayList();

        final String[] hashimg = { "theme02", "theme03", "theme04", "theme05", "theme06", "theme07", "theme08" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogTheme);
        builder.setTitle("추가할 여행 테마 추가");
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    //사용자가 체크한 경우 리스트에 추가
                    SelectedItems.add(which);
                } else if (SelectedItems.contains(which)) {
                    //이미 리스트에 들어있던 아이템이면 제거
                    SelectedItems.remove(Integer.valueOf(which));
                }
            }
        });

        ImageView image = new ImageView(this);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //String msg="";
                //선택한 항목 갯수만큼 선택한 항목 배열 돌리기
                for (int i = 0; i < SelectedItems.size(); i++) {
                    //index에 0번방부터 선택한 항목 집어넣기
                    int index = (int) SelectedItems.get(i);
                    //msg = msg+"\t"+ListItems.get(index);
                    //해시태그 이미지를 꺼내옴
                    int resId = getResources().getIdentifier(hashimg[i], "drawable",
                            "kr.hs.emirim.lyn.carrying");

                    image.setImageResource(resId);

//                    Dictionary data = new Dictionary(image);
//
//                    //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
//                    mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

                }
                //Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    void showDate1() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sy = year;
                sm = month+1;
                sd = dayOfMonth;
                Log.d("Hello",sy+"+"+sm+"+"+sd);
                start_date.setText("   "+sy+"년 "+sm+"월 "+ sd + "일");
            }
        },Today_year, Today_month, Today_date);

        datePickerDialog.setMessage("출발일");
        datePickerDialog.show();
    }

    void showDate2() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fy = year;
                fm = month+1;
                fd = dayOfMonth;
                Log.d("Hello",fy+"+"+fm+"+"+fd);
                finish_date.setText("   "+fy+"년 "+fm+"월 "+ fd + "일");
            }
        },Today_year, Today_month, Today_date);

        datePickerDialog.setMessage("도착일");
        datePickerDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

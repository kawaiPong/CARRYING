package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
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
    final List<String> ListItemsSeason = new ArrayList<>();
    final List<String> ListItemsTheme = new ArrayList<>();

    String theme01="10";
    String theme02="10";

    Button themeBtn;
    Button start_date;
    Button finish_date;
    Button themebtn01;
    Button themebtn02;
    public int sy=0, sm=0, sd=0;//출발날짜
    public int fy=0, fm=0,fd=0;//도착날짜
    public int Today_year,Today_month,Today_date;

    Calendar cal=Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        themeBtn=(Button)findViewById(R.id.themeBtn);
        themebtn01=(Button)findViewById(R.id.theme1);
        themebtn02=(Button)findViewById(R.id.theme2);


        ListItemsTheme.add("온천");
        ListItemsTheme.add("등산");
        ListItemsTheme.add("테마파크");
        ListItemsTheme.add("문화체험");
        ListItemsTheme.add("출장");
        ListItemsSeason.add("봄, 가을");
        ListItemsSeason.add("여름");
        ListItemsSeason.add("겨울");

        Intent intent=getIntent();
        String userUid=intent.getStringExtra("uid");//서버와 접촉할때 사용

        int userGender = intent.getExtras().getInt("gender");
        Log.d("mytag create_list","됨 ok : "+userUid+":"+userGender);

        EditText City=(EditText) findViewById(R.id.city);;

        Calendar cal = Calendar.getInstance();

        Today_year=cal.get(cal.YEAR);
        Today_month=cal.get(cal.MONTH) ;
        Today_date=cal.get(cal.DATE) ;

        Log.d("mytag 현재 날짜",Today_year+":"+Today_month+":"+Today_date);
        start_date=findViewById(R.id.start_date);
        finish_date=findViewById(R.id.finish_date);

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button add_btn=(Button)findViewById(R.id.add);
        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(create_list.this, Main_List.class);
                if((City.getText().toString().length()==0)||sd==0||fd==0){
                    Toast.makeText(getApplicationContext(), "빈칸이 있습니다.", Toast.LENGTH_LONG).show();
                    Log.d("mytag ","빈칸확인");
                }
                else{
                    Log.d("mytag ","레트로핏시작전");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://ec2-13-125-110-97.ap-northeast-2.compute.amazonaws.com:3000")
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
                            theme01+"",
                            theme02+""
                    );

                    Log.d("mytag 이렇게 들어감",City.getText().toString()+""+
                            sy+"-"+sm+"-"+sd+
                            fy+"-"+fm+"-"+fd+
                            userUid+":"+theme01+":"+theme02);
                    Log.d("mytag ","요청 후"+apiCall.toString());

                    apiCall.enqueue(new Callback<CheckList>() {
                        @Override
                        public void onResponse(Call<CheckList> call, Response<CheckList> response) {
                            CheckList du = response.body();
                            Log.d("mytag ","성공");
                            Toast.makeText(getApplicationContext(), "회원가입 되었습니다.", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<CheckList> call, Throwable t) {
                            Log.d("mytag", "안됨 fail : " + t.toString());
                            Toast.makeText(getApplicationContext(), "회원가입이 실패했습니다.", Toast.LENGTH_LONG).show();
                        }
                    });

                    intent.putExtra("uid",userUid);
                    finish();
                    startActivity(intent);

                }//else
            }
        });
//        themeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show1();
//            }
//        });

        themebtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show1();
            }
        });

        themebtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
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




    } //onCreate()

    public void onClick(View v) {
        show1();


    }

    void show1(){
        final CharSequence[] items =  ListItemsTheme.toArray(new String[ListItemsTheme.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogTheme);
        builder.setTitle("여행 테마 선택");
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
//                    SelectedItems.add(which);
                    theme01=which+"";
                    // 0: 온천
                    // 1: 등산
                    // 3: 테마파크
                    // 4: 문화체험
                    // 5: 출장
                    switch (theme01){
                        case("0"):
                            themebtn01.setBackgroundResource(R.drawable.theme02create);
                            break;
                        case("1"):
                            themebtn01.setBackgroundResource(R.drawable.theme03create);
                            break;

                        case("2"):
                            themebtn01.setBackgroundResource(R.drawable.theme04create);
                            break;

                        case("3"):
                            themebtn01.setBackgroundResource(R.drawable.theme05create);
                            break;

                        case("4"):
                            themebtn01.setBackgroundResource(R.drawable.theme09create);
                            break;

                        default:
                            themebtn01.setBackgroundResource(R.drawable.theme01create);
                            break;

                    }
                }

            }
        });

        builder.show();
    }

    void show2(){
        final CharSequence[] items =  ListItemsSeason.toArray(new String[ListItemsSeason.size()]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogTheme);
        builder.setTitle("여행 계절 선택");
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
//                    SelectedItems.add(which);
                    theme02=which+"";
                    //0: 봄, 가을
                    //1: 여름
                    //2: 겨울
                    switch (theme02) {
                        case ("0"):
                            themebtn02.setBackgroundResource(R.drawable.theme06create);
                            break;
                        case("1"):
                            themebtn02.setBackgroundResource(R.drawable.theme07create);
                            break;
                        case("2"):
                            themebtn02.setBackgroundResource(R.drawable.theme08create);
                            break;
                        default:
                            themebtn02.setBackgroundResource(R.drawable.theme01create);
                    }
                }

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

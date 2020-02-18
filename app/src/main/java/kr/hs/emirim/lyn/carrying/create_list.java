package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class create_list extends AppCompatActivity implements View.OnClickListener {

    Button start_date;
    Button finish_date;
    public int sy=0, sm=0, sd=0;
    public int fy=0, fm=0,fd=0;
    public int Today_year,Today_month,Today_date;
    EditText City;
    Calendar cal=Calendar.getInstance();

    final Context context = this;
    private ImageButton btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Today_year=cal.get(Calendar.YEAR);
        Today_month=cal.get(Calendar.MONTH)+1;
        Today_date=cal.get(Calendar.DAY_OF_WEEK);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        City=findViewById(R.id.City);
        start_date=findViewById(R.id.start_date);
        finish_date=findViewById(R.id.finish_date);
        Button back = (Button)findViewById(R.id.back);
        Button add_btn=(Button)findViewById(R.id.add);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_list.this, Main_List.class);
                intent.putExtra("num","2");
//                intent.putExtra("city",City.getText().toString());
                intent.putExtra("city","오사카");
                intent.putExtra("start_date",sy+"-"+sm+"-"+sd);
                intent.putExtra("finish_date",fy+"-"+fm+"-"+fd);
                startActivity(intent);
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

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String msg="";
                for (int i = 0; i < SelectedItems.size(); i++) {
                    int index = (int) SelectedItems.get(i);
                    msg = msg+"\t"+ListItems.get(index);
                }
                Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_SHORT).show();
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

        datePickerDialog.setMessage("메시지");
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

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

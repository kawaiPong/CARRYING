package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class create_list extends AppCompatActivity {
    Button start_date;
    Button finish_date;
    public int sy=0, sm=0, sd=0;
    public int fy=0, fm=0,fd=0;
    EditText City;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                intent.putExtra("city",City.getText().toString());
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


    }//onCreate()

    void showDate1() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sy = year;
                sm = month+1;
                sd = dayOfMonth;
                Log.d("Hello",sy+"+"+sm+"+"+sd);
                start_date.setText("  "+sy+"년 "+sm+"월 "+ sd + "일");
            }
        },2019, 1, 11);

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
                finish_date.setText("  "+fy+"년 "+fm+"월 "+ fd + "일");
            }
        },2019, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }


}

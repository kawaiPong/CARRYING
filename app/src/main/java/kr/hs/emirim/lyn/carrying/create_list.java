package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class create_list extends AppCompatActivity {

    public int y=0, m=0, d=0, h=0, mi=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(create_list.this, Main_List.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.textView_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
                button.setText(y+"년 "+m+"월 "+ d + "일");
            }
        });

    }//onCreate()

    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                Log.d("Hello",y+"+"+m+"d");

            }
        },2019, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

}

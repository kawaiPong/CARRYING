package kr.hs.emirim.lyn.carrying.Login;

import androidx.appcompat.app.AppCompatActivity;
import kr.hs.emirim.lyn.carrying.Main_List;
import kr.hs.emirim.lyn.carrying.R;
import kr.hs.emirim.lyn.carrying.create_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        Button back = (Button)findViewById(R.id.backbtn);
        Button Re=(Button)findViewById(R.id.joinbtn);
        Button check=(Button)findViewById(R.id.okEmail);
        EditText pw=(EditText)findViewById(R.id.et_password);
        EditText pwr=(EditText)findViewById(R.id.et_passwordre);


        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

        check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이메일 확인버튼", Toast.LENGTH_LONG).show();
            }
        });

        Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((pw.getText().toString()).equals(pwr.getText().toString())){
                    Toast.makeText(getApplicationContext(), "확인되었습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

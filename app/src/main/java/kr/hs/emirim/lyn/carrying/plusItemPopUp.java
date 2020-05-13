package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class plusItemPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_item_pop_up);

        EditText plus_item_text=(EditText) findViewById(R.id.plus_item_popup);//추가하는 물품 텍스트
        Button submit=(Button)findViewById(R.id.check_popup);//확인버튼



        submit.setOnClickListener(new View.OnClickListener() {//삭제하는 retrofit
            @Override
            public void onClick(View v) {
                plus_item_text.getText().toString();
                Log.d("sowon plus item popUp",plus_item_text.getText().toString());

            }
        });


    }
}

package kr.hs.emirim.lyn.carrying.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.google.firebase.auth.FirebaseAuth;

import kr.hs.emirim.lyn.carrying.R;

public class RegisterActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth auth;
    Spinner spinner;
    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        String email = "ghddnjf2901@gmail.com".trim();
        String pw = "dnjs290112sb*".trim();

        auth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(RegisterActivity.this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });

        spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        item=new String[]{"남성","여성","둘 다 선택","둘 다 선택하지 않음"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

//        buttonLister();
        Button joinbtn = (Button)findViewById(R.id.joinbtn);
        Button back=(Button)findViewById(R.id.imageView3);
        joinbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });


    }

    private void buttonListener() {

//        sign_up_btn.setOnClickListener(view -> {
//            String email = sign_up_id.getText().toString();
//            String pw = sign_up_pw.getText().toString();
//
//            firebaseAuth.createUserWithEmailAndPassword(email, pw)
//                    .addOnCompleteListener(RegisterActivity.this, task -> {
//                        if (task.isSuccessful()) {
//                            Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    });
//        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}

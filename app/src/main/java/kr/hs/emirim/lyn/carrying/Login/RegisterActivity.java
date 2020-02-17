package kr.hs.emirim.lyn.carrying.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.google.firebase.auth.FirebaseAuth;

import kr.hs.emirim.lyn.carrying.R;

public class RegisterActivity extends BaseActivity {

    private FirebaseAuth auth;



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

//        buttonLister();
        Button joinbtn = (Button)findViewById(R.id.joinbtn);
        Button back=(Button)findViewById(R.id.imageView3);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup1);



        joinbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                //getCheckedRadioButtonId() 의 리턴값은 선택된 RadioButton 의 id 값.
                RadioButton rb = (RadioButton) findViewById(id);

                EditText NickNameE = (EditText) findViewById(R.id.et_name);
                EditText EmailE=(EditText)findViewById(R.id.et_eamil);
                EditText PasswordE=(EditText)findViewById(R.id.et_password);
                EditText CheckPasswordE=(EditText)findViewById(R.id.et_passwordre);


                String NickName = NickNameE.getText().toString().trim();
                String Email=EmailE.getText().toString().trim();
                String Password=PasswordE.getText().toString().trim();
                String CheckPassword=CheckPasswordE.getText().toString().trim();

                if((NickName.length() == 0)||
                        (Email.length() == 0 )||
                        ( Password.length() == 0 )||
                        (CheckPassword.length() == 0 ))
                {

                    Toast.makeText(getApplicationContext(), "모든 항목이 채워져있는지 확인해주세요", Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(Password.equals(CheckPassword)){
                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }

                }
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
}

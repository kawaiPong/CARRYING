package kr.hs.emirim.lyn.carrying.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        item = new String[]{"남성", "여성", "둘 다 선택", "둘 다 선택하지 않음"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

//        buttonLister();
        Button joinbtn = (Button) findViewById(R.id.joinbtn);
        Button back = (Button) findViewById(R.id.backbtn);

        TextView login=(TextView)findViewById(R.id.textView1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText NickNameE = (EditText) findViewById(R.id.et_name);
                EditText EmailE = (EditText) findViewById(R.id.et_eamil);
                EditText PasswordE = (EditText) findViewById(R.id.et_password);
                EditText CheckPasswordE = (EditText) findViewById(R.id.et_passwordre);


                String NickName = NickNameE.getText().toString().trim();
                String Email = EmailE.getText().toString().trim();
                String Password = PasswordE.getText().toString().trim();
                String CheckPassword = CheckPasswordE.getText().toString().trim();

                if ((NickName.length() == 0) ||
                        (Email.length() == 0) ||
                        (Password.length() == 0) ||
                        (CheckPassword.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "모든 항목이 채워져있는지 확인해주세요", Toast.LENGTH_LONG).show();

                } else {
                    if (Password.equals(CheckPassword)) {
                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

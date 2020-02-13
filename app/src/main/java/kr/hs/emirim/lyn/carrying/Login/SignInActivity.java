package kr.hs.emirim.lyn.carrying.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kr.hs.emirim.lyn.carrying.R;

public class SignInActivity extends BaseActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


//        init();
//        buttonListener();

        auth = FirebaseAuth.getInstance();

//        sign_in_btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                String email = sign_in_id.getText().toString().trim();
//                String pw = sign_in_pw.getText().toString().trim();
//
//                auth.signInWithEmailAndPassword(email, pw)
//                        .addOnCompleteListener(LoginActivity.this, task -> {
//
//                            if (task.isSuccessful()) {
//                                Toast.makeText(LoginActivity.this, "환영합니다", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(LoginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
//                            }
//
//                        });
//            }
//
//        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {

        } else {

        }
    }

    //
//    private void init() {
//
//        sign_in_btn = findViewById(R.id.sign_in_btn);
//        register_Text = findViewById(R.id.register_Text);
//        sign_in_id = findViewById(R.id.sign_in_id);
//        sign_in_pw = findViewById(R.id.sign_in_pw);
//
//    }
//
//    private void buttonListener() {
//        register_Text.setOnClickListener(view -> {
//            startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
//        });
//    }
}

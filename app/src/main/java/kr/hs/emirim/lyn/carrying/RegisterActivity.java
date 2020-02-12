package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

//        buttonLister();
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

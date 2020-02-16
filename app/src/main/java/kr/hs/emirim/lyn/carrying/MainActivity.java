package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import kr.hs.emirim.lyn.carrying.Login.SignInActivity;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";



    private FirebaseAuth auth;
    FirebaseUser user;

    Button signOut_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//       
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        Log.d(TAG, "Really create");

        signOut_btn = (Button)findViewById(R.id.signOut);
        signOut_btn.setOnClickListener(view -> {
            auth.signOut();
            Log.d(TAG, "로그아웃 버튼");
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        //익명 인증일 경우 user.getDisplayName == NULL;
        // Log.d(TAG, user.getUid()); 페이스북 로그인에 에러




    }//oncreate


}

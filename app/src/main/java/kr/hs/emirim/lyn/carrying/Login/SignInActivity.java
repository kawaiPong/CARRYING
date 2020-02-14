package kr.hs.emirim.lyn.carrying.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import kr.hs.emirim.lyn.carrying.DataAdapter;
import kr.hs.emirim.lyn.carrying.R;
import kr.hs.emirim.lyn.carrying.User;

public class SignInActivity extends BaseActivity {

    private FirebaseAuth auth;

    Button SignIn_email;
    Button SignIn_pw;
    Button SignIn_btn;
    Button checkPW_btn;
    Button SignUp_btn;
    Button fb_btn;
    Button tt_btn;
    Button gg_btn;
    Button anonymous;
    Button emailText;
    Button pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initLoadDB();
        this.InitializeView();
        this.SetListener();

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



    private void SetListener() {
        SignIn_btn=(Button)(findViewById(R.id.Signin));
        SignIn_email=(Button)(findViewById(R.id.signin_email));;
        SignIn_pw=(Button)(findViewById(R.id.signIn_pw));;
        checkPW_btn=(Button)(findViewById(R.id.check));;
        SignUp_btn=(Button)(findViewById(R.id.signup));;
        fb_btn=(Button)(findViewById(R.id.fb));;
        tt_btn=(Button)(findViewById(R.id.tt));;
        gg_btn=(Button)(findViewById(R.id.gg));;
        anonymous=(Button)(findViewById(R.id.anonymous));;

    }

    private void InitializeView() {
        SignIn_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        SignIn_email.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        SignIn_pw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        checkPW_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        fb_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        tt_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        gg_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        anonymous.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

        SignUp_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public List<User> userList ;

    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        userList = mDbHelper.getTableData();
        Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
        mDbHelper.close();
    }


}

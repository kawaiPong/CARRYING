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

    EditText SignIn_email;
    EditText SignIn_pw;
    Button SignIn_btn;
    Button checkPW_btn;
    Button SignUp_btn;
    Button fb_btn;
    Button tt_btn;
    Button gg_btn;
    Button anonymous;
    Button emailText;
    Button pwText;

    private FirebaseAuth auth;
    private FirebaseUser user;

    Button signOut_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        init();

//        signOut_btn = (Button)findViewById(R.id.signOut);
//        signOut_btn.setOnClickListener(view -> {
//            auth.signOut();
//            Log.d(TAG, "로그아웃 버튼");
//            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
//            startActivity(intent);
//        });

        //익명 인증일 경우 user.getDisplayName == NULL;
        // Log.d(TAG, user.getUid()); 페이스북 로그인에 에러

        initLoadDB();
        this.InitializeView();



    }//oncreate

    private void init() {
        SignIn_btn=(Button)findViewById(R.id.Signin);
        SignIn_email=(EditText)(findViewById(R.id.signin_email));
        SignIn_pw=(EditText)(findViewById(R.id.signIn_pw));
        checkPW_btn=(Button)(findViewById(R.id.check));
        SignUp_btn=(Button)(findViewById(R.id.signup));
        fb_btn=(Button)(findViewById(R.id.fb));
        tt_btn=(Button)(findViewById(R.id.tt_btn));
        gg_btn=(Button)(findViewById(R.id.gg));
        anonymous=(Button)(findViewById(R.id.anonymous));

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

package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;
import kr.hs.emirim.lyn.carrying.API.OpenWeatherAPITask;
import kr.hs.emirim.lyn.carrying.API.Weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

//    Button SignIn_email;
//    Button SignIn_pw;
//    Button SignIn_btn;
//    Button checkPW_btn;
//    Button SignUp_btn;
//    Button fb_btn;
//    Button tt_btn;
//    Button gg_btn;
//    Button anonymous;
//    Button emailText;
//    Button pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initLoadDB();
//        this.InitializeView();
//        this.SetListener();


    }//oncreate



//    private void SetListener() {
//        SignIn_btn=(Button)(findViewById(R.id.Signin));
//        SignIn_email=(Button)(findViewById(R.id.signin_email));;
//        SignIn_pw=(Button)(findViewById(R.id.signIn_pw));;
//        checkPW_btn=(Button)(findViewById(R.id.check));;
//        SignUp_btn=(Button)(findViewById(R.id.signup));;
//        fb_btn=(Button)(findViewById(R.id.fb));;
//        tt_btn=(Button)(findViewById(R.id.tt));;
//        gg_btn=(Button)(findViewById(R.id.gg));;
//        anonymous=(Button)(findViewById(R.id.anonymous));;
//
//    }
//
//    private void InitializeView() {
//        SignIn_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        SignIn_email.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        SignIn_pw.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        checkPW_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        fb_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        tt_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        gg_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        anonymous.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        SignUp_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }
//
//
//    public List<User> userList ;
//
//    private void initLoadDB() {
//
//        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
//        mDbHelper.createDatabase();
//        mDbHelper.open();
//
//        // db에 있는 값들을 model을 적용해서 넣는다.
//        userList = mDbHelper.getTableData();
//        Toast.makeText(getApplicationContext(), "테스트테스트:::"+userList.get(0).getName(), Toast.LENGTH_LONG).show();
//        mDbHelper.close();
//    }
}

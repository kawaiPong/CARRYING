package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import kr.hs.emirim.lyn.carrying.Login.SignInActivity;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    private FirebaseAuth auth;
    private FirebaseUser user;

    Button signOut_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

//        signOut_btn = (Button)findViewById(R.id.signOut);
        signOut_btn.setOnClickListener(view -> {
            auth.signOut();
            Log.d(TAG, "로그아웃 버튼");
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        //익명 인증일 경우 user.getDisplayName == NULL;
        // Log.d(TAG, user.getUid()); 페이스북 로그인에 에러

        initLoadDB();
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

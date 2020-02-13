package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

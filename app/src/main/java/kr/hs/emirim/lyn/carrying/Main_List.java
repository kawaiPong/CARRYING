package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import kr.hs.emirim.lyn.carrying.Login.SignInActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__list);
        Button plus_btn = (Button)findViewById(R.id.plus);
        Button hamburger=(Button)findViewById(R.id.hamburger);

        plus_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "create List", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(Main_List.this, create_list.class);
                startActivity(intent);

            }
        });

        hamburger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "햄버거 액티비티 아직 안만듬", Toast.LENGTH_LONG).show();
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
//                drawer.openDrawer(Gravity.LEFT);
            }
        });

    }
    }

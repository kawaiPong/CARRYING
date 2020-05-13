package kr.hs.emirim.lyn.carrying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.hs.emirim.lyn.carrying.Retrofit.CheckList;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.checkListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class plusItemPopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_item_pop_up);

        EditText plus_item_text=(EditText) findViewById(R.id.plus_item_popup);//추가하는 물품 텍스트
        Button submit=(Button)findViewById(R.id.check_popup);//확인버튼



        submit.setOnClickListener(new View.OnClickListener() {//추가하는 retrofit
            @Override
            public void onClick(View v) {
                plus_item_text.getText().toString();
                Log.d("sowon plus item popUp",plus_item_text.getText().toString());

                if(plus_item_text.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "빈칸이 있습니다.", Toast.LENGTH_LONG).show();

                }
                else{//여기가 ㄹㅇ중요함


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://ec2-15-164-215-173.ap-northeast-2.compute.amazonaws.com:3000")
//                .baseUrl("http://192.168.219.142:4000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);

                    //list_num 임의로 1줌 intent해서 값 받아야 할듯
                    Call<checkListItem> apiCall = apiService.deleteOneItem(
                            1,
                            plus_item_text.getText().toString()+""
                    );


                    apiCall.enqueue(new Callback<checkListItem>() {
                        @Override
                        public void onResponse(Call<checkListItem> call, Response<checkListItem> response) {

                            checkListItem du = response.body();
                            Log.d("sowon mytag ","됨 ok : "+ du.toString());
                            Log.d("sowon du.getName", du.getName());//item이름이 잘 들어갔나 확인
                            Log.d("sowon du.getListnum", du.getList_num()+"");//item이름이 잘 들어갔나 확인

                            Toast.makeText(getApplicationContext(), "아이템이 추가되었습니다.", Toast.LENGTH_LONG).show();
                            finish();

                        }
                        @Override
                        public void onFailure(Call<checkListItem> call, Throwable t) {
                            Log.d("mytag", "안됨 fail : " + t.toString());
                            Toast.makeText(getApplicationContext(), "아이템추가에 실패했습니다.", Toast.LENGTH_LONG).show();
                        }
                    });






                }

            }
        });


    }
}

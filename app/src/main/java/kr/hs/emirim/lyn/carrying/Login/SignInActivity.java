package kr.hs.emirim.lyn.carrying.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.facebooklogin.LoginCallback;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;

import kr.hs.emirim.lyn.carrying.MainActivity;
import kr.hs.emirim.lyn.carrying.Main_List;
import kr.hs.emirim.lyn.carrying.R;
import kr.hs.emirim.lyn.carrying.Retrofit.RetrofitExService;
import kr.hs.emirim.lyn.carrying.Retrofit.User;
import kr.hs.emirim.lyn.carrying.create_list;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends BaseActivity {

    private final static String TAG = "SignInActivity";
    private FirebaseAuth auth;
    private FirebaseUser user;

    private LoginButton fb_btn;
    private LoginCallback mLoginCallback;
    private CallbackManager mCallbackManager;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-54-180-82-41.ap-northeast-2.compute.amazonaws.com:3000")
            //                            .baseUrl("http://192.168.219.142:4000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);

//    EditText SignIn_email;
//    EditText SignIn_pw;
    Button SignIn_btn;
    Button checkPW_btn;
    Button SignUp_btn;
    Button anonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mCallbackManager = CallbackManager.Factory.create();
        mLoginCallback = new LoginCallback();

        SignIn_btn = (Button) (findViewById(R.id.Signin));
//        final EditText SignIn_email = (EditText) (findViewById(R.id.signin_email));
//        final EditText SignIn_pw = (EditText) (findViewById(R.id.signIn_pw));
        checkPW_btn = (Button) (findViewById(R.id.check));
        SignUp_btn = (Button) (findViewById(R.id.signup));
        fb_btn = (LoginButton) findViewById(R.id.btn_facebook_login);
        fb_btn.setPermissions(Arrays.asList("public_profile", "email"));
        fb_btn.registerCallback(mCallbackManager, mLoginCallback);
        anonymous = (Button) (findViewById(R.id.anonymous));

        SetListener();
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        user = auth.getCurrentUser();
        checkCurrentUser(user);

    }

    public void checkCurrentUser(FirebaseUser user) {
        // [START check_current_user]
        if (user != null) {
            //uid로 로그인하기
            //readUser/:uid로 넘어가기
            String user_uid = user.getUid();
            Log.d("email", user_uid);
            apiService.getData(user_uid).enqueue(new Callback<User>() {//drawer에 닉네임이랑 이메일 뜨게하기 위한 작업
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    User du = response.body();
                    Log.d("sowon drawer modify","됨 ok : "+ du.toString());
                    Log.d("data.getNickname() : ", du.getNickname() + "");
                    Intent intent = new Intent(SignInActivity.this, Main_List.class);
                    intent.putExtra("gender",du.getGender());
                    intent.putExtra("uid",du.getUid());
                    intent.putExtra("num","1");
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onFailure(@NonNull Call<User> call,@NonNull Throwable t) {
                    Log.d("mytag Main", "안됨 fail : " + t.toString());
                }
            });
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }

    private void SetListener() {

        SignIn_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText SignIn_email = (EditText) (findViewById(R.id.signin_email));
                EditText SignIn_pw = (EditText) (findViewById(R.id.signIn_pw));

                String id = SignIn_email.getText().toString().trim();
                String password = SignIn_pw.getText().toString().trim();

                Log.d("sowon", id+":::"+password);

                if ((id.length() == 0) || (password.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "이메일과 비밀번호를 다시확인해주세요.", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), SignIn_pw.getText().toString().trim()+":::"+SignIn_pw.getText().toString().trim(), Toast.LENGTH_LONG).show();
                } else {
                    auth.signInWithEmailAndPassword(id, password)
                            .addOnCompleteListener(SignInActivity.this, task -> {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "signInWithEmail:success");
                                        user = auth.getCurrentUser();

                                        Call<User> apiCall = apiService.getDataEmail(id);
                                        apiCall.enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                User du = response.body();
                                                Log.d("mytag 됨 Sign", du.toString());
                                                Log.d("data.getUserId() 닉네임 : ", du.getNickname() + "");
                                                if(password.equals(du.getPassword())){
                                                    Log.d("mytag 됨 Sign",password+"::"+du.getPassword()+":이메일은:"+id);
                                                    Intent intent=new Intent(getApplicationContext(), Main_List.class);
                                                    intent.putExtra("gender",du.getGender());
                                                    intent.putExtra("uid",du.getUid());
                                                    intent.putExtra("num","1");
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<User> call, Throwable t) {
                                                Log.d("mytag Sign", "안됨 fail : " + t.toString());
                                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();

                                            }
                                        });
                    //                    signIn_email(id, password);
                                    } else {
                                        Log.d(TAG, "signInWithEmail:failure", task.getException());
                                    }
                            });
                }

            }
        });


        checkPW_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, FindPassword.class);
                startActivity(intent);
            }
        });

        anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAnonymously();
            }
        });

        SignUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, RegisterActivity.class));
            }
        });
    }

    //find
    private void init() {

    }

    private void signIn_email(String id, String password) {
        auth.signInWithEmailAndPassword(id, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Log.d(TAG, "이메일 로그인 버튼");
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void signInAnonymously() {
        showProgressDialog();
        // [START signin_anonymously]
        auth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            Log.d(TAG, "익명 로그인 버튼");
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END signin_anonymously]
    }
}
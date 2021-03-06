package kr.hs.emirim.lyn.carrying.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


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
import retrofit2.http.HEAD;

public class SignInActivity extends BaseActivity  {

    private final static String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;


    private FirebaseAuth auth;
    private FirebaseUser user;
    GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-54-180-93-190.ap-northeast-2.compute.amazonaws.com:3000")
            //                            .baseUrl("http://192.168.219.142:4000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);

    //    EditText SignIn_email;
//    EditText SignIn_pw;
    Button SignIn_btn;
    Button checkPW_btn;
    Button SignUp_btn;
    Button SignUp_gg;
    Button anonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        SignIn_btn = (Button) (findViewById(R.id.Signin));
//        final EditText SignIn_email = (EditText) (findViewById(R.id.signin_email));
//        final EditText SignIn_pw = (EditText) (findViewById(R.id.signIn_pw));
        checkPW_btn = (Button) (findViewById(R.id.check));
        SignUp_btn = (Button) (findViewById(R.id.signup));
        SignUp_gg = (Button) (findViewById(R.id.btn_google_login));
        anonymous = (Button) (findViewById(R.id.anonymous));

        SetListener();
        auth = FirebaseAuth.getInstance();

        // Configure Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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
                    Log.d("mytag checkCurrent", "안됨 fail : " + t.toString());
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
//                    Toast.makeText(getApplicationContext(), SignIn_pw.getText().toString().trim()+":::"+SignIn_pw.getText().toString().trim(), Toast.LENGTH_LONG).show();


                } else {


                    Retrofit retrofit = new Retrofit.Builder()
                           .baseUrl("http://ec2-54-180-93-190.ap-northeast-2.compute.amazonaws.com:3000")
//                            .baseUrl("http://192.168.219.142:4000")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
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
        SignUp_gg.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                signIn_google();
            }
        }));
    }

    //find
    private void init() {

    }

    private void signIn_google() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();

                            Log.d("useUid", user.getUid().toString());
                            Log.d("useEmail", user.getEmail().toString());
                            Log.d("user.name", user.getDisplayName().toString());
                            Log.d("userProviderId", user.getProviderId().toString());

                            String uid = user.getUid().toString();
                            String email = user.getEmail().toString();
                            String name = user.getDisplayName().toString();
                            String provideId = user.getProviderId().toString();

                            Call<User> apiCall = apiService.postData(uid,name,email,provideId,2);
                            Log.d("SOWON retrofit",uid+":"+name+":"+email+":"+provideId+":"+2+":");
                            apiCall.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    User du = response.body();
                                    Log.d("구글 회원가입", "성공");

                                    Intent intent=new Intent(getApplicationContext(), Main_List.class);
                                    intent.putExtra("gender",2);
                                    intent.putExtra("uid",du.getUid());
                                    intent.putExtra("num","1");
                                    startActivity(intent);
                                    finish();
                                }
                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.d("mytag", "안됨 fail : " + t.toString());
                                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();

                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
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

                            FirebaseUser user = auth.getCurrentUser();

                            Call<User> apiCall = apiService.postData(user.getUid(),"익명","anonymous", user.getProviderId(),2);
                            Log.d("SOWON retrofit",user.getUid()+": 익명 : anonymous :"+user.getProviderId()+":"+2+":");
                            apiCall.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    User du = response.body();
                                    Log.d("익명", "성공");

                                    Intent intent=new Intent(getApplicationContext(), Main_List.class);
                                    intent.putExtra("gender",2);
                                    intent.putExtra("uid",du.getUid());
                                    intent.putExtra("num","1");
                                    startActivity(intent);
                                    finish();
                                }
                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.d("mytag", "안됨 fail : " + t.toString());
                                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();

                                }
                            });
//                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
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
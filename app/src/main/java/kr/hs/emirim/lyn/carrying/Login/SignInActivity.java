package kr.hs.emirim.lyn.carrying.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.zxing.common.StringUtils;


import java.util.List;

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
    private static final int RC_SIGN_IN = 9001;
    private CallbackManager callbackManager;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth auth;
    private FirebaseUser user;


    EditText SignIn_email;
    EditText SignIn_pw;
    Button SignIn_btn;
    Button checkPW_btn;
    Button SignUp_btn;
    LoginButton fb_btn;
    Button tt_btn;
    Button gg_btn;
    Button anonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        init();
        SetListener();

        callbackManager = CallbackManager.Factory.create();
        fb_btn.setBackgroundResource(R.drawable.facebook);
        fb_btn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        fb_btn.setCompoundDrawablePadding(0);
        fb_btn.setPadding(0, 0, 0, 0);
        fb_btn.setText("");
        fb_btn.setPermissions("email", "public_profile");
        fb_btn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.d(TAG, "페북 로그인 버튼");
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

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
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            Log.d("ProviderID", user.getProviderData().toString());
            auth.signOut();
        } else {
            // No user is signed in
        }
        // [END check_current_user]
    }

    private void SetListener() {


        SignIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ed1 = (EditText) findViewById(R.id.signin_email);
                EditText ed2 = (EditText) findViewById(R.id.signIn_pw);

                String ed_text1 = ed1.getText().toString().trim();//이메일
                String ed_text2 = ed2.getText().toString().trim();//비밀번호
                if ((ed_text1.length() == 0) || (ed_text2.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "이메일과 비밀번호를 다시확인해주세요.", Toast.LENGTH_LONG).show();

                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.219.142:3000")
//                            .baseUrl("http://localhost:1234")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final RetrofitExService apiService = retrofit.create(RetrofitExService.class);
                    Call<User> apiCall = apiService.getDataEmail(ed_text1);
                    apiCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User du = response.body();
                            Log.d("mytag 됨 Sign", du.toString());
                            Log.d("data.getUserId() 닉네임 : ", du.getNickname() + "");
                            if(ed_text2.equals(du.getPassword())){
                                Log.d("mytag 됨 Sign",ed_text2+"::"+du.getPassword()+":이메일은:"+ed_text1);
                                Intent intent=new Intent(getApplicationContext(), Main_List.class);
                                intent.putExtra("email",du.getEmail());
                                intent.putExtra("num","1");
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d("mytag Sign", "안됨 fail : " + t.toString());
                            Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();

                        }
                    });


//                    signIn_email();

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


        tt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        gg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn_google();
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
        SignIn_btn = (Button) (findViewById(R.id.Signin));
        SignIn_email = (EditText) (findViewById(R.id.signin_email));
        SignIn_pw = (EditText) (findViewById(R.id.signIn_pw));
        checkPW_btn = (Button) (findViewById(R.id.check));
        SignUp_btn = (Button) (findViewById(R.id.signup));
        fb_btn = (LoginButton) (findViewById(R.id.fb));
        tt_btn = (Button) (findViewById(R.id.tt));
        gg_btn = (Button) (findViewById(R.id.gg));
        anonymous = (Button) (findViewById(R.id.anonymous));
    }



    private void signIn_google() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d(TAG, "구글 로그인 버튼");
    }

    private void signIn_email() {

        String email = SignIn_email.getText().toString().trim();
        String password = SignIn_pw.getText().toString().trim();

        auth.signInWithEmailAndPassword(email, password)
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

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }

                        // ...
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        } else {
            // Pass the activity result back to the Facebook SDK
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }
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
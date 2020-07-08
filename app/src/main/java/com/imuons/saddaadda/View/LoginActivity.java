package com.imuons.saddaadda.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.submitBtn)
    TextView tv_signup;
    @BindView(R.id.txForgetPass)
    TextView txForgetPass;

    @BindView(R.id.etUserId)
    EditText et_userId;

    @BindView(R.id.etPassword)
    EditText et_password;
    String fireBase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.i("getInstanceId failed::", task.getException().toString());
                            return;
                        }

                        // Get new Instance ID token
                        fireBase = task.getResult().getToken();

                        // Log and toast
                        // String msg = getString(R.string.msg_token_fmt, token);
                        Log.i("token::", fireBase);
                        //   Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @OnClick(R.id.txForgetPass)
    void gotoForgetPage() {
        startActivity(new Intent(this, ForgetPassword.class));
    }

    @OnClick(R.id.submitBtn)
    void submitCall() {
        String password = et_password.getText().toString().trim();
        String userId = et_userId.getText().toString().trim();

        if (userId.isEmpty()) {
            et_userId.setError(getResources().getString(R.string.enter_user_d));
        } else if (password.isEmpty()) {
            et_userId.setError(getResources().getString(R.string.enter_passsword));
        } else
            callLoginApi(new LoginEntity(userId, password, fireBase));

    }

    private void callLoginApi(LoginEntity loginEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            final Dialog dialog = ViewUtils.getProgressBar(LoginActivity.this);
            Call call = apiService.LoginApi(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    dialog.dismiss();
                    LoginResponseModel authResponse = (LoginResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Log.i("token:::", authResponse.getData().getAccessToken());
                            AppCommon.getInstance(LoginActivity.this).setToken(authResponse.getData().getAccessToken());
                            AppCommon.getInstance(LoginActivity.this).setUserLogin(et_userId.getText().toString().trim(), true);
                            AppCommon.getInstance(LoginActivity.this).setPassword(et_password.getText().toString().trim());
                            AppCommon.getInstance(LoginActivity.this).setSesstionId(authResponse.getData().getSession_id());
                            AppCommon.getInstance(LoginActivity.this).setDemo("Live");
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //   AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Server Error");
                        Toast.makeText(LoginActivity.this, "The user credentials were incorrect", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}

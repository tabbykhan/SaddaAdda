package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @BindView(R.id.etUserId)
    EditText et_userId;

    @BindView(R.id.etPassword)
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.submitBtn)
    void submitCall(){
        String password = et_password.getText().toString().trim();
        String userId = et_userId.getText().toString().trim();
        if (userId.isEmpty()) {
            et_userId.setError("Please enter User ID");
        } else if (password.isEmpty()) {
            et_userId.setError("Please enter password");
        } else
            callLoginApi(new LoginEntity(userId, password));
        startActivity(new Intent(this , HomeActivity.class));
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
                            AppCommon.getInstance(LoginActivity.this).setUserObject(new Gson().toJson(authResponse.getData()));
                            AppCommon.getInstance(LoginActivity.this).setToken(authResponse.getData().getAccessToken());
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Server Error");
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

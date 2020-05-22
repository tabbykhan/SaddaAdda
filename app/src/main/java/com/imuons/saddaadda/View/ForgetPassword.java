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
import com.imuons.saddaadda.EntityClass.ResetPasswordEntity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.ForgetPasswordResponse;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.UpdateProfileResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imuons.saddaadda.Utils.MyPreference.userId;

public class ForgetPassword extends AppCompatActivity {
    @BindView(R.id.submitBtn)
    TextView tv_signup;

    @BindView(R.id.etUserId)
    EditText et_userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.submitBtn)
    void forgetPassword() {
        String userId = et_userId.getText().toString().trim();
        if (userId.isEmpty()) {
            et_userId.setError("Please enter User ID");
        }
        callForResetPass(new ResetPasswordEntity(userId));

    }

    private void callForResetPass(ResetPasswordEntity resetPasswordEntity) {

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ForgetPassword.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.FORGET_PASSWORD_CALL(resetPasswordEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    dialog.dismiss();
                    ForgetPasswordResponse authResponse = (ForgetPasswordResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ResetResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
                            finish();
                            Toast.makeText(ForgetPassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ForgetPassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ForgetPassword.this).showDialog(ForgetPassword.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassword.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}


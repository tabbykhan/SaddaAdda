package com.imuons.saddaadda.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.EntityClass.RegitrationEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.RandomUserIdResponse;
import com.imuons.saddaadda.responseModel.RegisterResponse;
import com.imuons.saddaadda.responseModel.VerifyUserResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccount extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etRefralCode)
    EditText etRefralCode;
    @BindView(R.id.etUserId)
    EditText etUserId;
    @BindView(R.id.etMobile)
    EditText etMobile;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etCmfPassword)
    EditText etCmfPassword;
    @BindView(R.id.pin)
    EditText pin;
    @BindView(R.id.c_pin)
    EditText cpin;
    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.submitBtn)
    TextView submitBtn;

    String fireBase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        etRefralCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start >11){
                    etRefralCode.setError("Please enter valid id");
                }else if(start == 11){
                    callVarifyId(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        callRendomNumberAPI();
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.i("getInstanceId failed::", task.getException().toString());
                            return;
                        }

                        // Get new Instance ID token
                        fireBase= task.getResult().getToken();

                        // Log and toast
                        // String msg = getString(R.string.msg_token_fmt, token);
                        Log.i("token::", fireBase);
                        //   Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void callVarifyId(String id) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
           // Dialog dialog = ViewUtils.getProgressBar(CreateAccount.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.checkUserApi(new OtpEnitity(id));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                   // dialog.dismiss();
                    VerifyUserResponse authResponse = (VerifyUserResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                        } else {
                            etRefralCode.setError(authResponse.getMessage());
                           // Toast.makeText(CreateAccount.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                       // AppCommon.getInstance(CreateAccount.this).showDialog(CreateAccount.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    //dialog.dismiss();
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                    // loaderView.setVisibility(View.GONE);
                   // Toast.makeText(CreateAccount.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.submitBtn)
    void submitCall() {
        String userId = etUserId.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String referralCode = etRefralCode.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String cmf_password = etCmfPassword.getText().toString().trim();
        String pintxt = pin.getText().toString().trim();
        String cpintxt = cpin.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if (userId.isEmpty()) {
            etUserId.setError(getResources().getString(R.string.pls_enter_user_id));
        } else if (password.isEmpty()) {
            etPassword.setError(getResources().getString(R.string.psl_enter_password));
        } else if (name.isEmpty()) {
            etName.setError(getResources().getString(R.string.psl_enter_name));
        } else if (referralCode.isEmpty()) {
            etRefralCode.setError(getResources().getString(R.string.psl_enter_ref_code));
        } else if (mobile.isEmpty()) {
            etMobile.setError(getResources().getString(R.string.pls_enter_mobiel_no_10));
        } else if (cmf_password.isEmpty()) {
            etCmfPassword.setError(getResources().getString(R.string.ps_enter_conf_pwd));
        } else if (!cmf_password.matches(password)) {
            etCmfPassword.setError(getResources().getString(R.string.cofirm_pwd_not_match));
        } else if (pintxt.isEmpty()) {
            pin.setError(getResources().getString(R.string.enter_pin));
        } else if (!cmf_password.matches(password)) {
            cpin.setError(getResources().getString(R.string.confim_pin_not_match));
        } else
            callRegisterApi(userId, name, password, mobile, referralCode , pintxt,email);

    }

    private void callRegisterApi(String userId, String name, String password, String mobile, String referralCode , String pin, String email) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(CreateAccount.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.RegisterApi(new RegitrationEntity(userId, name, password, mobile, referralCode , pin,email));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                    dialog.dismiss();
                    RegisterResponse authResponse = (RegisterResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            callLoginApi(new LoginEntity(authResponse.getData().getUserId(), authResponse.getData().getPassword() , fireBase));
                        } else {
                            Toast.makeText(CreateAccount.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(CreateAccount.this).showDialog(CreateAccount.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(CreateAccount.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }


    private void callLoginApi(LoginEntity loginEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Dialog dialog = ViewUtils.getProgressBar(CreateAccount.this);
            Call call = apiService.LoginApi(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                    dialog.dismiss();
                    LoginResponseModel authResponse = (LoginResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("LoginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            AppCommon.getInstance(CreateAccount.this).setUserObject(new Gson().toJson(authResponse.getData()));
                            AppCommon.getInstance(CreateAccount.this).setToken(authResponse.getData().getAccessToken());
                            AppCommon.getInstance(CreateAccount.this).setUserLogin(loginEntity.getUser_id(), true);
                            AppCommon.getInstance(CreateAccount.this).setSesstionId(authResponse.getData().getSession_id());
                            AppCommon.getInstance(CreateAccount.this).setDemo("Live");
                            startActivity(new Intent(CreateAccount.this, HomeActivity.class));
                        } else {
                            Toast.makeText(CreateAccount.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(CreateAccount.this).showDialog(CreateAccount.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(CreateAccount.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void callRendomNumberAPI() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
           // Dialog dialog = ViewUtils.getProgressBar(CreateAccount.this);
            Call call = apiService.GetRendomNumber();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                  //  dialog.dismiss();
                    RandomUserIdResponse authResponse = (RandomUserIdResponse) response.body();
                    if (authResponse != null) {
                        Log.i("RendomResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                              etUserId.setText(String.valueOf(authResponse.getData().getUserId()));
                        } else {
                            Toast.makeText(CreateAccount.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(CreateAccount.this).showDialog(CreateAccount.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                //    dialog.dismiss();
                    AppCommon.getInstance(CreateAccount.this).clearNonTouchableFlags(CreateAccount.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(CreateAccount.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}

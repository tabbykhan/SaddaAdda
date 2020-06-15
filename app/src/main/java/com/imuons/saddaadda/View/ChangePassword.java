package com.imuons.saddaadda.View;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.saddaadda.EntityClass.ChangePasswordEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.ChangePasswordResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    @BindView(R.id.etOtp)
    EditText oldPin;
    @BindView(R.id.etNewPin)
    EditText newPin;
    @BindView(R.id.etConfirmPin)
    EditText confirmPin;
    @BindView(R.id.otp_et)
    EditText etOtp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submitBtn)
    void submit(){
        String otpTxt = oldPin.getText().toString().trim();
        String newPinTxt = newPin.getText().toString().trim();
        String cPin = confirmPin.getText().toString().trim();
        String otpNew = etOtp2.getText().toString().trim();
        if(otpTxt.isEmpty()){
            oldPin.setError(getString(R.string.pls_enter_old_pwd));
        }else if(newPinTxt.isEmpty()){
            newPin.setError(getString(R.string.pls_enter_new_pwd));
        }else if(cPin.isEmpty()){
            confirmPin.setError(getString(R.string.pls_confirm_pwd));
        }else if(otpNew.isEmpty()){
            etOtp2.setError(getString(R.string.pls_enter_otp));
        }else {
            callChangePassword(otpTxt , newPinTxt , cPin , otpNew);
        }
    }

    private void callChangePassword(String otpTxt, String newPinTxt, String cPin, String otpNew) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ChangePassword.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class , AppCommon.getInstance(this).getToken());
            Call call = apiService.change_password(new ChangePasswordEntity(otpTxt ,newPinTxt ,cPin , otpNew));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChangePassword.this).clearNonTouchableFlags(ChangePassword.this);
                    dialog.dismiss();
                    ChangePasswordResponse authResponse = (ChangePasswordResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(ChangePassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ChangePassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ChangePassword.this).showDialog(ChangePassword.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ChangePassword.this).clearNonTouchableFlags(ChangePassword.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ChangePassword.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    
        
    }
}

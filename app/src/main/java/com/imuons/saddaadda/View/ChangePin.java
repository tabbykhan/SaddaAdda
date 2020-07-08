package com.imuons.saddaadda.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.saddaadda.EntityClass.ChangePinEntity;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.OptResponse;
import com.imuons.saddaadda.responseModel.PinResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePin extends AppCompatActivity {

    @BindView(R.id.etOtp)
    EditText oldPin;
    @BindView(R.id.etNewPin)
    EditText newPin;
    @BindView(R.id.etConfirmPin)
    EditText confirmPin;
    @BindView(R.id.otp_et)
    EditText etOtp2;

    @BindView(R.id.submitBtn)
    TextView submitBtn;
    @BindView(R.id.resendOtp)
    TextView resendOtp;

    boolean isOtpSend = false;
    boolean resent = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);
        ButterKnife.bind(this);
        resendOtp.setVisibility(View.VISIBLE);
        resendOtp.setText(getString(R.string.sendOtp));
    }

    @OnClick(R.id.submitBtn)
    void submit() {
        String otpTxt = oldPin.getText().toString().trim();
        String newPinTxt = newPin.getText().toString().trim();
        String cPin = confirmPin.getText().toString().trim();
        String otpNew = etOtp2.getText().toString().trim();
        if (otpTxt.isEmpty()) {
            oldPin.setError(getResources().getString(R.string.please_enter_old_pin));
        } else if (newPinTxt.isEmpty()) {
            newPin.setError(getString(R.string.pls_enter_new_pin));
        } else if (cPin.isEmpty()) {
            confirmPin.setError(getString(R.string.pls_enter_new_pin));
        } else if (otpNew.isEmpty()) {
            etOtp2.setError(getString(R.string.pls_enter_otp));
        } else {
            callChangePin(otpTxt, newPinTxt, cPin, otpNew);
        }
       /* if (isOtpSend) {

        } else {
           // Toast.makeText(this, "Please send O", Toast.LENGTH_SHORT).show();
            callSendApi();
        }*/
    }

    private void callChangePin(String oldPin, String newPinTxt, String cPin, String otp) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ChangePin.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.ChangePinApi(new ChangePinEntity(oldPin, newPinTxt, cPin, otp));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChangePin.this).clearNonTouchableFlags(ChangePin.this);
                    dialog.dismiss();
                    PinResponse authResponse = (PinResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(ChangePin.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ChangePin.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ChangePin.this).showDialog(ChangePin.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ChangePin.this).clearNonTouchableFlags(ChangePin.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ChangePin.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }


    public void callSendApi() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ChangePin.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.SendOTP_FOR_PIN(new OtpEnitity(AppCommon.getInstance(this).getUserId()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChangePin.this).clearNonTouchableFlags(ChangePin.this);
                    dialog.dismiss();
                    OptResponse authResponse = (OptResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(ChangePin.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            isOtpSend = true;
                            submitBtn.setText(getString(R.string.update));
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    resent = false;
                                    resendOtp.setVisibility(View.VISIBLE);
                                    resendOtp.setText(String.valueOf(getString(R.string.resentOtp) + ": 00:" + millisUntilFinished / 1000));
                                    //here you can have your logic to set text to edittext
                                }

                                public void onFinish() {
                                    resent = true;
                                    resendOtp.setText(getString(R.string.resentOtp));
                                    //mTextField.setText("done!");
                                }

                            }.start();

                        } else {
                            Toast.makeText(ChangePin.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ChangePin.this).showDialog(ChangePin.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ChangePin.this).clearNonTouchableFlags(ChangePin.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ChangePin.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.resendOtp)
    void setResendOtp() {
        if (resent) {
            isOtpSend = true;
            callSendApi();
            resent = false;
        }
    }
}

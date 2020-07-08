package com.imuons.saddaadda.View;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.EntityClass.PinEntitiy;
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

public class PinEnterActivity extends AppCompatActivity {

    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    EditText et4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_enter);
        ButterKnife.bind(this);
       /* et1.addTextChangedListener(new GenericTextWatcher(et1));
        et2.addTextChangedListener(new GenericTextWatcher(et2));
        et3.addTextChangedListener(new GenericTextWatcher(et3));
        et4.addTextChangedListener(new GenericTextWatcher(et4));*/

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }



            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et2.requestFocus();
                    et1.setBackground(getDrawable(R.drawable.circle));
                }
                else if(s.length()==0)
                {
                    et1.clearFocus();
                    et1.setBackground(getDrawable(R.drawable.circle_2));
                }
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et3.requestFocus();
                    et2.setBackground(getDrawable(R.drawable.circle));
                }
                else if(s.length()==0)
                {
                    et1.requestFocus();
                    et2.setBackground(getDrawable(R.drawable.circle_2));
                }
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et4.requestFocus();
                    et3.setBackground(getDrawable(R.drawable.circle));
                }
                else if(s.length()==0)
                {
                    et2.requestFocus();
                    et3.setBackground(getDrawable(R.drawable.circle_2));
                }
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                String p1 = et1.getText().toString().trim();
                String p2 = et2.getText().toString().trim();
                String p3 = et3.getText().toString().trim();
                String p4 = et4.getText().toString().trim();
                et4.setBackground(getDrawable(R.drawable.circle));
                if(!p1.isEmpty() && !p3.isEmpty() && !p2.isEmpty() && !p4.isEmpty() ) {
                    callApiPin(p1 , p2 , p3 , p4);
                }
                    if(s.length()==0)
                    {
                        et3.requestFocus();
                        et4.setBackground(getDrawable(R.drawable.circle_2));
                    }
                }

        });


    }

    private void callApiPin(String p1, String p2, String p3, String p4) {
        String pin = p1+p2+p3+p4;
            if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
                Dialog dialog = ViewUtils.getProgressBar(PinEnterActivity.this);
                AppCommon.getInstance(this).setNonTouchableFlags(this);
                AppService apiService = ServiceGenerator.createService(AppService.class , AppCommon.getInstance(this).getToken());
                Call call = apiService.pinApi(new PinEntitiy(pin));
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        AppCommon.getInstance(PinEnterActivity.this).clearNonTouchableFlags(PinEnterActivity.this);
                        dialog.dismiss();
                        PinResponse authResponse = (PinResponse) response.body();
                        if (authResponse != null) {
                            Log.i("Response::", new Gson().toJson(authResponse));
                            if (authResponse.getCode() == 200) {
                               startActivity(new Intent(PinEnterActivity.this , HomeActivity.class));
                               finishAffinity();
                            } else {
                                et1.setText("");
                                et2.setText("");
                                et3.setText("");
                                et4.setText("");
                                et1.requestFocus();
                                Toast.makeText(PinEnterActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            AppCommon.getInstance(PinEnterActivity.this).showDialog(PinEnterActivity.this, "Server Error");
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        dialog.dismiss();
                        AppCommon.getInstance(PinEnterActivity.this).clearNonTouchableFlags(PinEnterActivity.this);
                        // loaderView.setVisibility(View.GONE);
                        Toast.makeText(PinEnterActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                // no internet
                Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
            }


    }

    public void forgotPin(View view) {
        callsendOtp();
        startActivity(new Intent(this , ForgotPin.class));
    }

    private void callsendOtp() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(PinEnterActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class , AppCommon.getInstance(this).getToken());
            Call call = apiService.SendOTP_FOR_PIN(new OtpEnitity(AppCommon.getInstance(this).getUserId()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(PinEnterActivity.this).clearNonTouchableFlags(PinEnterActivity.this);
                    dialog.dismiss();
                    OptResponse authResponse = (OptResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(PinEnterActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PinEnterActivity.this , ForgotPin.class));
                        } else {
                            Toast.makeText(PinEnterActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(PinEnterActivity.this).showDialog(PinEnterActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(PinEnterActivity.this).clearNonTouchableFlags(PinEnterActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(PinEnterActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.logoutImage , R.id.logoutText})
    void logout(){
        showAlertDialog();
    }
    private void showAlertDialog() {


        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getResources().getString(R.string.app_name));
        adb.setIcon(R.mipmap.ic_launcher_round);
        adb.setMessage(getResources().getString(R.string.r_u_sure_logout_message));
        adb.setPositiveButton(getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppCommon.getInstance(PinEnterActivity.this).clearPreference();
                AppCommon.getInstance(PinEnterActivity.this).setUserLogin(AppCommon.getInstance(PinEnterActivity.this).getUserId(), false);
                startActivity(new Intent(PinEnterActivity.this, SelectionPage.class));
                finishAffinity();
                Toast.makeText(PinEnterActivity.this, getString(R.string.logout_success),
                        Toast.LENGTH_SHORT).show();
                //startActivity(new Intent());
                // finishAffinity();
            }

        });
        adb.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.show();


    }
}

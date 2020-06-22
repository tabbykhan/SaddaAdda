package com.imuons.saddaadda.View;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.ProfileDataModel;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.OptResponse;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.responseModel.UpdateProfileResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etMobile)
    EditText etMobile;
    @BindView(R.id.etGooglePay)
    EditText etGooglePay;
    @BindView(R.id.etPhonePay)
    EditText etPhonePay;
    @BindView(R.id.et_paytm_no)
    EditText et_paytm_no;
    @BindView(R.id.etAccountName)
    EditText etAccountName;
    @BindView(R.id.etIFSC)
    EditText etIFSC;
    @BindView(R.id.etBranchName)
    EditText etBranchName;
    @BindView(R.id.etAccountNo)
    EditText etAccountNo;
    @BindView(R.id.submitBtn)
    TextView submitBtn;
    @BindView(R.id.txUserId)
    TextView txUserId;
    @BindView(R.id.logOut)
    TextView logOut;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getUserProfileInfo();
    }

    @OnClick(R.id.txt_changePin)
    void changePin() {
        callsendOtp(false);
    }

    @OnClick(R.id.txt_change_password)
    void changePwd() {
        callsendOtp(true);
    }

    @OnClick(R.id.logOut)
    void logout() {
        showAlertDialog();
    }

    @OnClick(R.id.submitBtn)
    void updateCall() {
        String name = etName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String googlePay = etGooglePay.getText().toString().trim();
        String phonePay = etPhonePay.getText().toString().trim();
        String accountName = etAccountName.getText().toString().trim();
        String ifsc = etIFSC.getText().toString().trim();
        String branch = etBranchName.getText().toString().trim();
        String accountNo = etAccountNo.getText().toString().trim();
        String paytm_no = et_paytm_no.getText().toString().trim();

        updateProfile(name, mobile, googlePay, phonePay, accountName, ifsc, branch, accountNo, paytm_no);
    }

    private void updateProfile(String name, String mobile, String googlePay, String phonePay, String accountName, String ifsc, String branch, String accountNo, String paytm_no) {

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ProfileActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.UPDATE_PROFILE_RESPONSE_CALL(new UpdateProfileEntity(name,
                    mobile, accountNo, accountName, branch, ifsc, googlePay, phonePay, paytm_no));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ProfileActivity.this).clearNonTouchableFlags(ProfileActivity.this);
                    dialog.dismiss();
                    UpdateProfileResponse authResponse = (UpdateProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(ProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            getUserProfileInfo();
                        } else {
                            Toast.makeText(ProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ProfileActivity.this).showDialog(ProfileActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ProfileActivity.this).clearNonTouchableFlags(ProfileActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }


    }

    private void getUserProfileInfo() {
        if (AppCommon.getInstance(getApplicationContext()).isConnectingToInternet(getApplicationContext())) {
            AppCommon.getInstance(getApplicationContext()).setNonTouchableFlags(ProfileActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getApplicationContext()).getToken());
            Dialog dialog = ViewUtils.getProgressBar(ProfileActivity.this);
            Call call = apiService.Get_ProfileInfo();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(ProfileActivity.this);
                    dialog.dismiss();
                    ProfileGetResponse authResponse = (ProfileGetResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ResponseProfile::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setProfileInfo(authResponse.getData());
                            // Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getApplicationContext()).showDialog(ProfileActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ProfileActivity.this).clearNonTouchableFlags(ProfileActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setProfileInfo(ProfileDataModel data) {



        if (data.getFullname() !=null){
            etName.setText(String.valueOf(data.getFullname()));
        }else {
            etName.setText("");
        }
        if (data.getMobile() !=null){
            etMobile.setText(String.valueOf(data.getMobile()));
        }else {
            etMobile.setText("");
        }

        if (data.getTezNo() != null) {
            etGooglePay.setText(data.getTezNo());
        } else {
            etGooglePay.setText("");
        }
        if (data.getPhonepeNo() != null) {
            etPhonePay.setText(data.getPhonepeNo());
        } else {
            etPhonePay.setText("");
        }
        if (data.getPaytm_no() != null) {
            et_paytm_no.setText(data.getPaytm_no());
        } else {
            et_paytm_no.setText("");
        }

        if (data.getBankName() != null) {
            etAccountName.setText(data.getBankName());

        } else {
            etAccountName.setText("");
        }
        if (data.getAccountNo() != null) {
            etAccountNo.setText(data.getAccountNo());

        } else {
            etAccountNo.setText("");
        }
        if (data.getIfscCode() != null) {
            etIFSC.setText(data.getIfscCode());

        } else {
            etIFSC.setText("");

        }
        if (data.getBranchName() != null) {
            etBranchName.setText(data.getBranchName());

        } else {
            etBranchName.setText("");

        }

        userID = String.valueOf(data.getUserId());
        txUserId.setText(String.valueOf(data.getUserId()));

        if (data.getFullname() == null || data.getFullname().equals("")) {
            etName.setEnabled(true);
        } else {
            etName.setCompoundDrawables(null, null, null, null);
            etName.setEnabled(false);
        }if (data.getMobile() == null || data.getMobile().equals("")) {
            etMobile.setEnabled(true);
        } else {
            etMobile.setEnabled(false);
            etMobile.setCompoundDrawables(null, null, null, null);
        }
        if (data.getIfscCode() == null || data.getIfscCode().equals("")) {
            etIFSC.setEnabled(true);
        } else {
            etIFSC.setEnabled(false);
            etIFSC.setCompoundDrawables(null, null, null, null);
        }

        if (data.getTezNo() == null || data.getTezNo().equals("")) {

            etGooglePay.setEnabled(true);
        } else {
            etGooglePay.setEnabled(false);
            etGooglePay.setCompoundDrawables(null, null, null, null);
        }

        if (data.getPhonepeNo() == null || data.getPhonepeNo().equals("")) {

            etPhonePay.setEnabled(true);
        } else {
            etPhonePay.setEnabled(false);
            etPhonePay.setCompoundDrawables(null, null, null, null);
        }

        if (data.getPaytm_no() == null || data.getPaytm_no().equals("")) {

            et_paytm_no.setEnabled(true);
        } else {
            et_paytm_no.setEnabled(false);
            et_paytm_no.setCompoundDrawables(null, null, null, null);
        }

        if (data.getBankName() == null || data.getBankName().equals("")) {

            etAccountName.setEnabled(true);

        } else {
            etAccountName.setEnabled(false);
            etAccountName.setCompoundDrawables(null, null, null, null);
        }

        if (data.getAccountNo() == null || data.getAccountNo().equals("")) {

            etAccountNo.setEnabled(true);

        } else {
            etAccountNo.setEnabled(false);
            etAccountNo.setCompoundDrawables(null, null, null, null);
        }

        if (data.getBranchName() == null || data.getBranchName().equals("")) {
            etBranchName.setEnabled(true);
        } else {
            etBranchName.setEnabled(false);
            etBranchName.setCompoundDrawables(null, null, null, null);
        }


    }

    private void showAlertDialog() {


        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getResources().getString(R.string.app_name));
        adb.setIcon(R.mipmap.ic_launcher_round);
        adb.setMessage("Are you sure you want to Logout?");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppCommon.getInstance(ProfileActivity.this).clearPreference();
                startActivity(new Intent(ProfileActivity.this, SelectionPage.class));
                finishAffinity();
                Toast.makeText(ProfileActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent());
                // finishAffinity();
            }

        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adb.show();

       /*
        AlertDialog.Builder builder1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            builder1 = new AlertDialog.Builder(ProfileActivity.this, AlertDialog.THEME_HOLO_LIGHT);
        }
        builder1.setTitle("Alert");
        builder1.setMessage("Are you sure you want to Logout ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        ProfileActivity.this.finish();
                        SharedPreferenceUtils.clearPreferences(ProfileActivity.this);
                        SharedPreferenceUtils.clearID(ProfileActivity.this);
                        SharedPreferenceUtils.clearAccess_Token(ProfileActivity.this);
                        SharedPreferenceUtils.storeSplash(ProfileActivity.this, "stop");
                        AppCommon.getInstance(ProfileActivity.this).clearPreference();

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();*/
    }

    public void changePin(View view) {
        //  callsendOtp();
    }

    private void callsendOtp(boolean flag) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ProfileActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.SendOTP_FOR_PIN(new OtpEnitity(AppCommon.getInstance(this).getUserId()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ProfileActivity.this).clearNonTouchableFlags(ProfileActivity.this);
                    dialog.dismiss();
                    OptResponse authResponse = (OptResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(ProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            if (flag) {
                                startActivity(new Intent(ProfileActivity.this, ChangePin.class));
                            } else {
                                startActivity(new Intent(ProfileActivity.this, ChangePassword.class));
                            }

                        } else {
                            Toast.makeText(ProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ProfileActivity.this).showDialog(ProfileActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ProfileActivity.this).clearNonTouchableFlags(ProfileActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.share)
    void shareImage() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            //String shareMessage= "\nLet me recommend you this application\n\n";
            // shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            String shareMessage = "Follow me on Sadda Adda and check out my latest updates! Tap \n" + "https://www.saddaadda.games/saddadda.apk " + "=" + userID + " to head to my profile now";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));

        } catch (Exception e) {
            //e.toString();
        }
    }

}


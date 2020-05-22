package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.ProfileDataModel;
import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.EntityClass.RegitrationEntity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.responseModel.RandomUserIdResponse;
import com.imuons.saddaadda.responseModel.RegisterResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getUserProfileInfo();
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

        updateProfile(name, mobile, googlePay, phonePay, accountName, ifsc, branch, accountNo);
    }

    private void updateProfile(String name, String mobile, String googlePay, String phonePay, String accountName, String ifsc, String branch, String accountNo) {

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ProfileActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.UPDATE_PROFILE_RESPONSE_CALL(new UpdateProfileEntity(name, mobile, accountNo, accountName, branch, ifsc, googlePay, phonePay));
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
        etName.setText(String.valueOf(data.getFullname()));
        etMobile.setText(String.valueOf(data.getMobile()));
        etGooglePay.setText(String.valueOf(data.getTezNo()));
        etPhonePay.setText(String.valueOf(data.getPhonepeNo()));
        etAccountName.setText(String.valueOf(data.getBankName()));
        etAccountNo.setText(String.valueOf(data.getAccountNo()));
        etIFSC.setText(String.valueOf(data.getIfscCode()));
        etBranchName.setText(String.valueOf(data.getBranchName()));
        txUserId.setText(String.valueOf(data.getUserId()));

    }
}

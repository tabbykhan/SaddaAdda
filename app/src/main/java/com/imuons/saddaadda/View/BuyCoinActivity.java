package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.EntityClass.BuyCoinEntity;
import com.imuons.saddaadda.EntityClass.LoginEntity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.BuyCoinResponse;
import com.imuons.saddaadda.responseModel.UpdateProfileResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyCoinActivity extends AppCompatActivity {

    @BindView(R.id.submitBtn)
    TextView tv_signup;

    @BindView(R.id.etAmount)
    EditText etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coin);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submitBtn)
    void submitCall() {
        String amount = etAmount.getText().toString().trim();
       
        if (amount.isEmpty()) {
            etAmount.setError("Please enter Amount");
        } else
            callLBuyCoinApi(new BuyCoinEntity(amount));

    }

    private void callLBuyCoinApi(BuyCoinEntity buyCoinEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.buyCoin(buyCoinEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    dialog.dismiss();
                    BuyCoinResponse buyCoinResponse = (BuyCoinResponse) response.body();
                    if (buyCoinResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(buyCoinResponse));
                        if (buyCoinResponse.getCode() == 200) {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(BuyCoinActivity.this, buyCoinResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyCoinActivity.this).showDialog(BuyCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(BuyCoinActivity.this).clearNonTouchableFlags(BuyCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

}

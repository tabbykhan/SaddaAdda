package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.CoinsDataModel;
import com.imuons.saddaadda.EntityClass.BuyCoinEntity;
import com.imuons.saddaadda.EntityClass.FundTransEntity;
import com.imuons.saddaadda.EntityClass.SellCoinEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;

import com.imuons.saddaadda.responseModel.CoinsResponseModel;
import com.imuons.saddaadda.responseModel.FundTransferResponse;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.responseModel.SellResponseModel;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellCoinActivity extends AppCompatActivity {

    @BindView(R.id.submitBtn)
    TextView tv_signup;

    @BindView(R.id.txtAvailableAmount)
    TextView txtAvailableAmount;
    @BindView(R.id.top_up_wallet_balance)
    TextView top_up_wallet_balance;

    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.etAmountTrans)
    EditText etAmountTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_coin);
        ButterKnife.bind(this);
        getUserCoinsInfo();
    }
        @OnClick(R.id.transferBtn)
        void transfercall(){
            String amount = etAmountTrans.getText().toString().trim();
            if (amount.isEmpty()) {
                etAmountTrans.setError("Please enter Amount");
            } else
                callFundTransferApi(new FundTransEntity(amount));

        }
    @OnClick(R.id.submitBtn)
    void submitCall() {
        String amount = etAmount.getText().toString().trim();

        if (amount.isEmpty()) {
            etAmount.setError("Please enter Amount");
        } else
            callSellCoinApi(new SellCoinEntity(amount));

    }

    private void callSellCoinApi(SellCoinEntity buyCoinEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(SellCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.sellCoin(buyCoinEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SellCoinActivity.this).clearNonTouchableFlags(SellCoinActivity.this);
                    dialog.dismiss();
                    SellResponseModel sellResponse = (SellResponseModel) response.body();
                    if (sellResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(sellResponse));
                        if (sellResponse.getCode() == 200) {
                            Toast.makeText(SellCoinActivity.this, sellResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            etAmount.setText("");
                            getUserCoinsInfo();
                        } else {
                            Toast.makeText(SellCoinActivity.this, sellResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SellCoinActivity.this).showDialog(SellCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(SellCoinActivity.this).clearNonTouchableFlags(SellCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SellCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void getUserCoinsInfo() {
        if (AppCommon.getInstance(getApplicationContext()).isConnectingToInternet(getApplicationContext())) {
            AppCommon.getInstance(getApplicationContext()).setNonTouchableFlags(SellCoinActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getApplicationContext()).getToken());
            Dialog dialog = ViewUtils.getProgressBar(SellCoinActivity.this);
            Call call = apiService.get_Coins_Details();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(SellCoinActivity.this);
                    dialog.dismiss();
                    CoinsResponseModel authResponse = (CoinsResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("ResponseCoins::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setProfileInfo(authResponse.getData());
                            // Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getApplicationContext()).showDialog(SellCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(SellCoinActivity.this).clearNonTouchableFlags(SellCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setProfileInfo(CoinsDataModel data) {
        if (data.getWinningWallet() != null) {
            txtAvailableAmount.setText(String.valueOf(data.getWinningWallet()));
        } else {
            txtAvailableAmount.setText("0");
        }
        if (data.getPurchaseWallet() != null) {
            top_up_wallet_balance.setText(String.valueOf(data.getPurchaseWallet()));
        } else {
            top_up_wallet_balance.setText("0");
        }
    }


    //fund Transfer Api

    private void callFundTransferApi(FundTransEntity fundTransEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(SellCoinActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.FUND_TRANSFER_RESPONSE_CALL(fundTransEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SellCoinActivity.this).clearNonTouchableFlags(SellCoinActivity.this);
                    dialog.dismiss();
                    FundTransferResponse sellResponse = (FundTransferResponse) response.body();
                    if (sellResponse != null) {
                        Log.i("ResponseUpdate::", new Gson().toJson(sellResponse));
                        if (sellResponse.getCode() == 200) {
                            Toast.makeText(SellCoinActivity.this, sellResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            etAmountTrans.setText("");
                            getUserCoinsInfo();
                        } else {
                            Toast.makeText(SellCoinActivity.this, sellResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SellCoinActivity.this).showDialog(SellCoinActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(SellCoinActivity.this).clearNonTouchableFlags(SellCoinActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SellCoinActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

}

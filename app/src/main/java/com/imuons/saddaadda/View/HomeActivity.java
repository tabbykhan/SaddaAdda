package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.DashboardData;
import com.imuons.saddaadda.DataModel.ProfileDataModel;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.ReportActivity;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.DashboardResponse;
import com.imuons.saddaadda.responseModel.ProfileGetResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Activity {
    @BindView(R.id.gotoProfile)
    RelativeLayout gotoProfile;
    @BindView(R.id.txUserId)
    TextView txUserId;
    @BindView(R.id.coin)
    TextView coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

    }

    private void getDashboardInfo() {
        if (AppCommon.getInstance(getApplicationContext()).isConnectingToInternet(getApplicationContext())) {
            AppCommon.getInstance(getApplicationContext()).setNonTouchableFlags(HomeActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getApplicationContext()).getToken());
            Dialog dialog = ViewUtils.getProgressBar(HomeActivity.this);
            Call call = apiService.Get_DashboardInfo();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getApplicationContext()).clearNonTouchableFlags(HomeActivity.this);
                    dialog.dismiss();
                    DashboardResponse authResponse = (DashboardResponse) response.body();
                    if (authResponse != null) {
                        Log.i("CategoryResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setDashboardData(authResponse.getData());
                            // Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getApplicationContext()).showDialog(HomeActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(HomeActivity.this).clearNonTouchableFlags(HomeActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(getApplicationContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDashboardData(DashboardData data) {
        txUserId.setText(String.valueOf(data.getUserId()));
        coin.setText(String.valueOf(data.getWalletBalance()));

    }

    @OnClick(R.id.gotoProfile)
    void goToProfile() {

        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void sevenClick(View view) {
        startActivity(new Intent(this, SevenUpDown.class));
    }

    public void ReportClick(View view) {
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
       // startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDashboardInfo();
    }
}

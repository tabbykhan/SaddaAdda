package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.ReportData;
import com.imuons.saddaadda.EntityClass.OtpEnitity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.ReportAdapter;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.ReportResponse;
import com.imuons.saddaadda.responseModel.VerifyUserResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    ReportAdapter reportAdapter;

    ArrayList<ReportData> reportData;
    @BindView(R.id.txUserId)
    TextView txUserId;
    @BindView(R.id.coin)
    TextView coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        coin.setText(String.valueOf(AppCommon.getInstance(this).getAccount()));
        txUserId.setText(String.valueOf(AppCommon.getInstance(this).getUserId()));
        reportData = new ArrayList<>();
        reportAdapter = new ReportAdapter(this , reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);

        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(reportAdapter);
        CallApiForReport();
                
    }

    private void CallApiForReport() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ReportActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.REPORT_CALL();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ReportActivity.this).clearNonTouchableFlags(ReportActivity.this);
                   dialog.dismiss();
                    ReportResponse authResponse = (ReportResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            reportData = authResponse.getData();
                            if(reportData.size() !=0) {
                                reportData.add(0 ,new ReportData());
                                reportAdapter.update(reportData);
                            }
                        } else {
                             Toast.makeText(ReportActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                         AppCommon.getInstance(ReportActivity.this).showDialog(ReportActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ReportActivity.this).clearNonTouchableFlags(ReportActivity.this);
                    // loaderView.setVisibility(View.GONE);
                     Toast.makeText(ReportActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkOpen(int adapterPosition) {
        if(reportData.get(adapterPosition).isOpen()){
            reportData.get(adapterPosition).setOpen(false);
        }else {
            reportData.get(adapterPosition).setOpen(true);
        }
        reportAdapter.update(reportData , adapterPosition);
    }
}

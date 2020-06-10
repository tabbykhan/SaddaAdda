package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.ReportData;
import com.imuons.saddaadda.DataModel.SellHistoryData;
import com.imuons.saddaadda.DataModel.SellRecord;
import com.imuons.saddaadda.DataModel.TransRecord;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.ReportAdapter;
import com.imuons.saddaadda.SellReportAdapter;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.ReportResponse;
import com.imuons.saddaadda.responseModel.SellHistoryReport;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellHistoryReportActivity extends AppCompatActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    //ReportAdapter reportAdapter;
    SellReportAdapter reportAdapter;

    ArrayList<SellRecord> reportData;
    int offsetLevel = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_history_report);
        ButterKnife.bind(this);
        reportData = new ArrayList<>();
        reportAdapter = new SellReportAdapter(this, reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);

        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(reportAdapter);
        CallApiForReport(0);

    }

        private void CallApiForReport(int start) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = null;
            if (start == 0)
                dialog = ViewUtils.getProgressBar(SellHistoryReportActivity.this);
            else
                dialog = ViewUtils.getBottomProgress(SellHistoryReportActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Map<String, String> roiMap = new HashMap<>();
            roiMap.put("provide", "1");
            roiMap.put("start", "0");
            roiMap.put("start", String.valueOf(start));
            roiMap.put("length", "20");
            Call call = apiService.SellREPORT_CALL(roiMap);
            Dialog finalDialog = dialog;
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SellHistoryReportActivity.this).clearNonTouchableFlags(SellHistoryReportActivity.this);
                    finalDialog.dismiss();
                    SellHistoryReport authResponse = (SellHistoryReport) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                            //  reportData = authResponse.getData().getRecords();
                          /*  if(reportData.size() !=0) {
                                reportData.add(0 ,new SellRecord());
                                reportAdapter.update(reportData);
                            }*/

                        } else {
                            Toast.makeText(SellHistoryReportActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SellHistoryReportActivity.this).showDialog(SellHistoryReportActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    finalDialog.dismiss();
                    AppCommon.getInstance(SellHistoryReportActivity.this).clearNonTouchableFlags(SellHistoryReportActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SellHistoryReportActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(SellHistoryData data) {
        if (reportData.size() != 0)
            reportData.addAll(data.getRecords());
        else {
            reportData = data.getRecords();
            reportData.add(0, new SellRecord());
        }
        reportAdapter.updateList(reportData, offsetLevel);
    }

    public void checkOpen(int adapterPosition) {
        if (reportData.get(adapterPosition).isOpen()) {
            reportData.get(adapterPosition).setOpen(false);
        } else {
            reportData.get(adapterPosition).setOpen(true);
        }
        reportAdapter.update(reportData, adapterPosition);
    }

    public void callapi(int position) {
        offsetLevel = offsetLevel + 1;
        CallApiForReport(reportData.size());
    }
}

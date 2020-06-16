package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.BuyRecord;
import com.imuons.saddaadda.DataModel.RecordData;
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
import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.sp_companies)
    Spinner gameTypeSppiner;
    int offsetLevel = 0;
    int selectedPos =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        reportData = new ArrayList<>();
        reportAdapter = new ReportAdapter(this, reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(reportAdapter);
        gameTypeSppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reportData.clear();
                reportAdapter.update(reportData);
                selectedPos = position;
                CallApiForReport(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void CallApiForReport( int start) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = null;
            if(start == 0)
                dialog = ViewUtils.getProgressBar(ReportActivity.this);
            else
                dialog = ViewUtils.getBottomProgress(ReportActivity.this);

            AppService apiService = ServiceGenerator.createService(AppService.class , AppCommon.getInstance(this).getToken());
            Map<String, String> roiMap = new HashMap<>();
            if(selectedPos == 0)
            roiMap.put("table_name", "zero_to_nine");
            else if(selectedPos == 1)
                roiMap.put("table_name", "seven_up_down");
            else
                roiMap.put("table_name", "damdar_shatak");
            roiMap.put("start", String.valueOf(start));
            roiMap.put("length", "20");
            Call call = apiService.REPORT_CALL(roiMap);
            Dialog finalDialog = dialog;
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ReportActivity.this).clearNonTouchableFlags(ReportActivity.this);
                    finalDialog.dismiss();
                    ReportResponse authResponse = (ReportResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());

                        } else {
                            Toast.makeText(ReportActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ReportActivity.this).showDialog(ReportActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    finalDialog.dismiss();
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

    private void setData(RecordData data) {
       // reportData = data.getRecords();
        if (reportData.size()!=0)
            reportData.addAll(data.getRecords());
        else {
            reportData = data.getRecords();
            reportData.add(0, new ReportData());
        }
        reportAdapter.updateList(reportData , offsetLevel);

        /* reportData = authResponse.getData().getRecords();
                            if (reportData.size() != 0) {
                                reportData.add(0, new ReportData());
                                reportAdapter.update(reportData);
                            }*/
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
        offsetLevel = offsetLevel+1;
        CallApiForReport( reportData.size());
    }
}

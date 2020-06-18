package com.imuons.saddaadda.View;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.DirectRecord;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.DirectIncomeReportAdapter;
import com.imuons.saddaadda.responseModel.DirectReport;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeDirectReport extends Activity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.glowingText)
    TextView glowingText;
    DirectIncomeReportAdapter directIncomeReportAdapter;
    ArrayList<DirectRecord> directRecordArrayList;
    int type = 0;
    // 0: 0-9 game , 1: 7-up , 2 shatak

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_direct);
        ButterKnife.bind(this);
        glowingText.setText("");
        if (getIntent() != null) {
            String titile = getIntent().getStringExtra("title");
            glowingText.setText(titile);
            type = getIntent().getIntExtra("type" , 0);
        }
        directRecordArrayList = new ArrayList<>();
        directIncomeReportAdapter = new DirectIncomeReportAdapter(this, directRecordArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(directIncomeReportAdapter);
        callDirectReport();
    }

    private void callDirectReport() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog finalDialog = ViewUtils.getProgressBar(IncomeDirectReport.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());

            Call call = null;
            if(type ==0)
               call = apiService.GetDirectIncome();
            else if(type ==1)
               call= apiService.Seven_DIRECT_REPORT_CALL();
              else
                call =  apiService.Satak_DIRECT_REPORT_CALL();

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(IncomeDirectReport.this).clearNonTouchableFlags(IncomeDirectReport.this);
                    finalDialog.dismiss();
                    DirectReport authResponse = (DirectReport) response.body();
                    if (authResponse != null) {
                        Log.i("direct income::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData().getRecords());
                        } else {
                            Toast.makeText(IncomeDirectReport.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(IncomeDirectReport.this).showDialog(IncomeDirectReport.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    finalDialog.dismiss();
                    AppCommon.getInstance(IncomeDirectReport.this).clearNonTouchableFlags(IncomeDirectReport.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(IncomeDirectReport.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(ArrayList<DirectRecord> records) {
        directRecordArrayList = records;
        directRecordArrayList.add(0 ,new DirectRecord());
        directIncomeReportAdapter.updateRecord(directRecordArrayList);
    }

    public void checkOpen(int adapterPosition) {
        if (directRecordArrayList.get(adapterPosition).isOpen()) {
            directRecordArrayList.get(adapterPosition).setOpen(false);
        } else {
            directRecordArrayList.get(adapterPosition).setOpen(true);
        }
        directIncomeReportAdapter.update(directRecordArrayList, adapterPosition);
    }
}

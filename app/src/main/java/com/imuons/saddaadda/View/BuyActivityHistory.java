package com.imuons.saddaadda.View;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.BuyHistoryData;
import com.imuons.saddaadda.DataModel.BuyRecord;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.BuyReportAdapter;
import com.imuons.saddaadda.responseModel.BuyHistoryResponse;
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

public class BuyActivityHistory extends AppCompatActivity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    //SellReportAdapter reportAdapter;
    BuyReportAdapter reportAdapter;

    ArrayList<BuyRecord> reportData;

    int offsetLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_history_report);
        ButterKnife.bind(this);
        reportData = new ArrayList<>();
        reportAdapter = new BuyReportAdapter(this, reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);

        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(reportAdapter);
        CallApiForReport(0);

    }

    private void CallApiForReport(int start) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = null;
            if(start == 0)
                 dialog = ViewUtils.getProgressBar(BuyActivityHistory.this);
            else
                dialog = ViewUtils.getBottomProgress(BuyActivityHistory.this);

            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Map<String, String> roiMap = new HashMap<>();
            roiMap.put("provide", "1");
            roiMap.put("start", String.valueOf(start));
            roiMap.put("length", "20");
            Call call = apiService.BuyREPORT_CALL(roiMap);
            Dialog finalDialog = dialog;
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyActivityHistory.this).clearNonTouchableFlags(BuyActivityHistory.this);
                    finalDialog.dismiss();
                    BuyHistoryResponse authResponse = (BuyHistoryResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if (authResponse.getCode() == 200) {
                                setData(authResponse.getData());
                            } else {
                                if(reportData.size() == 0)
                                    reportAdapter.updateList(reportData , 0);
                            /*if (finalIsupdate)
                            Toast.makeText(MyTeamFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();*/
                            }
                        } else {
                            Toast.makeText(BuyActivityHistory.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BuyActivityHistory.this).showDialog(BuyActivityHistory.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    finalDialog.dismiss();
                    AppCommon.getInstance(BuyActivityHistory.this).clearNonTouchableFlags(BuyActivityHistory.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BuyActivityHistory.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(BuyHistoryData data) {
//reportData.add(0, new BuyRecord());
        if (reportData.size()!=0)
            reportData.addAll(data.getRecords());
        else {
            reportData = data.getRecords();
            reportData.add(0, new BuyRecord());
        }
        reportAdapter.updateList(reportData , offsetLevel);
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

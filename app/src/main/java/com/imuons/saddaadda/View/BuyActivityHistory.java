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
import com.imuons.saddaadda.DataModel.BuyRecord;
import com.imuons.saddaadda.DataModel.SellRecord;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.SellReportAdapter;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.BuyHistoryResponse;
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

public class BuyActivityHistory extends Activity {

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    //SellReportAdapter reportAdapter;
   BuyReportAdapter reportAdapter;

    ArrayList<BuyRecord> reportData;
    @BindView(R.id.txUserId)
    TextView txUserId;
    @BindView(R.id.coin)
    TextView coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_history_report);
        ButterKnife.bind(this);
        coin.setText(String.valueOf(AppCommon.getInstance(this).getAccount()));
        txUserId.setText(String.valueOf(AppCommon.getInstance(this).getUserId()));
        reportData = new ArrayList<>();
        reportAdapter = new BuyReportAdapter(this, reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);

        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(reportAdapter);
        CallApiForReport();

    }

    private void CallApiForReport() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(BuyActivityHistory.this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Map<String, String> roiMap = new HashMap<>();

            roiMap.put("provide", "1");
            roiMap.put("start", "0");
            Call call = apiService.BuyREPORT_CALL(roiMap);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BuyActivityHistory.this).clearNonTouchableFlags(BuyActivityHistory.this);
                    dialog.dismiss();
                    BuyHistoryResponse authResponse = (BuyHistoryResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            reportData = authResponse.getData().getRecords();
                            if(reportData.size() !=0) {
                                reportData.add(0 ,new BuyRecord());
                                reportAdapter.update(reportData);
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
                    dialog.dismiss();
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

    public void checkOpen(int adapterPosition) {
        if (reportData.get(adapterPosition).isOpen()) {
            reportData.get(adapterPosition).setOpen(false);
        } else {
            reportData.get(adapterPosition).setOpen(true);
        }
        reportAdapter.update(reportData, adapterPosition);
    }

}

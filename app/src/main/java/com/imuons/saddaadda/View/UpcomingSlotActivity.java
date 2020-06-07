package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.ReportData;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.UpcomingSlotAdapter;
import com.imuons.saddaadda.responseModel.ReportResponse;
import com.imuons.saddaadda.responseModel.UpcomingSlotResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingSlotActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recycleView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    UpcomingSlotAdapter upcomingSlotAdapter;
    ArrayList<UpcomingSlotData> reportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_slot);
        ButterKnife.bind(this);
        reportData = new ArrayList<>();
        upcomingSlotAdapter = new UpcomingSlotAdapter(this, reportData);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setAdapter(upcomingSlotAdapter);
        CallApiUpcomingSlot();
    }

    @OnClick(R.id.leaderBoard)
    void learderBoard() {
        startActivity(new Intent(this, LeaderBoardActivity.class));
      //  Toast.makeText(this, "Work In progress", Toast.LENGTH_SHORT).show();


    }

    private void CallApiUpcomingSlot() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(UpcomingSlotActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.UPCOMING_SLOT_RESPONSE_CALL();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(UpcomingSlotActivity.this).clearNonTouchableFlags(UpcomingSlotActivity.this);
                    dialog.dismiss();
                    UpcomingSlotResponse authResponse = (UpcomingSlotResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            reportData = authResponse.getData().getRecords();
                            if (reportData.size() != 0) {
                                upcomingSlotAdapter.update(reportData);
                            }
                        } else {
                            Toast.makeText(UpcomingSlotActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(UpcomingSlotActivity.this).showDialog(UpcomingSlotActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(UpcomingSlotActivity.this).clearNonTouchableFlags(UpcomingSlotActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(UpcomingSlotActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void Open(int adapterPosition) {
        reportData.get(adapterPosition).getSlotNo();
        upcomingSlotAdapter.update(reportData, adapterPosition);
        startActivity(new Intent(this, DusKaDamActivity.class));

    }
}

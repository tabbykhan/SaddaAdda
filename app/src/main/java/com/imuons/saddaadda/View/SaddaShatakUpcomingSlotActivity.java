package com.imuons.saddaadda.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.SaddaShatakUpcomingSlotAdapter;
import com.imuons.saddaadda.responseModel.UpcomingSlotResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaddaShatakUpcomingSlotActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recycleView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    SaddaShatakUpcomingSlotAdapter upcomingSlotAdapter;
    ArrayList<UpcomingSlotData> reportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_slot);
        ButterKnife.bind(this);
        reportData = new ArrayList<>();
        upcomingSlotAdapter = new SaddaShatakUpcomingSlotAdapter(this, reportData);

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
            Dialog dialog = ViewUtils.getProgressBar(SaddaShatakUpcomingSlotActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Map<String, Object>paparam = new HashMap<>();
            paparam.put("start", 0);
            paparam.put("length",10);
            Call call = apiService.GetShatakUpcomingSolt(paparam);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SaddaShatakUpcomingSlotActivity.this).clearNonTouchableFlags(SaddaShatakUpcomingSlotActivity.this);
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
                            Toast.makeText(SaddaShatakUpcomingSlotActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SaddaShatakUpcomingSlotActivity.this).showDialog(SaddaShatakUpcomingSlotActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(SaddaShatakUpcomingSlotActivity.this).clearNonTouchableFlags(SaddaShatakUpcomingSlotActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SaddaShatakUpcomingSlotActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        CallApiUpcomingSlot();
    }
}

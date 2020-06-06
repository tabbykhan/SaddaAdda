package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.CompleteSlotRecord;
import com.imuons.saddaadda.EntityClass.CompleteSlotEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.LeaderBoardAdapter;
import com.imuons.saddaadda.adapters.UpcomingSlotAdapter;
import com.imuons.saddaadda.responseModel.CompleteSlotResponse;
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

public class LeaderBoardActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recycleView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    LeaderBoardAdapter leaderBoardAdapter;
    ArrayList<CompleteSlotRecord> reportData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        ButterKnife.bind(this);
        reportData = new ArrayList<>();
        leaderBoardAdapter = new LeaderBoardAdapter(this, reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setAdapter(leaderBoardAdapter);
        CallApiCompleteSlot();
    }

    private void CallApiCompleteSlot() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(LeaderBoardActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.COMPLETE_SLOT_RESPONSE_CALL(new CompleteSlotEntity("06 June 2020"));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LeaderBoardActivity.this).clearNonTouchableFlags(LeaderBoardActivity.this);
                    dialog.dismiss();
                    CompleteSlotResponse authResponse = (CompleteSlotResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            reportData = authResponse.getData().getRecords();
                           /* if (reportData.size() != 0) {
                                leaderBoardAdapter.update(reportData);
                            }*/
                            setAdapter(authResponse.getData().getRecords());
                            Toast.makeText(LeaderBoardActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LeaderBoardActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(LeaderBoardActivity.this).showDialog(LeaderBoardActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(LeaderBoardActivity.this).clearNonTouchableFlags(LeaderBoardActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(LeaderBoardActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<CompleteSlotRecord> records) {
        LeaderBoardAdapter leaderBoardAdapter = new LeaderBoardAdapter(this, records);
        recycleView.setAdapter(leaderBoardAdapter);
    }
}

package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.CompleteSlotRecord;
import com.imuons.saddaadda.DataModel.WinningDataModel;
import com.imuons.saddaadda.DataModel.WinningDateRecord;
import com.imuons.saddaadda.EntityClass.CompleteSlotEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.LeaderBoardAdapter;
import com.imuons.saddaadda.adapters.UpcomingSlotAdapter;
import com.imuons.saddaadda.adapters.WinningDateAdapter;
import com.imuons.saddaadda.responseModel.CompleteSlotResponse;
import com.imuons.saddaadda.responseModel.UpcomingSlotResponse;
import com.imuons.saddaadda.responseModel.WinningDateResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class LeaderBoardActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recycleView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.windate)
    EditText windate;
    LeaderBoardAdapter leaderBoardAdapter;
    WinningDateAdapter winningDateAdapter;
    ArrayList<CompleteSlotRecord> reportData;

    String[] winDate;

    ArrayAdapter<WinningDateRecord> adapter;
    ArrayList<WinningDateRecord> levelDataArrayList;

    String level, getdatecode;
    

    ListPopupWindow datelistPopupWindow;
    List<String> listDateName = new ArrayList<>();
    private List<WinningDateRecord> datelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        ButterKnife.bind(this);
        datelistPopupWindow = new ListPopupWindow(LeaderBoardActivity.this);

        reportData = new ArrayList<>();
        levelDataArrayList = new ArrayList<>();
        leaderBoardAdapter = new LeaderBoardAdapter(this, reportData);
        //  winningDateAdapter = new WinningDateAdapter(this, levelDataArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setAdapter(leaderBoardAdapter);
        datelistPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                windate.setText(datelist.get(i).getDate());

                getdatecode = datelist.get(i).getDate();

                datelistPopupWindow.dismiss();
            }
        });
        windate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                datelistPopupWindow.setAdapter(new ArrayAdapter(
                        LeaderBoardActivity.this,
                        R.layout.check_list_item, listDateName));
                datelistPopupWindow.setAnchorView(windate);
               // datelistPopupWindow.setWidth(800);
               // datelistPopupWindow.setHeight();
                datelistPopupWindow.getBackground();
                datelistPopupWindow.setModal(true);
                datelistPopupWindow.show();
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);

        CallWinningDate();
        CallApiCompleteSlot();
    }

    private void CallWinningDate() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(LeaderBoardActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.WINNING_DATE_RESPONSE_CALL();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(LeaderBoardActivity.this).clearNonTouchableFlags(LeaderBoardActivity.this);
                    dialog.dismiss();
                    WinningDateResponse authResponse = (WinningDateResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            datelist.addAll(authResponse.getData().getRecords());
                            listDateName.clear();
                            getstatebyname(authResponse.getData().getRecords());
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

    private void getstatebyname(ArrayList<WinningDateRecord> dateRecords) {
        for (int i = 0; i < dateRecords.size(); i++) {
            listDateName.add(dateRecords.get(i).getDate());
        }
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

package com.imuons.saddaadda.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.SaddaxReportDataModel;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;
import com.imuons.saddaadda.EntityClass.SaddaXEntity;
import com.imuons.saddaadda.EntityClass.SaddaXTopUp;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.SaddaXReportAdapters;
import com.imuons.saddaadda.responseModel.SaddaXResponse;
import com.imuons.saddaadda.responseModel.SaddaxReportResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DusKaDamActivity extends AppCompatActivity {

    @BindView(R.id.gotoRules)
    RelativeLayout gotoRules;

    String Product_idOne = "1";
    String Product_idTwo = "2";
    String Product_idThree = "3";
    String Product_idFour = "4";
    String Product_idFive = "5";
    String Product_idSix = "6";
    String Product_idSeven = "7";
    String Product_idEight = "8";
    String Product_idNine = "9";
    String Product_idTen = "10";

    String Product_idEleven = "11";
    String Product_idTwelve = "12";
    String Product_idThirteen = "13";
    String Product_idFourteen = "14";
    String Product_idFifteen = "15";
    String Product_idSixteen = "16";
    String Product_idSeventeen = "17";
    String Product_idEighteen = "18";
    String Product_idNineteen = "19";
    String Product_idTwenty = "20";

    String Product_idTwentyOne = "21";
    String Product_idTwentyTwo = "22";
    String Product_idTwentyThree = "23";
    String Product_idTwentyFour = "24";
    String Product_idTwentyFive = "25";
    String Product_idTwentySix = "26";
    String Product_idTwentySeven = "27";
    String Product_idTwentyEight = "28";
    String Product_idTwentyNine = "29";
    String Product_idThirty = "30";

    String Product_idThirtyOne = "31";
    String Product_idThirtyTwo = "32";
    String Product_idThirtyThree = "33";
    String Product_idThirtyFour = "34";
    String Product_idThirtyFive = "35";
    String Product_idThirtySix = "36";
    String Product_idThirtySeven = "37";
    AlertDialog dialogBuilder;
    String userId;

    ArrayList<SaddaxReportDataModel> reportData;
    SaddaXReportAdapters reportAdapter;
    UpcomingSlotData upcomingSlotData;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    int mSlotId;
    String slotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dus_ka_dam);
        ButterKnife.bind(this);
        userId = AppCommon.getInstance(this).getUserId();
        mSlotId = getIntent().getIntExtra("pos", 0);
        slotId = String.valueOf(mSlotId);
        reportData = new ArrayList<>();
        reportAdapter = new SaddaXReportAdapters(this, reportData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setNestedScrollingEnabled(true);
        recycleView.setAdapter(reportAdapter);
        CallApiForSaddaxReport(slotId);
    }


    private void CallApiForSaddaxReport(String slotId) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(DusKaDamActivity.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.SADDAX_REPORT_RESPONSE_CALL(new SaddaXTopUp(slotId));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(DusKaDamActivity.this).clearNonTouchableFlags(DusKaDamActivity.this);
                    dialog.dismiss();
                    SaddaxReportResponse authResponse = (SaddaxReportResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                            if (authResponse.getData() != null) {

                                setAdapter((ArrayList<SaddaxReportDataModel>) authResponse.getData());
                            } else {
                                Toast.makeText(DusKaDamActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            Toast.makeText(DusKaDamActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(DusKaDamActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(DusKaDamActivity.this).showDialog(DusKaDamActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(DusKaDamActivity.this).clearNonTouchableFlags(DusKaDamActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    //  Toast.makeText(DusKaDamActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setAdapter(ArrayList<SaddaxReportDataModel> data) {
        SaddaXReportAdapters saddaXReportAdapters = new SaddaXReportAdapters(this, data);
        recycleView.setAdapter(saddaXReportAdapters);
    }


    private void openDialog(String product_id) {
        dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogbox, null);

        final EditText editView = (EditText) dialogView.findViewById(R.id.etAmount);
        TextView Cancel = (TextView) dialogView.findViewById(R.id.btn_Cancel);
        TextView Submit = (TextView) dialogView.findViewById(R.id.btnSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = editView.getText().toString();
                if (amount.isEmpty()) {
                    editView.setError(getResources().getString(R.string.pls_enter_amount));
                } else {
                    dialogBuilder.dismiss();
                    betApi(product_id, amount, userId, slotId);
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS

                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    private void betApi(String product_id, String amount, String id, String slotid) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(DusKaDamActivity.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.SADDA_X_RESPONSE_CALL(new SaddaXEntity(product_id, amount, id, slotid));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(DusKaDamActivity.this).clearNonTouchableFlags(DusKaDamActivity.this);
                    dialog.dismiss();
                    SaddaXResponse authResponse = (SaddaXResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                            Toast.makeText(DusKaDamActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            CallApiForSaddaxReport(slotId);
                        } else {
                            Toast.makeText(DusKaDamActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(DusKaDamActivity.this).showDialog(DusKaDamActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(DusKaDamActivity.this).clearNonTouchableFlags(DusKaDamActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(DusKaDamActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }


    }


    @OnClick(R.id.one)
    void goOne() {
        openDialog(Product_idOne);
    }

    @OnClick(R.id.two)
    void goTwo() {
        openDialog(Product_idTwo);
    }

    @OnClick(R.id.three)
    void goThree() {
        openDialog(Product_idThree);
    }

    @OnClick(R.id.four)
    void goFour() {
        openDialog(Product_idFour);
    }

    @OnClick(R.id.five)
    void goFive() {
        openDialog(Product_idFive);
    }

    @OnClick(R.id.six)
    void goSix() {
        openDialog(Product_idSix);
    }

    @OnClick(R.id.seven)
    void goSeven() {
        openDialog(Product_idSeven);
    }

    @OnClick(R.id.eight)
    void goEight() {
        openDialog(Product_idEight);
    }

    @OnClick(R.id.nine)
    void goNine() {
        openDialog(Product_idNine);
    }

    @OnClick(R.id.ten)
    void goTen() {
        openDialog(Product_idTen);
    }


    /*11 to 20*/
    @OnClick(R.id.eleven)
    void eleven() {
        openDialog(Product_idEleven);
    }

    @OnClick(R.id.twelve)
    void twelve() {
        openDialog(Product_idTwelve);
    }

    @OnClick(R.id.thirteen)
    void thirteen() {
        openDialog(Product_idThirteen);
    }

    @OnClick(R.id.fourteen)
    void fourteen() {
        openDialog(Product_idFourteen);
    }

    @OnClick(R.id.fifteen)
    void fifteen() {
        openDialog(Product_idFifteen);
    }

    @OnClick(R.id.sixteen)
    void sixteen() {
        openDialog(Product_idSixteen);
    }

    @OnClick(R.id.seventeen)
    void seventeen() {
        openDialog(Product_idSeventeen);
    }

    @OnClick(R.id.eighteen)
    void eighteen() {
        openDialog(Product_idEighteen);
    }

    @OnClick(R.id.nineteen)
    void nineteen() {
        openDialog(Product_idNineteen);
    }

    @OnClick(R.id.twenty)
    void twenty() {
        openDialog(Product_idTwenty);
    }


    /*21 to 30*/
    @OnClick(R.id.twentyOne)
    void twentyOne() {
        openDialog(Product_idTwentyOne);
    }

    @OnClick(R.id.twentyTwo)
    void twentyTwo() {
        openDialog(Product_idTwentyTwo);
    }

    @OnClick(R.id.twentyThree)
    void twentyThree() {
        openDialog(Product_idTwentyThree);
    }

    @OnClick(R.id.twentyFour)
    void twentyFour() {
        openDialog(Product_idTwentyFour);
    }

    @OnClick(R.id.twentyFive)
    void twentyFive() {
        openDialog(Product_idTwentyFive);
    }

    @OnClick(R.id.twentySix)
    void twentySix() {
        openDialog(Product_idTwentySix);
    }

    @OnClick(R.id.twentySeven)
    void twentySeven() {
        openDialog(Product_idTwentySeven);
    }

    @OnClick(R.id.twentyEight)
    void twentyEight() {
        openDialog(Product_idTwentyEight);
    }

    @OnClick(R.id.twentyNine)
    void twentyNine() {
        openDialog(Product_idTwentyNine);
    }

    @OnClick(R.id.thirty)
    void thirty() {
        openDialog(Product_idThirty);
    }

    @OnClick(R.id.thirtyOne)
    void thirtyOne() {
        openDialog(Product_idThirtyOne);
    }

    @OnClick(R.id.thirtyTwo)
    void thirtyTwo() {
        openDialog(Product_idThirtyTwo);
    }

    @OnClick(R.id.thirtyThree)
    void thirtyThree() {
        openDialog(Product_idThirtyThree);
    }

    @OnClick(R.id.thirtyFour)
    void thirtyFour() {
        openDialog(Product_idThirtyFour);
    }

    @OnClick(R.id.thirtyFive)
    void thirtyFive() {
        openDialog(Product_idThirtyFive);
    }

    @OnClick(R.id.thirtySix)
    void thirtySix() {
        openDialog(Product_idThirtySix);
    }

    @OnClick(R.id.thirtySeven)
    void thirtySeven() {
        openDialog(Product_idThirtySeven);
    }


    @OnClick(R.id.gotoRules)
    void goToRules() {

        startActivity(new Intent(this, RulesActivity.class));
    }
}

package com.imuons.saddaadda.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.imuons.saddaadda.DataModel.SaddaxReportDataModel;
import com.imuons.saddaadda.DataModel.ShatakProductRecord;
import com.imuons.saddaadda.DataModel.ShatakProductResponseModel;
import com.imuons.saddaadda.EntityClass.SaddaXTopUp;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.adapters.ItemSaddaShatakListView;
import com.imuons.saddaadda.responseModel.CommonResponseModel;
import com.imuons.saddaadda.responseModel.SaddaxReportResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySaddaShatak extends AppCompatActivity {
    @BindView(R.id.ll_number_layer)
    LinearLayout ll_number_layer;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private AlertDialog dialogBuilder;
    private LinearLayout last_click_numbre;
    private TextView last_click_text;
    private int slot_number;
    private List<ShatakProductRecord> reportData;
    private boolean is_invest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadda_shatak);
        ButterKnife.bind(this);
        CallProduct();
        slot_number = getIntent().getIntExtra("pos", 0);


        CallShatakReport(slot_number);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setLayer();
        }
    }

    private void CallProduct() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ActivitySaddaShatak.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.GetShatakProduct();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ActivitySaddaShatak.this).clearNonTouchableFlags(ActivitySaddaShatak.this);
                    dialog.dismiss();
                    ShatakProductResponseModel authResponse = (ShatakProductResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            reportData = authResponse.getData();

                        } else {
                            Toast.makeText(ActivitySaddaShatak.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ActivitySaddaShatak.this).showDialog(ActivitySaddaShatak.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ActivitySaddaShatak.this).clearNonTouchableFlags(ActivitySaddaShatak.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ActivitySaddaShatak.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter(ArrayList<SaddaxReportDataModel> data) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setNestedScrollingEnabled(true);
        ItemSaddaShatakListView adapter = new ItemSaddaShatakListView(getApplicationContext(), ActivitySaddaShatak.this, data);
        recycler_view.setAdapter(adapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setLayer() {
        int rwo_number = 1;
        int last_point = 0;

        for (int i = 1; i <= 10; i++) {


            LinearLayout.LayoutParams mainparam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            mainparam.gravity = Gravity.CENTER_VERTICAL;
            mainparam.setMargins(0, 2, 0, 0);
            LinearLayout main_layer = new LinearLayout(this);
            main_layer.setLayoutParams(mainparam);
            main_layer.setWeightSum(10);
            main_layer.setOrientation(LinearLayout.HORIZONTAL);
            rwo_number = last_point;
            rwo_number = rwo_number + 1;
            for (int j = rwo_number; j <= 10 * i; j++) {
                Log.d("number prnt", "-----" + j);
                last_point = j;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                params.gravity = Gravity.CENTER;
                params.setMargins(2, 2, 2, 2);
                LinearLayout ll_box = new LinearLayout(this);
                ll_box.setLayoutParams(params);
                ll_box.setGravity(Gravity.CENTER);
                if (i % 2 == 0) {
                    ll_box.setBackground(getResources().getDrawable(R.drawable.select_bg_boarder_yellow_yellow));
                } else {
                    ll_box.setBackground(getResources().getDrawable(R.drawable.select_bg_boarder_green_yellow));
                }

                LinearLayout.LayoutParams txt_param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                txt_param.gravity = Gravity.CENTER;
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(txt_param);
                textView.setTextColor(getResources().getColor(R.color.lightgreen));
                textView.setTextSize(16);
                textView.setText(String.valueOf(j));

                ll_box.addView(textView);
                main_layer.addView(ll_box);

                ll_box.setTag(j);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("click event", "----" + ll_box.getTag().toString());
                        if (ll_box.isSelected()) {
                            if(selectelBix.containsKey(ll_box.getTag().toString())){
                                last_click_numbre = ll_box;
                                last_click_text = textView;
                                openDialog(ll_box.getTag().toString());
                                ll_box.setSelected(true);
                                textView.setActivated(true);
                                textView.setTextColor(getResources().getColor(R.color.colorBlack));
                            }else{
                                ll_box.setSelected(false);
                                textView.setTextColor(getResources().getColor(R.color.lightgreen));
                            }

                        } else {
                            last_click_numbre = ll_box;
                            last_click_text = textView;
                            openDialog(ll_box.getTag().toString());
                            ll_box.setSelected(true);
                            textView.setActivated(true);
                            textView.setTextColor(getResources().getColor(R.color.colorBlack));
                        }


                    }
                });


            }
            ll_number_layer.addView(main_layer);
        }

    }

    public void add(int position) {

    }

    public void minus(int position) {

    }

    @Override
    public void onBackPressed() {
        if (is_invest) {
            finish();
        }
        if (last_click_numbre != null) {
            last_click_numbre.setSelected(false);
            last_click_text.setTextColor(getResources().getColor(R.color.lightgreen));
            last_click_numbre = null;
        } else {
            finish();
        }
    }

    private void openDialog(String product_id) {
        dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogbox, null);

        final EditText editView = dialogView.findViewById(R.id.etAmount);
        TextView Cancel = dialogView.findViewById(R.id.btn_Cancel);
        TextView Submit = dialogView.findViewById(R.id.btnSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = editView.getText().toString();
                if (amount.isEmpty()) {
                    editView.setError(getResources().getString(R.string.pls_enter_amount));
                } else {
                    dialogBuilder.dismiss();
                    callSerivice(product_id, amount);
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                if (last_click_numbre != null) {
                    last_click_numbre.setSelected(false);
                    last_click_text.setTextColor(getResources().getColor(R.color.lightgreen));
                }
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
Map<String,Boolean> selectelBix=new HashMap<>();
    private void callSerivice(String product_id, String amount) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ActivitySaddaShatak.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Map<String, Object> param = new HashMap<>();
            param.put("slot_no", slot_number);
            param.put("product_id", reportData.get(Integer.parseInt(product_id) - 1).getId());
            param.put("amount", amount);
            Log.d("Param pass", "-----param--" + param);
            Call call = apiService.DumdarShatak(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ActivitySaddaShatak.this).clearNonTouchableFlags(ActivitySaddaShatak.this);
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    CommonResponseModel authResponse = (CommonResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            is_invest = true;
                            selectelBix.put(product_id, true);
                            CallShatakReport(slot_number);
                            Toast.makeText(ActivitySaddaShatak.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            if(!selectelBix.containsKey(product_id)) {
                                if (last_click_numbre != null) {
                                    last_click_numbre.setSelected(false);
                                    last_click_text.setTextColor(getResources().getColor(R.color.lightgreen));
                                    last_click_numbre = null;
                                }
                            }
                            Toast.makeText(ActivitySaddaShatak.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if(!selectelBix.containsKey(product_id)) {
                            if (last_click_numbre != null) {
                                last_click_numbre.setSelected(false);
                                last_click_text.setTextColor(getResources().getColor(R.color.lightgreen));
                                last_click_numbre = null;
                            }
                        }
                        AppCommon.getInstance(ActivitySaddaShatak.this).showDialog(ActivitySaddaShatak.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    if(!selectelBix.containsKey(product_id)) {
                        if (last_click_numbre != null) {
                            last_click_numbre.setSelected(false);
                            last_click_text.setTextColor(getResources().getColor(R.color.lightgreen));
                            last_click_numbre = null;
                        }
                    }
                    AppCommon.getInstance(ActivitySaddaShatak.this).clearNonTouchableFlags(ActivitySaddaShatak.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ActivitySaddaShatak.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            if(!selectelBix.containsKey(product_id)) {
                if (last_click_numbre != null) {
                    last_click_numbre.setSelected(false);
                    last_click_text.setTextColor(getResources().getColor(R.color.lightgreen));
                    last_click_numbre = null;
                }
            }
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void CallShatakReport(int slotId) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            Dialog dialog = ViewUtils.getProgressBar(ActivitySaddaShatak.this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.GetShtakReport(new SaddaXTopUp(String.valueOf(slotId)));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ActivitySaddaShatak.this).clearNonTouchableFlags(ActivitySaddaShatak.this);
                    dialog.dismiss();
                    SaddaxReportResponse authResponse = (SaddaxReportResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response: report:", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                            if (authResponse.getData() != null) {

                                setAdapter((ArrayList<SaddaxReportDataModel>) authResponse.getData());
                            } else {
                                Toast.makeText(ActivitySaddaShatak.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            Toast.makeText(ActivitySaddaShatak.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ActivitySaddaShatak.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ActivitySaddaShatak.this).showDialog(ActivitySaddaShatak.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(ActivitySaddaShatak.this).clearNonTouchableFlags(ActivitySaddaShatak.this);
                    // loaderView.setVisibility(View.GONE);
                    //  Toast.makeText(DusKaDamActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }
}

package com.imuons.saddaadda.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.imuons.saddaadda.EntityClass.SathKaDamEntity;
import com.imuons.saddaadda.EntityClass.UpdateProfileEntity;
import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.ViewUtils;
import com.imuons.saddaadda.responseModel.LoginResponseModel;
import com.imuons.saddaadda.responseModel.SathKaDamResponse;
import com.imuons.saddaadda.responseModel.UpdateProfileResponse;
import com.imuons.saddaadda.retrofit.AppService;
import com.imuons.saddaadda.retrofit.ServiceGenerator;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class SevenUpDown extends AppCompatActivity {

    @BindView(R.id.twoClick)
    ImageView twoClick;
    @BindView(R.id.sevenClick)
    ImageView sevenClick;
    @BindView(R.id.eightClick)
    ImageView eightClick;
    @BindView(R.id.dais_img)
    ImageView dais_img;
    @BindView(R.id.dais_img1)
    ImageView dais_img1;

    @BindView(R.id.times)
    TextView times;

    @BindView(R.id.bitText)
    EditText bitText;
    @BindView(R.id.two_2x)
    TextView two_2x;
    @BindView(R.id.seven_3x)
    TextView seven_3x;

    @BindView(R.id.eight_2x)
    TextView eight_2x;

    @BindView(R.id.tol1)
    TextView tol1;
    @BindView(R.id.tol2)
    TextView tol2;
    @BindView(R.id.tol3)
    TextView tol3;
    @BindView(R.id.tol4)
    TextView tol4;
    @BindView(R.id.tol5)
    TextView tol5;
    @BindView(R.id.tol6)
    TextView tol6;
    @BindView(R.id.tol7)
    TextView tol7;
    @BindView(R.id.tol8)
    TextView tol8;
    @BindView(R.id.tol9)
    TextView tol9;
    @BindView(R.id.tol10)
    TextView tol10;
    @BindView(R.id.tol11)
    TextView tol11;
    @BindView(R.id.tol12)
    TextView tol12;
    @BindView(R.id.fullImage)
    ImageView fullImage;
    @BindView(R.id.rolldice)
    ImageView rolldice;

    @BindView(R.id.tv_balance)
    TextView tv_balance;


    AnimationDrawable anim;
    AnimationDrawable anim2;
    boolean isOn;

    int selectedType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_up_down);
        ButterKnife.bind(this);
        twoClick.setActivated(true);
        dais_img.setVisibility(View.GONE);
        dais_img1.setVisibility(View.GONE);
        rolldice.setVisibility(View.VISIBLE);
        bitText.setSelection(bitText.getText().length());
        tv_balance.setText(String.valueOf(AppCommon.getInstance(this).getAccount()));
        bitText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String valStr = s.toString().trim();
                if (s != null && !valStr.isEmpty()) {
                    int val = Integer.parseInt(s.toString().trim());
                    two_2x.setText(String.valueOf(val * 2));
                    seven_3x.setText(String.valueOf(val * 3));
                    eight_2x.setText(String.valueOf(val * 2));
                } else {
                    two_2x.setText("0");
                    seven_3x.setText("0");
                    eight_2x.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Animation();

    }

    private void Animation() {
        dais_img.setImageDrawable(null);
        dais_img1.setImageDrawable(null);
        dais_img.clearAnimation();
        dais_img1.clearAnimation();
        dais_img.setImageResource(R.drawable.firstlevelanimation);
        dais_img1.setImageResource(R.drawable.firstlevelanimation1);
        anim = (AnimationDrawable) dais_img.getDrawable();
        anim2 = (AnimationDrawable) dais_img1.getDrawable();
        dais_img.post(run);
        dais_img1.post(run);
        isOn = true;

    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            anim.start();
            anim2.start();

        }
    };

    @OnClick(R.id.twoClick)
    void twoClick() {

        twoClick.setActivated(true);
        times.setText("2X");
        sevenClick.setActivated(false);
        eightClick.setActivated(false);
        selectedType = 0;
    }

    @OnClick(R.id.sevenClick)
    void sevenClick() {

        sevenClick.setActivated(true);
        times.setText("3X");
        twoClick.setActivated(false);
        eightClick.setActivated(false);
        selectedType = 1;
    }

    @OnClick(R.id.eightClick)
    void eightClick() {

        eightClick.setActivated(true);
        times.setText("2X");
        sevenClick.setActivated(false);
        twoClick.setActivated(false);
        selectedType = 2;
    }

    @OnClick(R.id.add_rl)
    void add_layout() {
        int bitAmount = Integer.parseInt(bitText.getText().toString().trim());
        bitAmount = bitAmount + 10;
        if (bitAmount <= 1000) {
            bitText.setText(String.valueOf(bitAmount));
            two_2x.setText(String.valueOf(bitAmount * 2));
            seven_3x.setText(String.valueOf(bitAmount * 3));
            eight_2x.setText(String.valueOf(bitAmount * 2));
        } else
            Toast.makeText(this, "Max Limit is 1000 only", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.remove_rl)
    void remove_layout() {
        int bitAmount = Integer.parseInt(bitText.getText().toString().trim());
        if (bitAmount != 10) {
            bitAmount = bitAmount - 10;
            bitText.setText(String.valueOf(bitAmount));
            two_2x.setText(String.valueOf(bitAmount * 2));
            seven_3x.setText(String.valueOf(bitAmount * 3));
            eight_2x.setText(String.valueOf(bitAmount * 2));
        } else {
            Toast.makeText(this, "Min Limit is 10 only", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick({R.id.dais_img1, R.id.dais_img, R.id.rolldice})
    void roll() {
        AppCommon.getInstance(this).onHideKeyBoard(this);
        if (bitText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter you bet", Toast.LENGTH_SHORT).show();

        } else {
            int val = Integer.parseInt(bitText.getText().toString().trim());
            if (AppCommon.getInstance(this).getAccount() >= val) {
                if (val % 10 == 0) {
                    if (val <= 1000 && val >= 10) {
                        dais_img.setVisibility(View.VISIBLE);
                        dais_img1.setVisibility(View.VISIBLE);
                        rolldice.setVisibility(View.GONE);
                        dais_img.setImageResource(R.drawable.firstlevelanimation);
                        dais_img1.setImageResource(R.drawable.firstlevelanimation1);
                        Animation();
                        callApi();
                    } else {
                        Toast.makeText(this, "Bet limit is 10 - 1000 only", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter you bet multiply by 10", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Insufficient Balance!! ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void callApi() {
        String id;
        if (twoClick.isActivated()) {
            Log.i("con", "win 2-6");
            id = "1";
        } else if (sevenClick.isActivated()) {
            Log.i("con", "win 7");
            id = "2";
        } else if (eightClick.isActivated()) {
            Log.i("con", "win 8-12");
            id = "3";
        } else {
            Log.i("con", "loss");
            id = "1";
        }

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            //Dialog dialog = ViewUtils.getProgressBar(SevenUpDown.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(this).getToken());
            Call call = apiService.SATH_KA_DAM_RESPONSE_Call(new SathKaDamEntity(id, AppCommon.getInstance(this).getUserId(), bitText.getText().toString().trim()));
            call.enqueue(new Callback() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SevenUpDown.this).clearNonTouchableFlags(SevenUpDown.this);
                    //  dialog.dismiss();
                    SathKaDamResponse authResponse = (SathKaDamResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            //Toast.makeText(SevenUpDown.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                setData(authResponse);

                            }
                        } else {
                            dais_img.setVisibility(View.GONE);
                            dais_img1.setVisibility(View.GONE);
                            rolldice.setVisibility(View.VISIBLE);
                            dais_img.setImageResource(R.drawable.firstlevelanimation);
                            dais_img1.setImageResource(R.drawable.firstlevelanimation1);
                            Toast.makeText(SevenUpDown.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        dais_img.setVisibility(View.GONE);
                        dais_img1.setVisibility(View.GONE);
                        rolldice.setVisibility(View.VISIBLE);
                        dais_img.setImageResource(R.drawable.firstlevelanimation);
                        dais_img1.setImageResource(R.drawable.firstlevelanimation1);
                        AppCommon.getInstance(SevenUpDown.this).showDialog(SevenUpDown.this, "Server Error");
                    }
                }


                @Override
                public void onFailure(Call call, Throwable t) {
                    // dialog.dismiss();
                    dais_img.setVisibility(View.GONE);
                    dais_img1.setVisibility(View.GONE);
                    rolldice.setVisibility(View.VISIBLE);
                    dais_img.setImageResource(R.drawable.firstlevelanimation);
                    dais_img1.setImageResource(R.drawable.firstlevelanimation1);
                    AppCommon.getInstance(SevenUpDown.this).clearNonTouchableFlags(SevenUpDown.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SevenUpDown.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            dais_img.setImageResource(R.drawable.firstlevelanimation);
            dais_img1.setImageResource(R.drawable.firstlevelanimation1);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setData(SathKaDamResponse authResponse) {
        String  status = authResponse.getData().getStatus();;
        AppCommon.getInstance(this).setAccount(authResponse.getData().getTopUpWalletBalance());
        tv_balance.setText(String.valueOf(AppCommon.getInstance(this).getAccount()));

        if(selectedType == 0 )
        {
            if(status.equalsIgnoreCase("win")){
                two_six(status);
            }else {
                eight_twielw(status);
            }
        }else if (selectedType == 1){
            if(status.equalsIgnoreCase("win")){
                seven(status);
            }else {
                two_six(status);
            }
        }else {
            if(status.equalsIgnoreCase("win")){
                eight_twielw(status);
            }else {
                two_six(status);
            }
        }

       /*int dice1 = authResponse.getData().getDice1();
        int dice2 = authResponse.getData().getDice2();
        anim.stop();
        anim2.stop();
        int totalVal = dice1 + dice2;
        AppCommon.getInstance(this).setAccount(authResponse.getData().getTopUpWalletBalance());
        tv_balance.setText(String.valueOf(AppCommon.getInstance(this).getAccount()));
        if (dice1 == 1) {
            dais_img.setImageResource(R.drawable.single_1);
        } else if (dice1 == 2) {
            dais_img.setImageResource(R.drawable.single_2);
        } else if (dice1 == 3) {
            dais_img.setImageResource(R.drawable.single_3);
        } else if (dice1 == 4) {
            dais_img.setImageResource(R.drawable.single_4);
        } else if (dice1 == 5) {
            dais_img.setImageResource(R.drawable.single_5);
        } else if (dice1 == 6) {
            dais_img.setImageResource(R.drawable.single_6);
        }
        if (dice2 == 1) {
            dais_img1.setImageResource(R.drawable.single_1);
        } else if (dice2 == 2) {
            dais_img1.setImageResource(R.drawable.single_2);
        } else if (dice2 == 3) {
            dais_img1.setImageResource(R.drawable.single_3);
        } else if (dice2 == 4) {
            dais_img1.setImageResource(R.drawable.single_4);
        } else if (dice2 == 5) {
            dais_img1.setImageResource(R.drawable.single_5);
        } else if (dice2 == 6) {
            dais_img1.setImageResource(R.drawable.single_6);
        }

        if (tol1.getText().toString().trim().isEmpty()) {
            tol1.setText(String.valueOf(totalVal));
        } else if (tol2.getText().toString().trim().isEmpty()) {
            tol2.setText(String.valueOf(totalVal));
        } else if (tol3.getText().toString().trim().isEmpty()) {
            tol3.setText(String.valueOf(totalVal));
        } else if (tol4.getText().toString().trim().isEmpty()) {
            tol4.setText(String.valueOf(totalVal));
        } else if (tol5.getText().toString().trim().isEmpty()) {
            tol5.setText(String.valueOf(totalVal));
        } else if (tol6.getText().toString().trim().isEmpty()) {
            tol6.setText(String.valueOf(totalVal));
        } else if (tol7.getText().toString().trim().isEmpty()) {
            tol7.setText(String.valueOf(totalVal));
        } else if (tol8.getText().toString().trim().isEmpty()) {
            tol8.setText(String.valueOf(totalVal));
        } else if (tol9.getText().toString().trim().isEmpty()) {
            tol9.setText(String.valueOf(totalVal));
        } else if (tol10.getText().toString().trim().isEmpty()) {
            tol10.setText(String.valueOf(totalVal));
        } else if (tol11.getText().toString().trim().isEmpty()) {
            tol11.setText(String.valueOf(totalVal));
        } else if (tol12.getText().toString().trim().isEmpty()) {
            tol12.setText(String.valueOf(totalVal));
        } else {
            tol1.setText("");
            tol2.setText("");
            tol3.setText("");
            tol4.setText("");
            tol5.setText("");
            tol6.setText("");
            tol7.setText("");
            tol8.setText("");
            tol9.setText("");
            tol10.setText("");
            tol11.setText("");
            tol12.setText("");
            tol1.setText(String.valueOf(totalVal));
        }
*/
       /* fullImage.setVisibility(View.VISIBLE);
        if (authResponse.getData().getStatus().equalsIgnoreCase("Win")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fullImage.setImageDrawable(getDrawable(R.drawable.new_winner1));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fullImage.setImageDrawable(getDrawable(R.drawable.lose));
            }
        }
        setAnimtion();*/
    }

    private void setAnimtion() {
        AppCommon.getInstance(this).setNonTouchableFlags(this);
        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        fullImage.startAnimation(aniFade);

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Animation aniFade1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                fullImage.startAnimation(aniFade1);
                fullImage.setVisibility(View.GONE);
                fullImage.setImageDrawable(null);
                AppCommon.getInstance(SevenUpDown.this).clearNonTouchableFlags(SevenUpDown.this);
                dais_img.setVisibility(View.GONE);
                dais_img1.setVisibility(View.GONE);
                rolldice.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(r, 1000);

    }

    public static double getRandomDoubleBetweenRange(double min, double max) {
        double x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    public void two_six(String status) {
        int caseVal = (int) getRandomDoubleBetweenRange(1, 14);
        int total =0;
        anim.stop();
        anim2.stop();
        switch (caseVal) {
            case 1:
                dais_img.setImageResource(R.drawable.single_1);
                dais_img1.setImageResource(R.drawable.single_1);
                total = 2;
                break;
            case 2:
                dais_img.setImageResource(R.drawable.single_1);
                dais_img1.setImageResource(R.drawable.single_2);
                total = 3;
                break;
            case 3:
                dais_img.setImageResource(R.drawable.single_1);
                dais_img1.setImageResource(R.drawable.single_3);
                total = 4;
                break;
            case 4:
                dais_img.setImageResource(R.drawable.single_1);
                dais_img1.setImageResource(R.drawable.single_4);
                total = 5;
                break;
            case 5:
                dais_img.setImageResource(R.drawable.single_2);
                dais_img1.setImageResource(R.drawable.single_1);
                total = 3;
                break;
            case 6:
                dais_img.setImageResource(R.drawable.single_3);
                dais_img1.setImageResource(R.drawable.single_1);
                total = 4;
                break;
            case 7:
                dais_img.setImageResource(R.drawable.single_4);
                dais_img1.setImageResource(R.drawable.single_1);
                total = 5;
                break;
            case 8:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_1);
                total = 6;
                break;
            case 9:
                dais_img.setImageResource(R.drawable.single_2);
                dais_img1.setImageResource(R.drawable.single_2);
                total = 4;
                break;
            case 10:
                dais_img.setImageResource(R.drawable.single_2);
                dais_img1.setImageResource(R.drawable.single_3);
                total = 5;

                break;
            case 11:
                dais_img.setImageResource(R.drawable.single_2);
                dais_img1.setImageResource(R.drawable.single_4);
                total = 6;
                break;
            case 12:
                dais_img.setImageResource(R.drawable.single_3);
                dais_img1.setImageResource(R.drawable.single_2);
                total = 5;
                break;
            case 13:
                dais_img.setImageResource(R.drawable.single_4);
                dais_img1.setImageResource(R.drawable.single_2);
                total = 6;
                break;
            case 14:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_1);
                total = 6;
                break;

        }
        setBottomText(total  , status);




    }

    public void seven(String status) {
        anim.stop();
        anim2.stop();
        int caseVal = (int) getRandomDoubleBetweenRange(1, 6);
        int total = 7;
        switch (caseVal) {
            case 1:
                dais_img.setImageResource(R.drawable.single_1);
                dais_img1.setImageResource(R.drawable.single_6);

                break;
            case 2:
                dais_img.setImageResource(R.drawable.single_2);
                dais_img1.setImageResource(R.drawable.single_5);
                break;
            case 3:
                dais_img.setImageResource(R.drawable.single_3);
                dais_img1.setImageResource(R.drawable.single_4);
                break;
            case 4:
                dais_img.setImageResource(R.drawable.single_4);
                dais_img1.setImageResource(R.drawable.single_3);
                break;
            case 5:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_2);
                break;
            case 6:
                dais_img.setImageResource(R.drawable.single_6);
                dais_img1.setImageResource(R.drawable.single_1);
                break;


        }
        setBottomText(7 , status);


    }

    public void eight_twielw(String status) {
        int caseVal = (int) getRandomDoubleBetweenRange(1, 14);
        int total = 0;
        anim.stop();
        anim2.stop();
        switch (caseVal) {
            case 1:
                dais_img.setImageResource(R.drawable.single_2);
                dais_img1.setImageResource(R.drawable.single_6);
                total = 8;
                break;
            case 2:
                dais_img.setImageResource(R.drawable.single_3);
                dais_img1.setImageResource(R.drawable.single_5);
                total = 8;
                break;
            case 3:
                dais_img.setImageResource(R.drawable.single_3);
                dais_img1.setImageResource(R.drawable.single_6);
                total = 9;

                break;
            case 4:
                dais_img.setImageResource(R.drawable.single_4);
                dais_img1.setImageResource(R.drawable.single_4);
                total = 8;
                break;
            case 5:
                dais_img.setImageResource(R.drawable.single_4);
                dais_img1.setImageResource(R.drawable.single_6);
                total = 10;
                break;
            case 6:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_3);
                total = 8;
                break;
            case 7:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_4);
                total = 9;
                break;
            case 8:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_5);
                total = 10;
                break;
            case 9:
                dais_img.setImageResource(R.drawable.single_5);
                dais_img1.setImageResource(R.drawable.single_6);
                total = 11;
                break;
            case 10:
                dais_img.setImageResource(R.drawable.single_6);
                dais_img1.setImageResource(R.drawable.single_2);
                total = 8;
                break;
            case 11:
                dais_img.setImageResource(R.drawable.single_6);
                dais_img1.setImageResource(R.drawable.single_3);
                total = 9;
                break;
            case 12:
                dais_img.setImageResource(R.drawable.single_6);
                dais_img1.setImageResource(R.drawable.single_4);
                total = 10;
                break;
            case 13:
                dais_img.setImageResource(R.drawable.single_6);
                dais_img1.setImageResource(R.drawable.single_5);
                total = 11;
                break;
            case 14:
                dais_img.setImageResource(R.drawable.single_6);
                dais_img1.setImageResource(R.drawable.single_6);
                total = 12;
                break;
        }
        // 8-12
        setBottomText(total , status);

    }

    public void  setBottomText(int totalVal , String staus){
        if (tol1.getText().toString().trim().isEmpty()) {
            tol1.setText(String.valueOf(totalVal));
        } else if (tol2.getText().toString().trim().isEmpty()) {
            tol2.setText(String.valueOf(totalVal));
        } else if (tol3.getText().toString().trim().isEmpty()) {
            tol3.setText(String.valueOf(totalVal));
        } else if (tol4.getText().toString().trim().isEmpty()) {
            tol4.setText(String.valueOf(totalVal));
        } else if (tol5.getText().toString().trim().isEmpty()) {
            tol5.setText(String.valueOf(totalVal));
        } else if (tol6.getText().toString().trim().isEmpty()) {
            tol6.setText(String.valueOf(totalVal));
        } else if (tol7.getText().toString().trim().isEmpty()) {
            tol7.setText(String.valueOf(totalVal));
        } else if (tol8.getText().toString().trim().isEmpty()) {
            tol8.setText(String.valueOf(totalVal));
        } else if (tol9.getText().toString().trim().isEmpty()) {
            tol9.setText(String.valueOf(totalVal));
        } else if (tol10.getText().toString().trim().isEmpty()) {
            tol10.setText(String.valueOf(totalVal));
        } else if (tol11.getText().toString().trim().isEmpty()) {
            tol11.setText(String.valueOf(totalVal));
        } else if (tol12.getText().toString().trim().isEmpty()) {
            tol12.setText(String.valueOf(totalVal));
        } else {
            tol1.setText("");
            tol2.setText("");
            tol3.setText("");
            tol4.setText("");
            tol5.setText("");
            tol6.setText("");
            tol7.setText("");
            tol8.setText("");
            tol9.setText("");
            tol10.setText("");
            tol11.setText("");
            tol12.setText("");
            tol1.setText(String.valueOf(totalVal));
        }
        fullImage.setVisibility(View.VISIBLE);
        if (staus.equalsIgnoreCase("Win")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fullImage.setImageDrawable(getDrawable(R.drawable.new_winner1));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fullImage.setImageDrawable(getDrawable(R.drawable.lose));
            }
        }
        setAnimtion();
    }


}

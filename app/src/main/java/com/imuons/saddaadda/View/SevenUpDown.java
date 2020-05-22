package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView bitText;
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


    AnimationDrawable anim;
    AnimationDrawable anim2;
    boolean isOn;

    int selectedType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_up_down);
        ButterKnife.bind(this);
        twoClick.setActivated(true);
        //Animation();
    }

    private void Animation() {

        anim = (AnimationDrawable) dais_img.getDrawable();
        anim2 = (AnimationDrawable) dais_img1.getDrawable();
        dais_img.post(run);
        dais_img1.post(run);
        isOn = true;
       /* try {
            synchronized(run) {
                run.wait(3000);
                anim.stop();
                anim2.stop();
                dais_img.setImageResource(R.drawable.dice_0);
                dais_img1.setImageResource(R.drawable.dice_0);
            }*/

           /* anim.stop();
            anim2.stop();
            dais_img.setImageResource(R.drawable.dice_0);
            dais_img.setImageResource(R.drawable.dice_0);*/
       /* } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
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
        bitAmount = bitAmount + 100;
        bitText.setText(String.valueOf(bitAmount));
        two_2x.setText(String.valueOf(bitAmount * 2));
        seven_3x.setText(String.valueOf(bitAmount * 3));
        eight_2x.setText(String.valueOf(bitAmount * 2));
    }

    @OnClick(R.id.remove_rl)
    void remove_layout() {
        int bitAmount = Integer.parseInt(bitText.getText().toString().trim());
        if (bitAmount != 100) {
            bitAmount = bitAmount - 100;
            bitText.setText(String.valueOf(bitAmount));
            two_2x.setText(String.valueOf(bitAmount * 2));
            seven_3x.setText(String.valueOf(bitAmount * 3));
            eight_2x.setText(String.valueOf(bitAmount * 2));
        }
    }


    @OnClick({R.id.dais_img1, R.id.dais_img})
    void roll() {
        dais_img.setImageResource(R.drawable.firstlevelanimation);
        dais_img1.setImageResource(R.drawable.firstlevelanimation1);
        Animation();
        callApi();

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
            Dialog dialog = ViewUtils.getProgressBar(SevenUpDown.this);
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.SATH_KA_DAM_RESPONSE_Call(new SathKaDamEntity(id, "S101014", bitText.getText().toString().trim()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SevenUpDown.this).clearNonTouchableFlags(SevenUpDown.this);
                    dialog.dismiss();
                    SathKaDamResponse authResponse = (SathKaDamResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Response::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            Toast.makeText(SevenUpDown.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            setData(authResponse);
                        } else {
                            Toast.makeText(SevenUpDown.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SevenUpDown.this).showDialog(SevenUpDown.this, "Server Error");
                    }
                }


                @Override
                public void onFailure(Call call, Throwable t) {
                    dialog.dismiss();
                    AppCommon.getInstance(SevenUpDown.this).clearNonTouchableFlags(SevenUpDown.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SevenUpDown.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData(SathKaDamResponse authResponse) {
        Double dice1 = authResponse.getData().getDice1();
        Double dice2 = authResponse.getData().getDice2();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    int val1 = (int) getRandomDoubleBetweenRange(1, 6);
                    int val2 = (int) getRandomDoubleBetweenRange(1, 6);
                    anim.stop();
                    anim2.stop();
                    Double totalVal = dice1 + dice2;


                    if (dice1 == 1) {
                        dais_img.setImageResource(R.drawable.single_1);
                    } else if (dice1 == 2) {
                        dais_img.setImageResource(R.drawable.single_2);
                    } else if (dice1 == 3) {
                        dais_img.setImageResource(R.drawable.single_3);
                    } else if (dice1 == 4) {
                        dais_img.setImageResource(R.drawable.single_4);
                    } else if (dice1 == 5) {
                        dais_img.setImageResource(R.drawable.single_6);
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


     /*               switch (dice1) {
                        case 1:
                            dais_img.setImageResource(R.drawable.single_1);
                            break;
                        case 2:
                            dais_img.setImageResource(R.drawable.single_2);
                            break;
                        case 3:
                            dais_img.setImageResource(R.drawable.single_3);
                            break;
                        case 4:
                            dais_img.setImageResource(R.drawable.single_4);
                            break;
                        case 5:
                            dais_img.setImageResource(R.drawable.single_6);
                            break;
                        case 6:
                            dais_img.setImageResource(R.drawable.single_6);
                            break;

                    }
                    switch (dice2) {
                        case 1:
                            dais_img1.setImageResource(R.drawable.single_1);
                            break;
                        case 2:
                            dais_img1.setImageResource(R.drawable.single_2);
                            break;
                        case 3:
                            dais_img1.setImageResource(R.drawable.single_3);
                            break;
                        case 4:
                            dais_img1.setImageResource(R.drawable.single_4);
                            break;
                        case 5:
                            dais_img1.setImageResource(R.drawable.single_5);
                            break;
                        case 6:
                            dais_img1.setImageResource(R.drawable.single_6);
                            break;

                    }*/
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
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();

    }

    public static double getRandomDoubleBetweenRange(double min, double max) {
        double x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

}

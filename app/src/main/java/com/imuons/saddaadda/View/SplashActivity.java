package com.imuons.saddaadda.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    String token = AppCommon.getInstance(SplashActivity.this).getToken();
                    //SharedPreferenceUtils.getAccesstoken(SplashScreen.this);
                  //  token == null || token.isEmpty()
                    if (AppCommon.getInstance(SplashActivity.this).isUserLogIn()) {
                        startActivity(new Intent(SplashActivity.this, PinEnterActivity.class));
                    } else {
                        if ( AppCommon.getInstance(SplashActivity.this).GetIsLangSelected()){
                            startActivity(new Intent(SplashActivity.this, SelectionPage.class));
                        }else{
                            startActivity(new Intent(SplashActivity.this, ActivityLangSelection.class));
                        }

                    }
                    finish();


                } catch (Exception e) {

                }
            }
        };
        thread.start();
    }



}

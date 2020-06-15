package com.imuons.saddaadda.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.saddaadda.R;
import com.imuons.saddaadda.Utils.AppCommon;
import com.imuons.saddaadda.Utils.MyPreference;

import java.util.Locale;

public class ActivityLangSelection extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_english;
    private TextView txt_hind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_selection);
        txt_english = findViewById(R.id.txt_english);
        txt_hind = findViewById(R.id.txt_hindi);

        txt_english.setOnClickListener(this);
        txt_hind.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(txt_english)) {
            AppCommon.getInstance(ActivityLangSelection.this).setSeleectedLan(MyPreference.LANG_ENGLISH);
            AppCommon.getInstance(ActivityLangSelection.this).IslangSelected(true);
            SetAppLocale();
        } else if (v.equals(txt_hind)) {
            AppCommon.getInstance(ActivityLangSelection.this).setSeleectedLan(MyPreference.LANG_HINDI);
            AppCommon.getInstance(ActivityLangSelection.this).IslangSelected(true);
            SetAppLocale();
        }
    }

    private void SetAppLocale() {
        Locale locale = new Locale(AppCommon.getInstance(ActivityLangSelection.this).GetLangSelected());
        // Locale locale = new Locale(Constants.LANG_AREBIC);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        callIntent();
    }

    private void callIntent() {
        finishAffinity();
        String token = AppCommon.getInstance(ActivityLangSelection.this).getToken();
        // if (token == null || token.isEmpty())
        if (AppCommon.getInstance(ActivityLangSelection.this).isUserLogIn()) {
            startActivity(new Intent(ActivityLangSelection.this, PinEnterActivity.class));
        } else {
            startActivity(new Intent(ActivityLangSelection.this, SelectionPage.class));

        }

    }
}

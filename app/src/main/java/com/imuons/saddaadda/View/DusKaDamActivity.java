package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.imuons.saddaadda.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DusKaDamActivity extends AppCompatActivity {

    @BindView(R.id.gotoRules)
    RelativeLayout gotoRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dus_ka_dam);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.gotoRules)
    void goToRules() {

        startActivity(new Intent(this, RulesActivity.class));
    }
}

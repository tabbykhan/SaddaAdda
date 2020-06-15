package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.imuons.saddaadda.R;

import butterknife.ButterKnife;

public class ShatakRules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shatak_rules);
        ButterKnife.bind(this);
    }
}

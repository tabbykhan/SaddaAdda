package com.imuons.saddaadda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);
    }

    public void signIn(View view) {
        startActivity( new Intent(this , LoginActivity.class));
    }

    public void createAccount(View view) {
        startActivity( new Intent(this , MainActivity.class));
    }
}

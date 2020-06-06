package com.imuons.saddaadda.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.saddaadda.R;
import com.imuons.saddaadda.SaddaAdda;

public class ViewSlipActivity extends AppCompatActivity {

    private ImageView iv_attacment;
    private Bundle bundle;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slip);
        iv_attacment=findViewById(R.id.iv_attacment);
        bundle=getIntent().getExtras();
        url=bundle.getString("url");
        SaddaAdda.imageLoader.displayImage(url, iv_attacment);
    }
}

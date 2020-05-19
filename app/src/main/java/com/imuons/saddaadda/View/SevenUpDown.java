package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.imuons.saddaadda.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SevenUpDown extends AppCompatActivity {

    @BindView(R.id.twoClick)
    ImageView twoClick;
    @BindView(R.id.sevenClick)
    ImageView sevenClick;
    @BindView(R.id.eightClick)
    ImageView eightClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_up_down);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.twoClick)
    void twoClick(){
        if(twoClick.isActivated())
            twoClick.setActivated(false);
        else
            twoClick.setActivated(true);
    }
    @OnClick(R.id.sevenClick)
    void sevenClick(){
        if(sevenClick.isActivated())
            sevenClick.setActivated(false);
        else
            sevenClick.setActivated(true);
    }
    @OnClick(R.id.eightClick)
    void eightClick(){
        if(eightClick.isActivated())
            eightClick.setActivated(false);
        else
            eightClick.setActivated(true);
    }

}

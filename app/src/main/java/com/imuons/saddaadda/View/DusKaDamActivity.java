package com.imuons.saddaadda.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imuons.saddaadda.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DusKaDamActivity extends AppCompatActivity {

    @BindView(R.id.gotoRules)
    RelativeLayout gotoRules;

  /*  @BindView(R.id.one)
    ImageView one;*/
    @BindView(R.id.two)
    ImageView two;
    @BindView(R.id.three)
    ImageView three;
    @BindView(R.id.four)
    ImageView four;
    @BindView(R.id.five)
    ImageView five;
    @BindView(R.id.six)
    ImageView six;
    @BindView(R.id.seven)
    ImageView seven;
    @BindView(R.id.eight)
    ImageView eight;
    @BindView(R.id.nine)
    ImageView nine;
    @BindView(R.id.ten)
    ImageView ten;

    @BindView(R.id.eleven)
    ImageView eleven;
    @BindView(R.id.twelve)
    ImageView twelve;
    @BindView(R.id.thirteen)
    ImageView thirteen;
    @BindView(R.id.fourteen)
    ImageView fourteen;
    @BindView(R.id.fifteen)
    ImageView fifteen;
    @BindView(R.id.sixteen)
    ImageView sixteen;
    @BindView(R.id.seventeen)
    ImageView seventeen;
    @BindView(R.id.eighteen)
    ImageView eighteen;
    @BindView(R.id.nineteen)
    ImageView nineteen;
    @BindView(R.id.twenty)
    ImageView twenty;

    @BindView(R.id.twentyOne)
    ImageView twentyOne;
    @BindView(R.id.twentyTwo)
    ImageView twentyTwo;
    @BindView(R.id.twentyThree)
    ImageView twentyThree;
    @BindView(R.id.twentyFour)
    ImageView twentyFour;
    @BindView(R.id.twentyFive)
    ImageView twentyFive;
    @BindView(R.id.twentySix)
    ImageView twentySix;
    @BindView(R.id.twentySeven)
    ImageView twentySeven;
    @BindView(R.id.twentyEight)
    ImageView twentyEight;
    @BindView(R.id.twentyNine)
    ImageView twentyNine;
    @BindView(R.id.thirty)
    ImageView thirty;

    @BindView(R.id.thirtyOne)
    ImageView thirtyOne;
    @BindView(R.id.thirtyTwo)
    ImageView thirtyTwo;
    @BindView(R.id.thirtyThree)
    ImageView thirtyThree;
    @BindView(R.id.thirtyFour)
    ImageView thirtyFour;
    @BindView(R.id.thirtyFive)
    ImageView thirtyFive;
    @BindView(R.id.thirtySix)
    ImageView thirtySix;
    @BindView(R.id.thirtySeven)
    ImageView thirtySeven;

    String Product_idOne = "1";
    String Product_idTwo = "2";
    String Product_idThree = "3";
    String Product_idFour = "4";
    String Product_idFive = "5";
    String Product_idSix = "6";
    String Product_idSeven = "7";
    String Product_idEight = "8";
    String Product_idNine = "9";
    String Product_idTen = "10";

    String Product_idEleven = "11";
    String Product_idTwelve = "12";
    String Product_idThirteen = "13";
    String Product_idFourteen = "14";
    String Product_idFifteen = "15";
    String Product_idSixteen = "16";
    String Product_idSeventeen = "17";
    String Product_idEighteen = "18";
    String Product_idNineteen = "19";
    String Product_idTwenty = "20";

    String Product_idTwentyOne = "21";
    String Product_idTwentyTwo = "22";
    String Product_idTwentyThree = "23";
    String Product_idTwentyFour = "24";
    String Product_idTwentyFive = "25";
    String Product_idTwentySix = "26";
    String Product_idTwentySeven = "27";
    String Product_idTwentyEight = "28";
    String Product_idTwentyNine = "29";
    String Product_idThirty = "30";

    String Product_idThirtyOne = "31";
    String Product_idThirtyTwo = "32";
    String Product_idThirtyThree = "33";
    String Product_idThirtyFour = "34";
    String Product_idThirtyFive = "35";
    String Product_idThirtySix = "36";
    String Product_idThirtySeven = "37";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dus_ka_dam);
        ButterKnife.bind(this);
    }


    private void openDialog(String product_idOne) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogbox, null);

        final EditText amount = (EditText) dialogView.findViewById(R.id.etAmount);
        TextView Cancel = (TextView) dialogView.findViewById(R.id.btn_Cancel);
        TextView Submit = (TextView) dialogView.findViewById(R.id.btnSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DusKaDamActivity.this, ""+product_idOne, Toast.LENGTH_SHORT).show();
                dialogBuilder.dismiss();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }


    @OnClick(R.id.one)
    void goOne() {
        openDialog(Product_idOne);
      //  Toast.makeText(this, ""+Product_idOne, Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.two)
    void goTwo() {
        openDialog(Product_idTwo);
     //   Toast.makeText(this, ""+Product_idTwo, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.gotoRules)
    void goToRules() {

        startActivity(new Intent(this, RulesActivity.class));
    }
}

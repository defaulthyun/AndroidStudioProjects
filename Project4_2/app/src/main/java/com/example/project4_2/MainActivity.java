package com.example.project4_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView text1,text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoDog, rdoCat, rdoRabbit;
    Button BtnOk;
    ImageView imgPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("선호하는 동물 보기");

        text1 = (TextView) findViewById(R.id.textView);
        chkAgree = (CheckBox) findViewById(R.id.checkBox);

        text2 = (TextView) findViewById(R.id.textView2);

        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoDog = (RadioButton) findViewById(R.id.radioButton);
        rdoCat = (RadioButton) findViewById(R.id.radioButton2);
        rdoRabbit = (RadioButton) findViewById(R.id.radioButton3);

        BtnOk = (Button) findViewById(R.id.button1);
        imgPet = (ImageView) findViewById(R.id.Imgpet);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkAgree.isChecked() == true){
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    BtnOk.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);

                }
                else
                {
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    BtnOk.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        BtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rGroup1.getCheckedRadioButtonId()){
                    case R.id.radioButton:
                        imgPet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.radioButton2:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.radioButton3:
                        imgPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "동뮬 먼저 선택", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


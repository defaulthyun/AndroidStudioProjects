package com.example.ch4_exercise_7;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    CheckBox ch1,ch2,ch3;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("연습문제 4-7");

        ch1 = (CheckBox) findViewById(R.id.checkBox);
        ch2 = (CheckBox) findViewById(R.id.checkBox2);
        ch3 = (CheckBox) findViewById(R.id.checkBox3);

        btn1 = (Button)  findViewById(R.id.button);

        // 체크박스 선택할때마다 버튼의 속성이 적용되도록 설정

        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch1.isChecked() == true)
                {
                    btn1.setEnabled(true);
                }
                else{
                    btn1.setEnabled(false);
                }
            }
        });

        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch2.isChecked() == true)
                {
                    btn1.setClickable(true);
                }
                else{
                    btn1.setClickable(false);
                }
            }
        });

        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch3.isChecked() == true)
                {
                    btn1.setRotation(45);
                }
                else{
                    btn1.setRotation(0);
                }
            }
        });



    }
}
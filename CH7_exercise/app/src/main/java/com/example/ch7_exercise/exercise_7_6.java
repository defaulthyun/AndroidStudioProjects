package com.example.ch7_exercise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class exercise_7_6 extends AppCompatActivity {

    RadioGroup radioGroup;
    Button btn;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_7_6);
        setTitle("연습문제 7-6");

        btn = (Button) findViewById(R.id.btn);
        radioGroup = (RadioGroup) findViewById(R.id.gr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dlg  = new AlertDialog.Builder(exercise_7_6.this);
                View  dlgView = View.inflate(exercise_7_6.this,R.layout.dialog,null);

                img = (ImageView) dlgView.findViewById(R.id.imageView1);
                dlg.setView(dlgView);

                switch (radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.rd1:
                        dlg.setTitle("강아지");
                        img.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rd2:
                        dlg.setTitle("고양이");
                        img.setImageResource(R.drawable.cat);
                        break;
                    case R.id.rd3:
                        dlg.setTitle("토끼");
                        img.setImageResource(R.drawable.rabit);
                        break;
                    case R.id.rd4:
                        dlg.setTitle("말");
                        img.setImageResource(R.drawable.ic_launcher_background);
                        break;
                }
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });

    }
}
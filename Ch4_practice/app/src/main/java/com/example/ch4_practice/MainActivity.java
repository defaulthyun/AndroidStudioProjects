package com.example.ch4_practice;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1,tv2,tv3;
        tv1 = (TextView) findViewById(R.id.textView6);
        tv2 = (TextView) findViewById(R.id.textView7);
        tv3 = (TextView) findViewById(R.id.textView8);
        
        tv1.setText("안뇽허새요 B789071_현동엽");
        tv1.setTextColor(Color.MAGENTA);
        tv2.setTextSize(30);
        tv2.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);
        tv3.setTextSize(30);
        tv3.setText("WVhwT05XUjZRbmxhUTBGNFRsTkJNR0p0VW5sTlZFSnJTVkU5UFE9PQ==");
        tv3.setSingleLine();



    }
}
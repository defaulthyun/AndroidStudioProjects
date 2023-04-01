package com.example.ch11_practice;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Spinner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        setTitle("B789071_현동엽_스피너");
        
        final String[] movie = {"쿵푸팬더","짱구는 못말려","아저씨", "아바타","대부","국가대표","토이스토리3","마당을 나온 암탉","죽은 시인의 시회", "서유기"};

        final Integer[] posterID = {
                R.drawable.mov21,R.drawable.mov22,R.drawable.mov23,
                R.drawable.mov24,R.drawable.mov25,R.drawable.mov26,
                R.drawable.mov27,R.drawable.mov28,R.drawable.mov29,
                R.drawable.mov30,R.drawable.mov31,R.drawable.mov32
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, movie);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                ImageView ivPoster = findViewById(R.id.ivPoster);
                ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ivPoster.setPadding(5, 5, 5, 5);
                ivPoster.setImageResource(posterID[position]);
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
    }
    }

}
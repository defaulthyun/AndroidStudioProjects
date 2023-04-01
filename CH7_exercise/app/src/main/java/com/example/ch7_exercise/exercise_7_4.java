package com.example.ch7_exercise;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class exercise_7_4 extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise7_4);
        setTitle("CH7_연습문제 7-4");

        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"강아지");
        menu.add(0,2,0,"고양이");
        menu.add(0,3,0,"토끼");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                imageView.setImageResource(R.drawable.dog);
                return super.onOptionsItemSelected(item);
            case 2:
                imageView.setImageResource(R.drawable.cat);
                return super.onOptionsItemSelected(item);
            case 3:
                imageView.setImageResource(R.drawable.rabit);

        }
        return false;
    }
}
package com.example.ch11_spainner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("B789071_현동엽_스피너");

        final String[] movie = {"쿵푸팬더","짱구는 못말려","아저씨", "아바타","대부","국가대표","토이스토리3","마당을 나온 암탉","죽은 시인의 시회", "서유기"};

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,movie);
        spinner.setAdapter(adapter);
    }
}
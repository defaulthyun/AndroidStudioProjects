package com.example.project_7_1_1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ImageView imgisland;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("제주도 풍경 앱_B789071_현동엽");

        imgisland = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                item.setChecked(true);
                imgisland.setImageResource(R.drawable.hanrasan);
                return true;

            case R.id.item2:
                item.setChecked(true);
                imgisland.setImageResource(R.drawable.chujado);
                return true;

            case R.id.item3:
                item.setChecked(true);
                imgisland.setImageResource(R.drawable.bumsum);
                return true;

            case R.id.subRotate:
                item.setChecked(true);
                imgisland.setRotation(Integer.parseInt(editText.getText().toString()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
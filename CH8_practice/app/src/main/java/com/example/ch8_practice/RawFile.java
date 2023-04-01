package com.example.ch8_practice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class RawFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_file);

        Button btnRead;
        final EditText edtRaw;

        btnRead = (Button) findViewById(R.id.button3);
        edtRaw = (EditText) findViewById(R.id.textView);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream inputStream = getResources().openRawResource(R.raw.raw_test);
                    byte[] txt = new byte[inputStream.available()];
                    inputStream.read(txt);
                    edtRaw.setText(new String(txt));
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
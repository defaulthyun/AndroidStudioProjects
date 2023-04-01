package com.example.ch8_practice;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SDcard_Read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard_read);

        Button btnRead;
        final EditText edtsd;

        btnRead = (Button) findViewById(R.id.btnSDread);
        edtsd = (EditText) findViewById(R.id.etdSD);


        // ActivityCompat.requestPermissions( ) 메서드를 사용하면 됩니다.
        //- 위 메서드는 사용자가 거부 / 허용을 선택할 수 있는 대화상자가 나오게 하는데, <허용>을 클릭해야 파일 등에 접근이 가능합니다.
        ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream inputStream = new FileInputStream("/sdcard/sd_test.txt");
                    byte[] txt = new byte[inputStream.available()];
                    inputStream.read(txt);
                    edtsd.setText(new String(txt));
                    inputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
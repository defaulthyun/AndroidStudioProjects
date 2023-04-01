package com.example.ch8_practice;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class FileAccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_access);

        Button btnfilelist;
        final EditText edtfilelist;

        btnfilelist = (Button) findViewById(R.id.btnFilelist);
        edtfilelist = (EditText) findViewById(R.id.edtFilelist);

        btnfilelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                File[] sysFiles = (new File(sysDir).listFiles());

                String strFname;
                for (int i = 0; i < sysFiles.length; i++){
                    if (sysFiles[i].isDirectory() == true)
                        strFname = "<폴더>" + sysFiles[i].toString();
                    else
                        strFname = "<파일>" + sysFiles[i].toString();

                    edtfilelist.setText(edtfilelist.getText() + "\n" + strFname);
                }
            }
        });
    }
}
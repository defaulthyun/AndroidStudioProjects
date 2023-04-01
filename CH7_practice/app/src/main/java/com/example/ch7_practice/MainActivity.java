package com.example.ch7_practice;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 버전 선택을 -> 배열 형식으로 만든다음 문자열 쓸 문자열 입력
                // 여럭 개를 동시 선택 시 setMultiChoiceItems() 사용
                final String[] versionArray = new String[] {"Q(10)","R(11)","S(12)"};
                final boolean[] checkArray = new boolean[]{true,false,false};

                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);

                dig.setTitle("좋아하는 버전은?");
                dig.setIcon(R.mipmap.ic_launcher);

                // 라디오 버튼 설정 0 -> setSingleChoiceItems() 사용
                dig.setMultiChoiceItems(versionArray, checkArray,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                button1.setText(versionArray[which]);
                            }
                        });

                dig.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"B789071_현동엽",Toast.LENGTH_SHORT).show();
                    }
                });
                dig.show();
            }
        });


    }
}
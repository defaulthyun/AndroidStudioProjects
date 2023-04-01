package com.example.project8_1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

//    1. activity_main.xml의 3개 위젯에 대응할 위젯 변수 3개를 선언
//    파일 이름을 지정할 문자열 변수 1개 → 파일 이름은 “연_월_일.txt”로 지정

    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("B789071_현동엽_간단 일기장");

        dp = (DatePicker) findViewById(R.id.dataPicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

//        (2) Calendar 클래스를 이용해 현재 날짜의 연/월/일을 구한 후에 데이트피커 초기화
//        (3) 데이트피커의 날짜가 변경되면 변경된 날짜에 해당하는 일기 파일(연_월_일.txt)의내용을 에디트텍스트에 보여줌

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                filename = Integer.toString(year) + "_"
                        + Integer.toString(monthofyear + 1) + "_"
                        + Integer.toString(dayofmonth) + ".txt";

                String str = readDiary(filename);
                edtDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

//        (5) 맨 아래 버튼을 클릭했을 때 동작하는 내용을 onCreate( ) 내부에 완성
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outputStream.write(str.getBytes());
                    outputStream.close();

                    Toast.makeText(getApplicationContext(),filename+" 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    (4) 현재 날짜 파일(연_월_일.txt)을 읽어 일기 내용을 반환하는 readDiary( ) 메소드 완성

    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(fName);
            byte[] txt = new byte[500];
            inputStream.read(txt);
            inputStream.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
        } 
        catch (FileNotFoundException e) {
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return diaryStr;
    }
}
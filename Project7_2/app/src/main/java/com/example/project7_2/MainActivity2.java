package com.example.project7_2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    LinearLayout baselayout;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("배경색으로 바꾸기(컨텍스트 메뉴)_JAVA");

        baselayout = findViewById(R.id.baseLayout);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        // 메뉴에 사용할 위젯 등록
        registerForContextMenu(button1);
        registerForContextMenu(button2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==button1){
            // 그룹 ID, 고유 ID, 순서 ID, 출력텍스트
            menu.add(0,1,0,"배경색(빨강)");
            menu.add(0,2,0,"배경색(초록)");
            menu.add(0,3,0,"배경색(파랑)");}
        if(v==button2){
            menu.add(0,4,0,"버튼45도회전");
            menu.add(0,5,0,"버튼 2배확대");}
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case 1:
                baselayout.setBackgroundColor(Color.RED);
                break;
            case 2:
                baselayout.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                baselayout.setBackgroundColor(Color.BLUE);
                break;
            case 4:
                button1.setRotation(45);
                break;
            case 5:
                button1.setScaleX(2);
                break;
        }
        return true;
    }
}
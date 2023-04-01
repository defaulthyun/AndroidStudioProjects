package com.example.project5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnadd, btnsub, btnmul, btndiv;
    TextView textresult;
    String num1, num2;
    Integer result;

    // 10개 숫자 버튼을 저장할 버튼 배열
    Button[] numButtons = new Button[10];

    // 10개 버튼의 id를 저장할 정수형 배열 ( Interger : 클래스 메서드, int : 정수 선언 )
    Integer[] numBtnIDs = {R.id.button, R.id.button2,R.id.button3,
            R.id.button4,R.id.button5,R.id.button6,R.id.button7,
            R.id.button9, R.id.button10};

    // 증가값으로 사용할 정수 변수
    int i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("테이블레이아웃을 이용한 계산기 B789071_현동엽");

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);

        btnadd = (Button) findViewById(R.id.button11);
        btnsub = (Button) findViewById(R.id.button12);
        btnmul = (Button) findViewById(R.id.button13);
        btndiv = (Button) findViewById(R.id.button14);

        textresult = (TextView) findViewById(R.id.textResult);

        btnadd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                textresult.setText("계산결과 : " + result.toString());

                return false;
            }
        });

        btnsub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                textresult.setText("계산결과 : " + result.toString());

                return false;
            }
        });

        btnmul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                textresult.setText("계산결과 : " + result.toString());

                return false;
            }
        });

        btndiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                textresult.setText("계산결과 : " + result.toString());

                return false;
            }
        });

        // 숫자 버튼 10개를 배열 변수에 대입한 후에 각 버튼의 클릭 이벤트 리스너를 만들기
        for (i=0; i< numBtnIDs.length; i++){
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        for (i=0; i<numBtnIDs.length; i++){
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edit1.isFocused() == true){
                        num1 = edit1.getText().toString() +
                                numButtons[index].getText().toString();
                        edit1.setText(num1);
                    }
                    else if(edit2.isFocused() == true){
                        num2 = edit2.getText().toString() +
                                numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"먼저 에디트 텍스트를 선택하세요.",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }




    }
}
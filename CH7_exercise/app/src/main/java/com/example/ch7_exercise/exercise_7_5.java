package com.example.ch7_exercise;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class exercise_7_5 extends AppCompatActivity {

    private View toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_7_5);
        setTitle("연습문제 7-5");

        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(exercise_7_5.this);
                toastView = (View)View.inflate(exercise_7_5.this,R.layout.toast,null);
                toast.setView(toastView) ;

                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int xOffest = (int) (Math.random() * display.getWidth());
                int yOffset = (int) (Math.random() + display.getHeight());

                toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffest, yOffset);
            }
        });
    }
}
package com.example.project9_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 위젯 변수 6개와 클래스 변수 1개를 전역변수로 선언
    ImageButton ibZoomin, ibZoomout, ibRotate, ibBright, ibDark, ibGray;
    MyGraphicView graphicView;
    // 축척으로 사용될 전역변수 2개를 선언
    static float scaleX=1, scaleY=1;
    // 회전 각도로 사용될 전역변수 선언
    static float angle = 0;
    // 색상 배수로 사용될 전역변수 선언
    static float color = 1;
    // 채도 배수로 사용된 전역변수 선언
    static float satur = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("B789071_현동엽_미니포토샵");

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);

        ClickIcons(); // 6개 아이콘을 클릭했을 때 수행할 기능을 메소드로 정의함
    }


    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){
            super(context);
        }


        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            // 화면(뷰)의 중앙을 구하고
            // 전역변수에 설정된 값으로 캔버스의 축척을 설정

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            // 전역변수에 설정된 각도로 캔버스를 회전시킴
            canvas.rotate(angle,cenX,cenY);

            Paint paint = new Paint();
            float[] array = {
                    color, 0, 0, 0, 0,
                    0, color, 0, 0, 0,
                    0, 0, color, 0, 0,
                    0, 0, 0, 1, 0
            };

            ColorMatrix cm = new ColorMatrix(array);

            ColorMatrix grayScaleMatrix = new ColorMatrix();

            // setSaturation( ) 메서드가 실행되면 위에 설정된 ColorMatrix
            if(satur == 0)
                grayScaleMatrix.setSaturation(0);

            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            paint.setColorFilter(new ColorMatrixColorFilter(grayScaleMatrix));

            Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.lena256);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture,picX,picY,paint);
            picture.recycle();
        }
    }

    private void ClickIcons() {

        // 확대아이콘 클릭리스너
        ibZoomin = (ImageButton) findViewById(R.id.imageButton);
        ibZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
            }
        });

        // 축소아이콘 클릭리스너
        ibZoomout = (ImageButton) findViewById(R.id.imageButton2);
        ibZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
            }
        });

        // 회전아이콘 클릭리스너
        ibRotate = (ImageButton) findViewById(R.id.imageButton3);
        ibRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle = angle + 20;
                graphicView.invalidate();
            }
        });

        // 밝기아이콘 클릭리스너
        ibBright = (ImageButton) findViewById(R.id.imageButton4);
        ibBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = color + 0.2f;
                graphicView.invalidate();
            }
        });

        // 어둡게아이콘 클릭리스너
        ibDark = (ImageButton) findViewById(R.id.imageButton5);
        ibDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = color - 0.2f;
                graphicView.invalidate();
            }
        });

        // 흑백아이콘 클릭리스너
        ibGray = (ImageButton) findViewById(R.id.imageButton6);
        ibGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (satur == 0)
                    satur = 1;
                else
                    satur = 0;
                graphicView.invalidate();
            }
        });
    }
}
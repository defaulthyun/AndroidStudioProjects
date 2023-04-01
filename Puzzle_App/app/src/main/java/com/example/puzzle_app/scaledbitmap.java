package com.example.puzzle_app;

import static android.graphics.Bitmap.createScaledBitmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;

public class scaledbitmap extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("B789071_퍼즐앱");
    }

    class MyGraphicView extends View {

        // 뷰 화면의 너버와 높이
        int width, height;

        // 좌측 우측 여백
        int left, top;

        // 원본 이미지의 너비 높이
        int orgW, orgH;

        // 퍼즐 조각 이미지의 너비 높이
        int picW, picH;

        // 원본, 퍼즐 구별
        Bitmap imgBack, imgOrg;

        // 2차원 배열
        Bitmap[][] imgPic = new Bitmap[3][3];

        public MyGraphicView(Context context) {

            super(context);

            Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

            width = display.getWidth();
            height = display.getHeight()-50;

            imgBack = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
            imgBack = createScaledBitmap(imgBack,width,height,false );

            imgOrg = BitmapFactory.decodeResource(getResources(),R.drawable.lena256);
            imgOrg = createScaledBitmap(imgOrg,width-width/20,height-height/20,false);

            orgW = imgOrg.getWidth();
            orgH = imgOrg.getHeight();

            picW = orgW / 3;
            picH = orgH / 3;

            left = (width-orgW)/2;
            top = (width-orgH)/2;

            // 사진 자르기
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    imgPic[i][j] = Bitmap.createBitmap(imgOrg,j*picW,i*picH,picW,picH);
                }
            }

            // 마지막 칸은 공란
            imgPic[2][1] = Bitmap.createBitmap(imgOrg,0,0,1,1);
        }

        @SuppressLint("DrawAllocation")
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            EmbossMaskFilter eMask;

            eMask = new EmbossMaskFilter(new float[]{3,3,3}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);

            canvas.drawBitmap(imgBack,0,0,null);

            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    canvas.drawBitmap(imgPic[i][j], left+j*picW,top+i*picH,paint);
                }
            }

        }
    }
}
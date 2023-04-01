package com.example.ch9_practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class bitmap_9_6 extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this) );
        setTitle("B789071_현동엽_예제9-6");
    }

    private static class MyGraphicView extends View{
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.jeju14);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            // canvas.rotate(45,cenX,cenY);
            // canvas.translate(-150,200);
            // canvas.scale(2,2,cenX,cenY);
            // canvas.skew(0.3f,0.3f);

            canvas.drawBitmap(picture,picX,picY,null);
            picture.recycle();
        }
    }
}

package com.example.project10_3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("B789071_현동엽_실습10-3_로그캣");
        android.util.Log.i("로그캣 테스트","OnCreate()");

        Button btnDial = (Button) findViewById(R.id.button);
        Button btnFinal = (Button) findViewById(R.id.button2);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:/012345678");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        android.util.Log.i("로그캣 테스트","onDestory()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        android.util.Log.i("로그캣 테스트","onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        android.util.Log.i("로그캣 테스트","onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        android.util.Log.i("로그캣 테스트","onPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        android.util.Log.i("로그캣 테스트","onStop()");
    }

}
package com.example.project7_3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    Button button;
    EditText digEdtName, digEdtEmail;
    TextView toastText;
    View dialogView, toastView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this,
                        R.layout.dialog1, null);

                AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);

                dig.setTitle("사용자 입력");

                dig.setView(dialogView);

                dig.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        digEdtName = (EditText) dialogView.findViewById(R.id.digEdit1);
                        digEdtEmail = (EditText) dialogView.findViewById(R.id.digEdit2);

                        tvName.setText(digEdtName.getText().toString());
                        tvEmail.setText(digEdtEmail.getText().toString());
                    }
                });

                dig.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast  = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this,
                                R.layout.toast1,null);

                        toastText = (TextView) toastView.findViewById(R.id.toastText1);

                        toastText.setText("취소했습니다");
                        toast.setView(toastView);
                        toast.show();

                    }
                });
                dig.show();

            }
        });

    }
}
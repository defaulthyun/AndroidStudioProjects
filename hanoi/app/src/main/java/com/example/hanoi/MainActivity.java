package com.example.hanoi;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // 레이아웃에 뷰의 ID 저장
    ArrayList<Integer> layoutOneList;
    ArrayList<Integer> layoutTwoList;
    ArrayList<Integer> layoutThreeList;

    int counter;
    TextView countText;
    TextView winnerText;
    TextView textUpdateTime;
    Timer timer;
    double time;

    Button startButton;
    boolean startButtonPressed = false;

    // 레이아웃 설정
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;

    // 그림 뷰 Ring 설정
    View bigRed;
    View mediumBlue;
    View smallGreen;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("B789071_현동엽_하노이 탑");

        time = 0.0;
        counter = 0;

        // 레이아웃 설정
        layout1 = findViewById(R.id.layout_01);
        layout2 = findViewById(R.id.layout_02);
        layout3 = findViewById(R.id.layout_03);

        // 드래그 할 레이아웃 설정
        layout1.setOnDragListener(new MyDragListener());
        layout2.setOnDragListener(new MyDragListener());
        layout3.setOnDragListener(new MyDragListener());

        // 링 설정
        bigRed = findViewById(R.id.big_red);
        mediumBlue = findViewById(R.id.medium_blue);
        smallGreen = findViewById(R.id.small_green);

        // 카운트 설정
        countText = findViewById(R.id.count);


        // 타이머 설정
        textUpdateTime = findViewById(R.id.time);
        timer = new Timer();

        // 시작버튼
        startButton = findViewById(R.id.startButton);

        if(savedInstanceState != null){

            // 시작버튼 클릭 시
            startButton.performClick();

            // 카운트 시작
            int countString = savedInstanceState.getInt("count");
            counter = countString;
            countText.setText(Integer.toString(countString));

            // 타이머 시간 시작 저장
            time = savedInstanceState.getDouble("time");

            // 시간 형식을 지정하여 텍스트로 입력
            textUpdateTime.setText(getTimerText(time));

            // 고리 위치 저장
            ArrayList<Integer> savedLayoutOne = savedInstanceState.getIntegerArrayList("layoutOne");
            ArrayList<Integer> savedLayoutTwo = savedInstanceState.getIntegerArrayList("layoutTwo");
            ArrayList<Integer> savedLayoutThree = savedInstanceState.getIntegerArrayList("layoutThree");

            for (Integer viewId : savedLayoutOne) {
                View tempViw = findViewById(viewId);
                ((ViewGroup) tempViw.getParent()).removeView(tempViw);
                layout1.addView(tempViw);
            }
            for (Integer viewId : savedLayoutTwo) {
                View tempViw = findViewById(viewId);
                ((ViewGroup) tempViw.getParent()).removeView(tempViw);
                layout2.addView(tempViw);
            }
            for (Integer viewId : savedLayoutThree) {
                View tempViw = findViewById(viewId);
                ((ViewGroup) tempViw.getParent()).removeView(tempViw);
                layout3.addView(tempViw);
            }

        }
    }

    // 시작버튼 누를 시
    @SuppressLint("SetTextI18n")
    public void startButton(View view){
        if(startButtonPressed){
            reset();
        }

        else{
            counter = 0;
            int countString = counter;
            countText.setText(Integer.toString(countString));
            startButtonPressed = true;
            startButton.setText("리셋");

            // 타이머 시작
            startTimer();

            // 터치리스너 추가
            bigRed.setOnTouchListener(new MyTouchListener());
            mediumBlue.setOnTouchListener(new MyTouchListener());
            smallGreen.setOnTouchListener(new MyTouchListener());
        }
    }

    // 재설정 버튼을 눌렀을 때의 재설정 방법으로, 시작과 동일한 버튼
    @SuppressLint("SetTextI18n")
    private void reset() {

        startButtonPressed = false;
        startButton.setText("시작");

        // 타이머 리셋
        timer.cancel();
        time = 0.0;
        timer = new Timer();
        textUpdateTime.setText("00:00:00");
        winnerText.setText("");

        // 레이아웃 리셋
        layout1.removeAllViews();
        layout2.removeAllViews();
        layout3.removeAllViews();

        // 레이아웃1 다시 롤백
        layout1.addView(smallGreen);
        layout2.addView(mediumBlue);
        layout3.addView(bigRed);

        // 터치리스너 제거
        smallGreen.setOnTouchListener(null);
        mediumBlue.setOnTouchListener(null);
        bigRed.setOnTouchListener(null);
    }


    // 애플리케이션에서 끌어서 놓기 사용  : https://www.vogella.com/tutorials/AndroidDragAndDrop/article.html
    private static final class MyTouchListener implements View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("test","test");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data,shadowBuilder,view,0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
            else{
                return false;
            }
        }
    }
    // 애플리케이션에서 끌어서 놓는 부분 : https://www.vogella.com/tutorials/AndroidDragAndDrop/article.html
    class MyDragListener implements View.OnDragListener {
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable normalShape = getResources().getDrawable(R.drawable.t_big);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    notSmallestView(v, event);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // 드롭 시 뷰 그룹 재설정 되야 됨
                    bigSmallRule(v, event);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    // 뷰 그룹에서 가장 작은 뷰가 아닐시, 빨간색 테두리 표시 안 함
    @SuppressLint("UseCompatLoadingForDrawables")
    public void notSmallestView(View v, DragEvent event) {
        View view = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) view.getParent();

        if(view != owner.getChildAt(0)){
            v.setBackground(getResources().getDrawable(R.drawable.t_big));
        }
        else{
            v.setBackground(getResources().getDrawable(R.drawable.t_big_red_border));
        }
    }

    // 규칙 처리 부분
    public void bigSmallRule(View v, DragEvent event){
        View view = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) view.getParent();

        LinearLayout container = (LinearLayout) v;
        view.setVisibility(View.VISIBLE);

        // 뷰를 넣은 레이아웃이 비어 있는 경우
        if(container.getChildCount() == 0){
            // 먼저 사용자에게서 가져온 뷰가 가장 위의 원판(index : 0)에서 가져온 것인지 확인합니다.
            if(owner.getChildAt(0) != view) {
                // 링이 맨 위로 올라간 경우 가장 위의 원판인지 확인
                whenNotIndexZero(v, event);
            }
            //레이아웃이 비어 있으면 뷰를 추가합니다.
            else{
                owner.removeView(view);
                container.addView(view,0);
                count(); // 카운트에 1 추가
            }
        }
        // 뷰를 넣은 레이아웃이 비어 있지 않은 경우
        if(container.getChildCount() != 0){
            if(owner.getChildAt(0) != view){
                whenNotIndexZero(v,event);
            }
            // 새 레이아웃에 넣은 뷰가 이미 레이아웃의 맨 위에 있는 뷰보다 큰지 확인합니다.
            else if(view.getPaddingLeft() > container.getChildAt(0).getPaddingLeft()){
                //사용자에게서 가져온 뷰가 가장 위의 원판에서 가져온 것인지 확인.
                owner.removeView(view);
                container.addView(view,0);
                count(); // 카운트에 1 추가
            }
        }
        // 오른쪽에 다 채워질 시 게임 종료 Toast 메시지 출력
        winner(v,event);
    }
    
    // 링이 맨 위로 올라간 경우 가장 위의 원판인지 확인
    private void whenNotIndexZero(View v, DragEvent event) {
        View view = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) view.getParent();

        LinearLayout container = (LinearLayout) v;

        // 해당 차일드뷰가 화면에 보여지는 아이템들 중 몇번째인지를 반환
        int indexView = owner.indexOfChild(view);

        // 가장 위 원판이 아닐 시 작은 원판이니 올라온다
        if(owner.getChildAt(0) != view){
            if (owner == container){
                container.removeView(view);
                owner.addView(view,indexView);
            }
            // 가장 위 원판 일 시 작은 원판은 못 올린다.
           else{
                container.removeView(view);
            }
        }
    }

    // 맨 오른쪽 기둥에 모든 원판이 이동이 완료 시 "게임종료" 메시지 출력
    @SuppressLint("ResourceType")
    public void winner(View v, DragEvent event){
        LinearLayout layout3 = findViewById(R.id.layout_03);
        View view = (View) event.getLocalState();
        LinearLayout container = (LinearLayout) v;

        //SmallGreen = 2131231025(bigest), MediumBlue = 2131230925(medium), BigRed = 2131230805(smallest)
        if(layout3.getChildCount() == 3 && container.getChildCount() == 3 &&
                (Integer) container.getChildAt(0).getId() == 2131231025 &&
                (Integer) container.getChildAt(1).getId() == 2131230925 &&
                (Integer) container.getChildAt(2).getId() == 2131230805)
        {
            // 게임 종료 시 다시 초기화
            Toast.makeText(getApplicationContext(),"게임종료",0).show();
            timer.cancel();
            timer = new Timer();
            smallGreen.setOnTouchListener(null);
            mediumBlue.setOnTouchListener(null);
            bigRed.setOnTouchListener(null);
        }
    }

    @SuppressLint("SetTextI18n")
    public void count(){
        counter++;
        int countString = counter;
        countText.setText(Integer.toString(countString));
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time++;
                runOnUiThread(() -> textUpdateTime.setText(getTimerText(time)));
            }
        }, 0, 1000);

    }

    private String getTimerText(double time) {
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        return formatTime(seconds, minutes, hours);
    }

    @SuppressLint("DefaultLocale")
    private String formatTime(int seconds, int minutes, int hours) {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        //링 배치 저장(레이아웃만 저장), 시간 절약, 카운트 저장
        outState.putInt("count",counter);
        outState.putDouble("time",time);

        // 링 위치 저장
        statusLayouts();
        outState.putIntegerArrayList("layoutOne", layoutOneList);
        outState.putIntegerArrayList("layoutTwo", layoutTwoList);
        outState.putIntegerArrayList("layoutThree", layoutThreeList);
    }

    private void statusLayouts() {
        //세 가지 선형 레이아웃에 포함된 요소를 나열
        layoutOneList = new ArrayList<>();
        layoutTwoList = new ArrayList<>();
        layoutThreeList = new ArrayList<>();

        LinearLayout layoutOne = findViewById(R.id.layout_01);
        LinearLayout layoutTwo = findViewById(R.id.layout_02);
        LinearLayout layoutThree = findViewById(R.id.layout_03);

        for (int i = 0; i < layoutOne.getChildCount(); i++) {
            layoutOneList.add(layoutOne.getChildAt(i).getId());
        }
        for (int i = 0; i < layoutTwo.getChildCount(); i++) {
            layoutTwoList.add(layoutTwo.getChildAt(i).getId());
        }
        for (int i = 0; i < layoutOne.getChildCount(); i++) {
            layoutThreeList.add(layoutThree.getChildAt(i).getId());
        }
    }


}
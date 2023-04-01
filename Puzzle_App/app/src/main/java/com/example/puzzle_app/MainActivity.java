package com.example.puzzle_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public int num = 3; // 3*3 퍼즐이므로 초기값은 3
    protected Bitmap[] oriPuzzlePiece; // 이미지 비트맵 배열 선언
    protected PuzzlePiece[] puzzlePiece; // 퍼즐 정답 형태 배열 선언
    protected PuzzlePiece[] shufflePiece; // shuffle했을 때 배열 선언
    int[][] board; // 3*3 형태 위치를 찾기 위한 보드판 -> 2차원배열 설정
    int loc = 0; // 위치 좌표 초기값 : 0으로 설정
    int x = 0, y = 0; // 보드판에서 좌표 x,y
    private boolean answer = false; // 정답 체크
    private boolean click = false; // shuffle버튼 눌렀는지 체크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("B789071_현동엽_슬라이딩_퍼즐앱");

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
        cutImage(image);

        // 초기값 3*3
        puzzlePiece = new PuzzlePiece[num*num];
        shufflePiece = new PuzzlePiece[num*num];

        // 보드판 위치 설정
        board = new int[num][num];
        for(int i = 0; i < num; i++) {
            for(int j = 0; j < num; j++, loc++)
                board[i][j] = loc;
        }

        // 퍼즐 조각으로 나누기
        for(int i = 0; i < num*num; i++) {
            puzzlePiece[i] = new PuzzlePiece(oriPuzzlePiece[i], i);
        }

        // 나누는 형태를 그리드뷰로 저장 -> activity_main에 그리드 뷰 설정해야 됨
        GridView gridView = (GridView) findViewById(R.id.GridView);
        PuzzleAdapter adapter = new PuzzleAdapter(this, image, num, puzzlePiece);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(num);

        // 버튼 연결
        Button init = (Button) findViewById(R.id.init);
        Button shuffle = (Button) findViewById(R.id.shuffle);

        // 버튼 클릭하면 원상태로 복구
        init.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                num = 3;
                loc = 0;
                cutImage(image);
                puzzlePiece = new PuzzlePiece[num*num];
                shufflePiece = new PuzzlePiece[num*num];

                for(int i = 0; i < num*num; i++) {
                    puzzlePiece[i] = new PuzzlePiece(oriPuzzlePiece[i], i);
                }

                board = new int[num][num];
                for(int i = 0; i < num; i++) {
                    for(int j = 0; j < num; j++, loc++)
                        board[i][j] = loc;
                }

                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, puzzlePiece);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);
            }
        });

        shuffle.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                click = true; // shuffle을 눌렀음을 표시
                answer = false;

                PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, puzzlePiece);
                Collections.shuffle(Arrays.asList(adapter.newPuzzlePiece));
                gridView.setAdapter(adapter);
                gridView.setNumColumns(num);

                // shuffle 눌렀을 때의 퍼즐 순서 저장
                for(int i = 0; i < adapter.newPuzzlePiece.length; i++) {
                    shufflePiece[i] = new PuzzlePiece(adapter.newPuzzlePiece[i].getImagePiece(), adapter.newPuzzlePiece[i].getIndex());
                }
            }
        });


        // 퍼즐을 클릭했을 때의 동작
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int clickLocation; // 클릭한 퍼즐 위치
            int blank; // 빈칸 위치

            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                clickLocation = position;
                blank = shufflePiece.length - 1;

                // shuffle버튼을 누르고, 정답이 아닐 경우에만 클릭 가능 -> 무작위로 정답 형태로 만들 수 없는 경우가 있다고 함
                if(click && !answer) {
                    // 클릭한 퍼즐 좌표 찾기
                    for(int h = 0; h < num; h++) {
                        for(int w = 0; w < num; w++){
                            if(board[h][w] == clickLocation) {
                                y = h;
                                x = w;
                                break;
                            }
                        }
                    }

                    // 위쪽이 빈칸은 경우
                    if(y - 1 >= 0 && shufflePiece[board[y-1][x]].getIndex() == blank){
                        swapPiece(clickLocation, board[y-1][x]);
                        checkAnswer();
                    }
                    // 아래쪽이 빈칸인 경우
                    else if(y + 1 < num  && shufflePiece[board[y+1][x]].getIndex() == blank) {
                        swapPiece(clickLocation, board[y+1][x]);
                        checkAnswer();
                    }
                    // 오른쪽이 빈칸인 경우
                    else if(x + 1 < num && shufflePiece[board[y][x+1]].getIndex() == blank){
                        swapPiece(clickLocation, board[y][x+1]);
                        checkAnswer();
                    }
                    // 왼쪽이 빈칸인 경우
                    else if(x - 1 >= 0 && shufflePiece[board[y][x-1]].getIndex() == blank){
                        swapPiece(clickLocation, board[y][x-1]);
                        checkAnswer();
                    }
                    // 주변에 빈칸이 없는 공간을 클릭했을 경우는 동작 안함 (

                    // 클릭한 후, 이동한 퍼즐을 화면에 표시
                    PuzzleAdapter adapter = new PuzzleAdapter(getApplicationContext(), image, num, shufflePiece);
                    gridView.setAdapter(adapter);
                    gridView.setNumColumns(num);

                    // 정답이라면 Toast메시지 출력후, 더 이상 클릭 불가!
                    if(answer)
                        Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // 이미지 비트맵 분할
    public void cutImage(Bitmap image) {
        int x, y;
        int i = 0;
        oriPuzzlePiece = new Bitmap[num * num];

        for (y = 0; y <= (image.getHeight() - image.getHeight() / num); y += image.getHeight() / num) {
            for (x = 0; x <= (image.getWidth() - image.getWidth() / num); x += image.getWidth() / num, i++) {
                // 마지막 공란이어야 되므로 3*3 - 1 = 8칸 생성
                if(i == num*num-1)
                    break;
                oriPuzzlePiece[i] = Bitmap.createBitmap(image, x, y, image.getWidth()/ num, image.getHeight() / num);
            }
        }
    }

    // 정답인지 체크
    private void checkAnswer() {

        for(int i = 0; i < num*num; i++) {
            if(shufflePiece[i].getIndex() == i)
                continue;
            else {
                answer = false;
                return;
            }
        }
        answer = true;
    }

    // 퍼즐 위치 swap
    public void swapPiece(int loc1, int loc2){
        PuzzlePiece tmp1 = shufflePiece[loc1]; // 클릭한 위치
        PuzzlePiece tmp2 = shufflePiece[loc2]; // 변경할 위치

        System.out.println("tmp1: " + tmp1.getIndex());
        System.out.println("tmp2: " + tmp2.getIndex());

        shufflePiece[loc1] = tmp2;
        shufflePiece[loc2] = tmp1;
    }
}
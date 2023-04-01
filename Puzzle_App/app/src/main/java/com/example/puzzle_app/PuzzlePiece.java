package com.example.puzzle_app;

import android.graphics.Bitmap;


// 퍼즐 조각으로 나눈 뒤 인덱스로 설정
public class PuzzlePiece {
    private Bitmap imagePiece;
    private int index;

    public PuzzlePiece(Bitmap imagePiece, int num) {
        this.imagePiece= imagePiece;
        this.index = num;
    }

    public Bitmap getImagePiece() {
        return imagePiece;
    }

    public void setImagePiece(Bitmap imagePiece) {
        this.imagePiece = imagePiece;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

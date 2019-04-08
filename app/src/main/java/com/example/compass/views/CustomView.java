package com.example.compass.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
//    constants
    private static final float BIG_CIRCLE_SIZE = 500f; // tamaño del circulo grande
    private static final int BIG_TEXT_SIZE = 100; // tamaño del circulo interno
    private static final int SMALL_TEXT_SIZE = 30; // tamaño de los grados en la circunsferencia
    private static final int DEGREES_ADVANCE = 10; // cada cuantos grados realizar un texto en la circunsferencia

    private static final String MY_RED = "#e24141";
    private static final String MY_GRAY = "#8a94a3";

    private Paint mPaintText;
    private Paint mPaintCircle;
    private Paint mPaintSmallCircle;

    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {

//        big circle
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.parseColor(MY_GRAY));

//        inner circle
        mPaintSmallCircle = new Paint();
        mPaintSmallCircle.setAntiAlias(true);
        mPaintSmallCircle.setColor(Color.BLACK);

//        inner Text
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Float cx, cy;
        float radius = BIG_CIRCLE_SIZE;

        cx = getWidth() / 2f;
        cy = getHeight() / 2f;

//        circulo externo
        canvas.drawCircle(cx, cy, radius, mPaintCircle);

//        circulo interno
        canvas.drawCircle(cx, cy, radius - 200, mPaintSmallCircle);

//        escribir N, E, W o S  o el g° correspondiente en la circunsferencia
        for (int i = 0; i < 360; i += DEGREES_ADVANCE){
            if (i == 0) {
                mPaintText.setTextSize(BIG_TEXT_SIZE);
                mPaintText.setColor(Color.parseColor(MY_RED));
                canvas.drawText("N", cx - (mPaintText.measureText("N") / 2), cy - radius + mPaintText.getTextSize(), mPaintText);
            } else if (i == 90) {
                mPaintText.setTextSize(BIG_TEXT_SIZE);
                canvas.drawText("E", cx - (mPaintText.measureText("E") / 2), cy - radius + mPaintText.getTextSize(), mPaintText);
            } else if (i == 180) {
                mPaintText.setTextSize(BIG_TEXT_SIZE);
                canvas.drawText("S", cx - (mPaintText.measureText("S") / 2), cy - radius + mPaintText.getTextSize(), mPaintText);
            } else if (i == 270) {
                mPaintText.setTextSize(BIG_TEXT_SIZE);
                canvas.drawText("W", cx - (mPaintText.measureText("W") / 2), cy - radius + mPaintText.getTextSize(), mPaintText);
            } else {
                mPaintText.setColor(Color.BLACK);
                mPaintText.setTextSize(SMALL_TEXT_SIZE);
                canvas.drawText(String.valueOf(i), cx - (mPaintText.measureText(String.valueOf(i)) / 2), cy - radius + mPaintText.getTextSize(), mPaintText);
            }

//            por cada vez que se ejecuta el for loop rotar el customView 10 grados
            canvas.rotate(DEGREES_ADVANCE, cx, cy);
        }
    }
}

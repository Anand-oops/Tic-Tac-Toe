package com.example.android.tictactoe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class player_game extends View {

    Paint line = new Paint();
    int player=0,box_number=0;
    char symbol;
    boolean[][] box_state = new boolean[3][3];
    char[][] input=new char[3][3];
    ArrayList<Input> input_holder = new ArrayList<>();

    public player_game(Context context) {
        super(context);
    }

    public player_game(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public player_game(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        line.setColor(Color.BLACK);
        line.setStyle(Paint.Style.STROKE);
        line.setStrokeWidth(20);
        line.setStrokeCap(Paint.Cap.ROUND);
        line.setAntiAlias(true);

        canvas.drawLine(getWidth() / 2f - 100, getHeight() / 2f - 300, getWidth() / 2f - 100, getHeight() / 2f + 300, line);    //vertical line
        canvas.drawLine(getWidth() / 2f + 100, getHeight() / 2f - 300, getWidth() / 2f + 100, getHeight() / 2f + 300, line);    //vertical line
        canvas.drawLine(getWidth() / 2f - 300, getHeight() / 2f - 100, getWidth() / 2f + 300, getHeight() / 2f - 100, line);    //horizontal line
        canvas.drawLine(getWidth() / 2f - 300, getHeight() / 2f + 100, getWidth() / 2f + 300, getHeight() / 2f + 100, line);    //horizontal line

        box_number=0;
        for (int i = 0; i < input_holder.size(); i++) {
            canvas.drawText(String.valueOf(input_holder.get(i).symbol), input_holder.get(i).start_x, input_holder.get(i).start_y, input_holder.get(i).color);
            box_number++;
        }

        if(box_number<9)
            invalidate();
        else{
            result('y');
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x_touch = event.getX(), y_touch = event.getY();
            float cx = getWidth() / 2f - 300, cy = getHeight() / 2f - 300;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (x_touch >= cx && x_touch < cx + 200 && y_touch >= cy && y_touch < cy + 200) {
                        if (!box_state[i][j]) {
                            box_state[i][j]=true;
                            if(player%2==0) symbol='X';
                            else symbol='O';
                            input[i][j]=symbol;
                            input_holder.add(new Input(symbol,getWidth() / 2f - 300 + (2 * j + 1) * 100 - 30, getHeight() / 2f - 300 + (2 * i + 1) * 100 + 30));
                            check(i,j);
                            player++;   player%=2;
                        }
                    }
                    cx += 200;
                }
                cx = getWidth() / 2f - 300;
                cy += 200;
            }
        }
        return value;
    }

    public void check(int i,int j){
        if(i==0){
            if(j==0){
                if(input[i][j]==input[i][j+1] && input[i][j]==input[i][j+2])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j+1] && input[i][j]==input[i+2][j+2])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j] && input[i][j]==input[i+2][j])
                    result(input[i][j]);
            }else if(j==1){
                if(input[i][j]==input[i][j+1] && input[i][j]==input[i][j-1])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j] && input[i][j]==input[i+2][j])
                    result(input[i][j]);
            }else if(j==2){
                if(input[i][j]==input[i][j-1] && input[i][j]==input[i][j-2])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j] && input[i][j]==input[i+2][j])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j-1] && input[i][j]==input[i+2][j-2])
                    result(input[i][j]);
            }
        }else if(i==1){
            if(j==0){
                if(input[i][j]==input[i][j+1] && input[i][j]==input[i][j+2])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j] && input[i][j]==input[i-1][j])
                    result(input[i][j]);
            }else if(j==1){
                if(input[i][j]==input[i][j+1] && input[i][j]==input[i][j-1])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j+1] && input[i][j]==input[i-1][j-1])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j] && input[i][j]==input[i-1][j])
                    result(input[i][j]);
                if(input[i][j]==input[i-1][j+1] && input[i][j]==input[i+1][j-1])
                    result(input[i][j]);
            }else if(j==2){
                if(input[i][j]==input[i][j-1] && input[i][j]==input[i][j-2])
                    result(input[i][j]);
                if(input[i][j]==input[i+1][j] && input[i][j]==input[i-1][j])
                    result(input[i][j]);
            }
        }else if(i==2){
            if(j==0){
                if(input[i][j]==input[i][j+1] && input[i][j]==input[i][j+2])
                    result(input[i][j]);
                if(input[i][j]==input[i-1][j] && input[i][j]==input[i-2][j])
                    result(input[i][j]);
                if(input[i][j]==input[i-1][j+1] && input[i][j]==input[i-2][j+2])
                    result(input[i][j]);
            }else if(j==1){
                if(input[i][j]==input[i][j+1] && input[i][j]==input[i][j-1])
                    result(input[i][j]);
                if(input[i][j]==input[i-2][j] && input[i][j]==input[i-1][j])
                    result(input[i][j]);
            }else if(j==2){
                if(input[i][j]==input[i][j-1] && input[i][j]==input[i][j-2])
                    result(input[i][j]);
                if(input[i][j]==input[i-2][j-2] && input[i][j]==input[i-1][j-1])
                    result(input[i][j]);
                if(input[i][j]==input[i-2][j] && input[i][j]==input[i-1][j])
                    result(input[i][j]);
            }
        }
    }

    public void result(final char c){
        if(c=='y') {
            MediaPlayer song= MediaPlayer.create(getContext(),R.raw.draw);
            song.start();
            Toast.makeText(getContext(), "It's a Draw", Toast.LENGTH_SHORT).show();
        }
        else if(c=='X') {
            MediaPlayer song= MediaPlayer.create(getContext(),R.raw.x_win);
            song.start();
            Toast.makeText(getContext(),c+" wins",Toast.LENGTH_SHORT).show();
        }
        else {
            MediaPlayer song= MediaPlayer.create(getContext(),R.raw.o_win);
            song.start();
            Toast.makeText(getContext(),c+" wins",Toast.LENGTH_SHORT).show();
        }
        Vibrator vibe = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        assert vibe != null;
        vibe.vibrate(80);
        Activity activity=(Activity) getContext();
        activity.finish();
    }
}

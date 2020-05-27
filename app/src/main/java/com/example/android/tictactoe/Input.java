package com.example.android.tictactoe;

import android.graphics.Color;
import android.graphics.Paint;

class Input {
    float start_x,start_y;
    Paint color=new Paint();
    char symbol;
    Input(char c,float x1,float y1){
        symbol=c;
        start_x=x1;
        start_y=y1;
        if(symbol=='X')
            color.setColor(Color.RED);
        else color.setColor(Color.BLUE);
        color.setTextSize(80);
    }

}

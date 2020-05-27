package com.example.android.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class game extends AppCompatActivity {

    String player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

    }

    @SuppressLint("SetTextI18n")
    public void start_game(View view) {

        EditText text;
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        text = findViewById(R.id.player1name);
        player1 = text.getText().toString();

        text = findViewById(R.id.player2name);
        player2 = text.getText().toString();

        if (player1.equals("") || player2.equals("")) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            assert vibe != null;
            vibe.vibrate(80);
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            text = findViewById(R.id.player1name);
            text.setVisibility(View.GONE);

            text = findViewById(R.id.player2name);
            text.setVisibility(View.GONE);

            view = findViewById(R.id.start_button);
            view.setVisibility(View.GONE);

            TextView textView;

            textView = findViewById(R.id.player1);
            textView.setText(player1 + "  X");

            textView = findViewById(R.id.player2);
            textView.setText(player2 + "  O");

            view = findViewById(R.id.name_view);
            view.setVisibility(View.VISIBLE);

            view = findViewById(R.id.player_game);
            view.setVisibility(View.VISIBLE);
        }
    }
}

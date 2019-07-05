package com.cryptoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity {
    final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private boolean title_is_shifted = false;
    private TextView title;
    private Button cipher_button, puzz_button, hist_button, learn_button;
    private Intent cipher_intent, puzz_intent, hist_intent, learn_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Set up title animation.
        title = findViewById(R.id.start_activity_title);
        title.setKeyListener(null); // Disable editing of title.
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title_is_shifted) {
                    title.setText(R.string.start_activity_title);
                    title.setTextColor(getResources().getColor(R.color.popOfOrange, getTheme()));
                }
                else {
                    title.setText(randomShift(getString(R.string.start_activity_title)));
                    title.setTextColor(getResources().getColor(R.color.popOfGreen, getTheme()));
                }
                title_is_shifted = !title_is_shifted;
            }
        });

        // Set up buttons to go to new activities.
        cipher_button = findViewById(R.id.cipher_button);
        cipher_intent = new Intent(this, CipherActivity.class);
        cipher_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //TODO: create new activity for ciphers.
                startActivity(cipher_intent);
            }
        });
        puzz_button = findViewById(R.id.puzz_button);
        puzz_intent = new Intent(this, PuzzleActivity.class);
        puzz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create new activity for cryptograms.
                startActivity(puzz_intent);
            }
        });
        hist_button = findViewById(R.id.hist_button);
        hist_intent = new Intent(this, HistoryActivity.class);
        hist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create a new activity for the history of cryptography. (include booklist?)
                startActivity(hist_intent);
            }
        });
        learn_button = findViewById(R.id.learn_button);
        learn_intent = new Intent(this, LearnActivity.class);
        learn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: create a new activity to teach some crypto parts of math. (modeled after brilliant.org)
                startActivity(learn_intent);
            }
        });
    }

    private String randomShift(String message) {
        int rand_num = (int)Math.floor(Math.random() * 27);

        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);

            if(UPPERCASE_LETTERS.contains("" + c)) {
                char newChar = UPPERCASE_LETTERS.charAt((UPPERCASE_LETTERS.indexOf(c) + rand_num) % 26);
                message = message.substring(0, i) + newChar + message.substring(i+1);
            }
            else if(LOWERCASE_LETTERS.contains("" + c)) {
                char newChar = LOWERCASE_LETTERS.charAt((LOWERCASE_LETTERS.indexOf(c) + rand_num) % 26);
                message = message.substring(0, i) + newChar + message.substring(i+1);
            }
        }

        return message;
    }
}

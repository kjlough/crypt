package com.cryptoapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends Activity {
    public boolean title_is_shifted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final TextView title = findViewById(R.id.start_activity_title);
        title.setKeyListener(null); // Disable editing of title.
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title_is_shifted) {
                    title.setText(R.string.start_activity_title);
                    title.setTextColor(getResources().getColor(R.color.popOfOrange, getTheme()));
                }
                else {
                    title.setText("Max Vkrimh Tii"); // TODO: change this to an actual random shift cipher!
                    title.setTextColor(getResources().getColor(R.color.popOfGreen, getTheme()));
                }

                title_is_shifted = !title_is_shifted;
            }
        });
    }
}

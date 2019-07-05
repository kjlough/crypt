package com.cryptoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Locale;

public class CipherActivity extends Activity {
    final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    EditText plaintext, ciphertext, shiftAmtInput;
    Switch aSwitch;
    Button shiftButton;

    private boolean decryptBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);

        shiftAmtInput = findViewById(R.id.shift_amt_input);
        plaintext = findViewById(R.id.plaintext);
        ciphertext = findViewById(R.id.ciphertext);
        aSwitch = findViewById(R.id.enc_dec_switch);
        shiftButton = findViewById(R.id.shift_button);

        aSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                decryptBool = isChecked;
            }
        });

        shiftButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                shift();
            }
        });
    }

    public void shift() {
        int shift = 0;

        // Get shift value.
        if(!shiftAmtInput.getText().toString().matches("")) {
            shift = Integer.parseInt(shiftAmtInput.getText().toString()) % 26;
            shiftAmtInput.setText(String.format(Locale.ENGLISH, "%d", shift));
        }

        // Perform encrypt/decrypt
        if(decryptBool){
            String message = ciphertext.getText().toString();

            for(int i = 0; i < message.length(); i++){
                char c = message.charAt(i);

                if(("" + c).matches("[a-zA-Z]")){
                    char newChar = UPPERCASE_LETTERS.charAt((UPPERCASE_LETTERS.indexOf(c) + 26 - shift) % 26);
                    message = message.substring(0, i) + newChar + message.substring(i+1);
                }
            }

            plaintext.setText(message.toLowerCase());
        }
        else {
            String message = plaintext.getText().toString().toLowerCase();

            for(int i = 0; i < message.length(); i++){
                char c = message.charAt(i);
                if(("" + c).matches("[a-zA-Z]")) {
                    char newChar = LOWERCASE_LETTERS.charAt((LOWERCASE_LETTERS.indexOf(c) + shift) % 26);
                    message = message.substring(0, i) + newChar + message.substring(i + 1);
                }
            }

            ciphertext.setText(message.toUpperCase());
        }
    }
}

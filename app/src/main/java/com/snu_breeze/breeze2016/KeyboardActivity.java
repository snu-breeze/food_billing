package com.snu_breeze.breeze2016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class KeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        Intent intent = getIntent();
        final KeyboardView kbv = (KeyboardView) findViewById(R.id.keyboardView);
        kbv.setTitleText(intent.getStringExtra("title"));
        Button btnEnter = (Button) findViewById(R.id.enter_button);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = kbv.getInputText();
                if (input != null) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", input);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }
            }
        });
    }
}

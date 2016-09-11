package com.snu_breeze.breeze2016;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

public class ActivityQRCode extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {
    private QRCodeReaderView qrCodeReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_qrcode);
        Toast.makeText(ActivityQRCode.this, "Launching activity", Toast.LENGTH_SHORT).show();
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        // Enable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);
        // Autofocus interval
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setBackCamera();

        Button btnManual = (Button) findViewById(R.id.manual_user_btn);
        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keyboardActivity = new Intent(ActivityQRCode.this, KeyboardActivity.class);
                keyboardActivity.putExtra("title", "Enter User Identification Number");
                startActivityForResult(keyboardActivity, 2);
            }
        });
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrCodeReaderView.setQRDecodingEnabled(false);
        Toast.makeText(ActivityQRCode.this, text, Toast.LENGTH_SHORT).show();
        Intent keyboardActivity = new Intent(ActivityQRCode.this, KeyboardActivity.class);
        keyboardActivity.putExtra("title", "Enter Item Cost");
        startActivityForResult(keyboardActivity, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                // TODO: Send data to server
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(ActivityQRCode.this, "Error getting input.", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                onQRCodeRead(result, null);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(ActivityQRCode.this, "Error getting input.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
        qrCodeReaderView.setQRDecodingEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
        qrCodeReaderView.setQRDecodingEnabled(false);
    }
}

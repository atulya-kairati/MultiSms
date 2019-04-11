package com.project.multisms;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText msgEditText, noEditText, msgCount, delayText;
    Button sendButton;
    String phoneNO, message;
    int count, delay;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgEditText = findViewById(R.id.msgEditText);
        noEditText = findViewById(R.id.noEditText);
        msgCount = findViewById(R.id.msgCount);
        delayText = findViewById(R.id.delay);
        sendButton = findViewById(R.id.sendButton);
        progressBar = findViewById(R.id.progressBar);


    }

    public void SendMsg(View V){

        phoneNO = noEditText.getText().toString();
        message = msgEditText.getText().toString();
        count = Integer.parseInt(msgCount.getText().toString());
        delay = Integer.parseInt(delayText.getText().toString());

        final SmsManager smsMan = SmsManager.getDefault();

        if( phoneNO.length() == 10 && message != null && count > 0 && delay >=0 ) {

            sendButton.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);

            if (delay == 0) {


                for (int i = 0; i < count; i++) {

                    smsMan.sendTextMessage(phoneNO, null, message, null, null);
                }
                Toast.makeText(getApplicationContext(), "SMS SENT", Toast.LENGTH_LONG).show();
                sendButton.setEnabled(true);
                progressBar.setVisibility(View.GONE);

            } else if (delay > 0) {

                new CountDownTimer(1000 * (long) (count * (delay-1)), 1000 * (long) delay) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        smsMan.sendTextMessage(phoneNO, null, message, null, null);
                        Toast.makeText(getApplicationContext(), "SMS SENT", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFinish() {

                        smsMan.sendTextMessage(phoneNO, null, message, null, null);
                        Toast.makeText(getApplicationContext(), "ALL SENT", Toast.LENGTH_LONG).show();
                        sendButton.setEnabled(true);
                        progressBar.setVisibility(View.GONE);

                    }
                }.start();

            }

        }
    }

}

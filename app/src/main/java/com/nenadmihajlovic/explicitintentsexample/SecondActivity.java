package com.nenadmihajlovic.explicitintentsexample;
/**
 * EXPLICIT INTENTS EXAMPLE - SecondActivity
 * Created by Sillass on 12.2.2016..
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mReceivedTextView;

    //Variables for second way
    private static final String EXTRA_DATA = "com.nenadmihajlovic.explicitintentsexample.receiving";
    private String mReceivedString;



    //For second way
    public static Intent newIntent(Context packageContext, String dataString) {
        Intent i = new Intent(packageContext, SecondActivity.class);
        i.putExtra(EXTRA_DATA, dataString);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mReceivedTextView = (TextView) findViewById(R.id.received_textView_2);

        //First way
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle != null) {
            String dataString = dataBundle.getString(MainActivity.SENDING_KEY);
            if (dataString != null) {
                mReceivedTextView.setText("Sent data: \n" + dataString);
            }
        }

        //Second way
        mReceivedString = getIntent().getStringExtra(EXTRA_DATA);
        if (mReceivedString != null) {
            mReceivedTextView.setText("Sent data: \n" + mReceivedString);
        }

    }
}

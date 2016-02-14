package com.nenadmihajlovic.explicitintentsexample;
/**
 * EXPLICIT INTENTS EXAMPLE - ThirdActivity
 * Created by Sillass on 12.2.2016..
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private static final String EXTRA_RESULT_TWO_WAY = "com.nenadmihajlovic.explicitintentsexample.two_way";

    private TextView mReceivedTextView;
    private TextView mResultTextView;
    private Button mSendButton;

    private String mReceivedString;
    private int mLength;


    public static Intent newIntent(Context packageContext, String dataString) {
        Intent i = new Intent(packageContext, ThirdActivity.class);
        i.putExtra(EXTRA_RESULT_TWO_WAY, dataString);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mReceivedString = getIntent().getStringExtra(EXTRA_RESULT_TWO_WAY);

        mReceivedTextView = (TextView) findViewById(R.id.received_textView_3);
        mReceivedTextView.setText("Received data: \n" + mReceivedString);

        mResultTextView = (TextView) findViewById(R.id.result_textView);
        mLength = mReceivedString.length();
        mResultTextView.setText("Length of received data: \n" + mLength);
        mSendButton = (Button) findViewById(R.id.send_result_button);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //You can put next line into onBackPressed() method. Example is at the bottom.
                setActivityResult(mLength);
                onBackPressed();
            }
        });
    }

    private void setActivityResult(int result) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_RESULT_TWO_WAY, result);
        setResult(RESULT_OK, resultIntent);
    }

    public static int resultIs(Intent resultIntent) {
        return resultIntent.getIntExtra(EXTRA_RESULT_TWO_WAY, 0);
    }

//    @Override
//    public void onBackPressed() {
//        setActivityResult(mLength);
//        super.onBackPressed();
//    }
}

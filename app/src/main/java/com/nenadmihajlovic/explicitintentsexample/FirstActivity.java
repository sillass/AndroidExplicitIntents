package com.nenadmihajlovic.explicitintentsexample;
/**
 * EXPLICIT INTENTS EXAMPLE - FirstActivity
 * Created by Sillass on 12.2.2016..
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    private static final String EXTRA_RESULT = "com.nenadmihajlovic.explicitintentsexample.result";

    private EditText mResultEditText;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mResultEditText = (EditText) findViewById(R.id.result_textView);
        mSendButton = (Button) findViewById(R.id.send_result_button);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //You can put next two lines into onBackPressed() method. Example is at the bottom.
                String resultString = mResultEditText.getText().toString();
                setActivityResult(resultString);
                onBackPressed();
            }
        });
    }

    private void setActivityResult(String result){
        Intent resultIntent= new Intent();
        resultIntent.putExtra(EXTRA_RESULT, result);
        setResult(RESULT_OK, resultIntent);
    }

    public static String resultIs(Intent resultIntent){
        return resultIntent.getStringExtra(EXTRA_RESULT);
    }

//    @Override
//    public void onBackPressed() {
//        String resultString = mResultEditText.getText().toString();
//        setActivityResult(resultString);
//        super.onBackPressed();
//    }
}

package com.example.simio.intelligentmachine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogginActivity extends AppCompatActivity {
    private String stream;
    private EditText editTextIP;
    private static final Pattern IP_ADDRESS
            = Pattern.compile(
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9]))");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        editTextIP = findViewById(R.id.ip_address);
        editTextIP.setText("192.168.0.101");
    }

    public void onClickButton(View v){
        String ipAddress = editTextIP.getText().toString();
        Matcher matcher = IP_ADDRESS.matcher(ipAddress);
        if (matcher.matches()) {
            stream = "http://"+ipAddress+":8000";

            Intent sendIntent = new Intent(getBaseContext(),MainActivity.class);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra("streamAddress", stream);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}

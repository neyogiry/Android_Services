package neyogiry.app.android_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_on;
    Button btn_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_on = (Button)findViewById(R.id.btn_on);
        btn_off = (Button)findViewById(R.id.btn_off);

        btn_on.setOnClickListener(mClickListener);
        btn_off.setOnClickListener(mClickListener);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_on:
                    //Start Service
                    startService(new Intent(MainActivity.this, MyService.class));
                    break;
                case R.id.btn_off:
                    //Stop Service
                    stopService(new Intent(MainActivity.this, MyService.class));
                    break;
            }
        }
    };

}

package tw.sean.codedemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ShowDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        Intent intent = getIntent();
        String startTime = intent.getStringExtra("start");
        String endTime = intent.getStringExtra("end");
        String temperature = intent.getStringExtra("temp");
        String showString = startTime + "\n" + endTime + "\n" + temperature;

        TextView tvData = findViewById(R.id.tvData);
        tvData.getBaseline();
        tvData.setText(showString);
    }
}

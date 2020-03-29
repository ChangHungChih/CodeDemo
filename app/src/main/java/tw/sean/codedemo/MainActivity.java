package tw.sean.codedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tw.sean.codedemo.adapter.WeatherAdapter;
import tw.sean.codedemo.httprequest.WeatherApiRequest;
import tw.sean.codedemo.model.WeatherRes;
import tw.sean.codedemo.utils.Json;

public class MainActivity extends AppCompatActivity {
    private static final String SP_KEY = "preferences";
    private RecyclerView rvWeather;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<WeatherRes.RecordsBean.LocationBean.WeatherElementBean.TimeBean> minTs = getWeatherData();
        weatherAdapter = new WeatherAdapter(minTs);
        initViews();
        checkAndSaveOpenTimes();
    }

    private void initViews() {
        rvWeather = findViewById(R.id.rvWeather);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvWeather.setLayoutManager(layoutManager);
        rvWeather.setAdapter(weatherAdapter);
    }

    private List<WeatherRes.RecordsBean.LocationBean.WeatherElementBean.TimeBean> getWeatherData() {
        String dataString = new WeatherApiRequest().go();
        WeatherRes res = Json.fromJson(dataString, WeatherRes.class);
        List<WeatherRes.RecordsBean.LocationBean.WeatherElementBean> weatherElement =
                res.getRecords().getLocation().get(0).getWeatherElement();
        for (WeatherRes.RecordsBean.LocationBean.WeatherElementBean bean : weatherElement) {
            if (bean.getElementName().equals("MinT")) {
                return bean.getTime();
            }
        }
        return new ArrayList<>();
    }

    private void checkAndSaveOpenTimes() {
        String openKey = "open_times";
        SharedPreferences sp = getSharedPreferences(SP_KEY, MODE_PRIVATE);
        int openTimes = sp.getInt(openKey, 0);
        if (openTimes > 0) {
            Toast.makeText(this, "歡迎回來", Toast.LENGTH_SHORT).show();
        } else {
            sp.edit().putInt(openKey, openTimes + 1).apply();
        }
    }
}

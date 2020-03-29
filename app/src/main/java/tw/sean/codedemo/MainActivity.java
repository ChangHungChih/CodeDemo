package tw.sean.codedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tw.sean.codedemo.adapter.WeatherAdapter;
import tw.sean.codedemo.httprequest.WeatherApiRequest;
import tw.sean.codedemo.model.WeatherRes;
import tw.sean.codedemo.utils.Json;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvWeather;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<WeatherRes.RecordsBean.LocationBean.WeatherElementBean.TimeBean> minTs = getWeatherData();
        weatherAdapter = new WeatherAdapter(minTs);
        initViews();
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
            if(bean.getElementName().equals("MinT")){
                return bean.getTime();
            }
        }
        return new ArrayList<>();
    }
}

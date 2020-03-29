package tw.sean.codedemo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tw.sean.codedemo.R;
import tw.sean.codedemo.ShowDataActivity;
import tw.sean.codedemo.model.WeatherRes;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WeatherRes.RecordsBean.LocationBean.WeatherElementBean.TimeBean> weatherData;

    public WeatherAdapter(List<WeatherRes.RecordsBean.LocationBean.WeatherElementBean.TimeBean> weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType > 0) {
            return new ImageViewHolder(inflater.inflate(R.layout.rv_weather_image, parent, false));
        }
        return new DataViewHolder(inflater.inflate(R.layout.rv_weather_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            final int index = position / 2;
            final WeatherRes.RecordsBean.LocationBean.WeatherElementBean.TimeBean bean = weatherData.get(index);
            ((DataViewHolder) holder).tvStartTime.setText(bean.getStartTime());
            ((DataViewHolder) holder).tvEndTime.setText(bean.getEndTime());
            final String temperatureString = bean.getParameter().getParameterName() + bean.getParameter().getParameterUnit();
            ((DataViewHolder) holder).tvTemperature.setText(temperatureString);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), ShowDataActivity.class)
                            .putExtra("start", bean.getStartTime())
                            .putExtra("end", bean.getEndTime())
                            .putExtra("temp", temperatureString);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return weatherData.size() * 2;
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime;
        TextView tvEndTime;
        TextView tvTemperature;

        DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            tvEndTime = itemView.findViewById(R.id.tvEndTime);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

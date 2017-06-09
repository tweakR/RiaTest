package ria.com.riatest.ui.fragment.mainscreen.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ria.com.riatest.BR;
import ria.com.riatest.R;
import ria.com.riatest.constant.Constant;
import ria.com.riatest.databinding.ItemMainScreenBinding;
import ria.com.riatest.model.WeatherModel;
import ria.com.riatest.ui.activity.SelectedActivity;
import ria.com.riatest.ui.core.view.adapter.BindingHolder;
import ria.com.riatest.ui.fragment.detailed.view.WeatherDetailedFragment;

public class MainScreenAdapter extends RecyclerView.Adapter<BindingHolder<ItemMainScreenBinding>> {

    private List<WeatherModel> originalWeatherList;
    private List<WeatherModel> tempList;

    private Context context;

    public MainScreenAdapter(Context context) {
        originalWeatherList = new ArrayList<>();
        this.context = context;
        tempList = new ArrayList<>();
    }

    @Override
    public BindingHolder<ItemMainScreenBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMainScreenBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_main_screen, parent, false);
        return new BindingHolder<ItemMainScreenBinding>(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder<ItemMainScreenBinding> holder, int position) {
        holder.binding.setVariable(BR.model, originalWeatherList.get(position));

        holder.binding.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.WEATHER, originalWeatherList.get(position));
            SelectedActivity.startActivityWithFragment(context, WeatherDetailedFragment.class.getName(),
                    bundle, context.getString(R.string.title_weather_details));
        });

    }

    @Override
    public int getItemCount() {
        return originalWeatherList.size();
    }

    public void setWeatherList(List<WeatherModel> originalWeatherList) {
        this.originalWeatherList = originalWeatherList;
        notifyDataSetChanged();
    }

    public void set3DaysWeather() {
        List<WeatherModel> customWeatherList = new ArrayList<>();
        customWeatherList = copyArray(originalWeatherList);
        for (int i = 3; i <= 6; i++) {
            tempList.add(customWeatherList.get(i));
        }
        for (int i = 6; i >= 3; i--) {
            customWeatherList.remove(i);
        }
        setWeatherList(customWeatherList);
    }

    private List<WeatherModel> copyArray(List<WeatherModel> source) {
        List<WeatherModel> destination = new ArrayList<>();
        destination.addAll(source);
        return destination;
    }

    public void set7DaysWeather() {
        for (WeatherModel item : tempList) {
            originalWeatherList.add(item);
        }
        tempList.clear();
        setWeatherList(originalWeatherList);
    }
}

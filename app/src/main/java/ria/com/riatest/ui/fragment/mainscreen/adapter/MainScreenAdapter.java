package ria.com.riatest.ui.fragment.mainscreen.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ria.com.riatest.R;
import ria.com.riatest.api.response.ListResponse;
import ria.com.riatest.constant.Constant;
import ria.com.riatest.databinding.ItemMainScreenBinding;
import ria.com.riatest.ui.core.view.adapter.BindingHolder;
import ria.com.riatest.util.DateConverter;

public class MainScreenAdapter extends RecyclerView.Adapter<BindingHolder<ItemMainScreenBinding>> {

    private List<ListResponse> originalWeatherList;
    private List<ListResponse> tempList;

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
        holder.binding.maxTemperature.setText(String.valueOf(((int) originalWeatherList.get(position).getTemp().getMax())));
        holder.binding.minTemperature.setText(String.valueOf(((int) originalWeatherList.get(position).getTemp().getMin())));
        holder.binding.date.setText(DateConverter.convertDate(originalWeatherList.get(position).getDt()));
        Picasso.with(context.getApplicationContext())
                .load(Constant.ICON_URL + originalWeatherList.get(position).getWeather().get(0).getIcon() + ".png")
                .into(holder.binding.icon);
    }

    @Override
    public int getItemCount() {
        return originalWeatherList.size();
    }

    public void setWeatherList(List<ListResponse> originalWeatherList) {
        this.originalWeatherList = originalWeatherList;
        notifyDataSetChanged();
    }

    public void set3DaysWeather() {
        List<ListResponse> customWeatherList = new ArrayList<>();
        customWeatherList = copyArray(originalWeatherList);
        for (int i = 3; i <= 6; i++) {
            tempList.add(customWeatherList.get(i));
        }
        for (int i = 6; i >= 3; i--) {
            customWeatherList.remove(i);
        }
        setWeatherList(customWeatherList);
    }

    private List<ListResponse> copyArray(List<ListResponse> source) {
        List<ListResponse> destination = new ArrayList<>();
        destination.addAll(source);
        return destination;
    }

    public void set7DaysWeather() {
        for (ListResponse item : tempList) {
            originalWeatherList.add(item);
        }
        tempList.clear();
        setWeatherList(originalWeatherList);
    }
}

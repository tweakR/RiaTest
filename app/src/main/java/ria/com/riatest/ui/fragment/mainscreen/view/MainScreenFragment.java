package ria.com.riatest.ui.fragment.mainscreen.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import ria.com.riatest.R;
import ria.com.riatest.RiaApplication;
import ria.com.riatest.databinding.FragmentMainScreenBinding;
import ria.com.riatest.db.entities.WeatherData;
import ria.com.riatest.ui.core.view.CoreFragment;
import ria.com.riatest.ui.fragment.mainscreen.adapter.MainScreenAdapter;
import ria.com.riatest.ui.fragment.mainscreen.presenter.MainScreenPresenter;

public class MainScreenFragment extends CoreFragment<MainScreenPresenter, FragmentMainScreenBinding>
        implements MainScreenView {

    private MainScreenAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MainScreenAdapter(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_screen;
    }

    @Override
    protected void inject() {
        RiaApplication.getInstance().getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!checkInternetConnection()) {
            getPresenter().getFromDb();
        } else {
            getPresenter().getWeather("London");
        }
        getDataBinding().daySwitcher.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                adapter.set3DaysWeather();
            } else {
                adapter.set7DaysWeather();
            }
        });
    }

    @Override
    public void setWeatherList(List<WeatherData> list) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        getDataBinding().list.setLayoutManager(manager);
        getDataBinding().list.setAdapter(adapter);
        adapter.setWeatherList(list);
    }
}

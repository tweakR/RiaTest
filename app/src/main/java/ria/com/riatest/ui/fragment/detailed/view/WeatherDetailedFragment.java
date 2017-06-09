package ria.com.riatest.ui.fragment.detailed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.squareup.picasso.Picasso;

import ria.com.riatest.R;
import ria.com.riatest.RiaApplication;
import ria.com.riatest.constant.Constant;
import ria.com.riatest.databinding.FragmentWeatherDetailedBinding;
import ria.com.riatest.model.WeatherModel;
import ria.com.riatest.ui.core.view.CoreFragment;
import ria.com.riatest.ui.fragment.detailed.presenter.WeatherDetailedPresenter;

public class WeatherDetailedFragment extends CoreFragment<WeatherDetailedPresenter, FragmentWeatherDetailedBinding>
        implements WeatherDetailedView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather_detailed;
    }

    @Override
    protected void inject() {
        RiaApplication.getInstance().getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WeatherModel model = getArguments().getParcelable(Constant.WEATHER);
        initViewElements(model);
    }

    private void initViewElements(WeatherModel model) {
        getDataBinding().tvDate.setText(model.getDate());
        getDataBinding().tvMaxTemperature.setText(model.getMaxTemperature());
        getDataBinding().tvMinTemperature.setText(model.getMinTemperature());
        getDataBinding().tvValueHumidity.setText(model.getHumidity());
        getDataBinding().tvValuePressure.setText(model.getPressure());
        getDataBinding().tvDescription.setText(model.getDescription());
        Picasso.with(getActivity())
                .load(model.getIcon())
                .into(getDataBinding().ivWeather);
    }
}

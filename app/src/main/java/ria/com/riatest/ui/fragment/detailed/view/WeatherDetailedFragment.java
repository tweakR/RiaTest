package ria.com.riatest.ui.fragment.detailed.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ria.com.riatest.R;
import ria.com.riatest.RiaApplication;
import ria.com.riatest.constant.Constant;
import ria.com.riatest.databinding.FragmentWeatherDetailedBinding;
import ria.com.riatest.ui.core.view.CoreFragment;
import ria.com.riatest.ui.fragment.detailed.presenter.WeatherDetailedPresenter;
import ria.com.riatest.ui.fragment.mainscreen.bindmodel.WeatherViewModel;

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
        WeatherViewModel model = getArguments().getParcelable(Constant.WEATHER);
        getDataBinding().setModel(model);
    }
}

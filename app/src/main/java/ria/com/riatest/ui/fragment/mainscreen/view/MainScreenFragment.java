package ria.com.riatest.ui.fragment.mainscreen.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
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
    private List<String> defaultCities;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MainScreenAdapter(getActivity());
        defaultCities = new ArrayList<>();
        defaultCities.add(0, "Vynnitsia");
        defaultCities.add(1, "Kiev");
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
        setHasOptionsMenu(true);
        getPresenter().getCity();

        if (!checkInternetConnection()) {
            getPresenter().getDataFromDb();
        }

        getDataBinding().fab.setOnClickListener(v -> {
            showAddCityDialog();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_action:
                showPopupMenu();
                break;
        }
        return true;
    }

    private void showPopupMenu() {
        PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.filter_action));
        popup.getMenuInflater().inflate(R.menu.menu_filter, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.weather_3_days:
                    adapter.set3DaysWeather();
                    break;
                case R.id.weather_7_days:
                    adapter.set7DaysWeather();
                    break;
            }
            return true;
        });
        popup.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_main_screen_menu, menu);
    }


    @Override
    public void setWeatherList(List<WeatherData> list) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        getDataBinding().list.setLayoutManager(manager);
        getDataBinding().list.setAdapter(adapter);
        adapter.setWeatherList(list);
    }

    private void showAddCityDialog() {
        View dialogView = View.inflate(getContext(), R.layout.dialog_add_city, null);
        EditText editText = (EditText) dialogView.findViewById(R.id.et_add_city);

        new AlertDialog.Builder(getContext())
                .setTitle("Add your city")
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    String city = editText.getText().toString();
                    addCity(city);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }


    @Override
    public void setCity(List<String> cityNames) {
        setupArrayAdapter(cityNames);
        getDataBinding().spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city = cityNames.get(i);
                if (checkInternetConnection()) {
                    getPresenter().getWeather(city);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setupArrayAdapter(List<String> cityNames) {
        cityNames.addAll(defaultCities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, cityNames);
        getDataBinding().spCity.setAdapter(adapter);
    }

    private void addCity(String addedCity) {
        getPresenter().saveCityName(addedCity);
    }

}

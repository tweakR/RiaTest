package ria.com.riatest.ui.activity;

import android.os.Bundle;

import ria.com.riatest.R;
import ria.com.riatest.databinding.ActivityMainBinding;
import ria.com.riatest.ui.core.activity.BaseActivity;
import ria.com.riatest.ui.fragment.mainscreen.view.MainScreenFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MainScreenFragment(), MainScreenFragment.class.getName())
                .commit();
    }
}

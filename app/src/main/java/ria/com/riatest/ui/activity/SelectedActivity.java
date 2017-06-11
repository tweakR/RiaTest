package ria.com.riatest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.WindowManager;

import ria.com.riatest.R;
import ria.com.riatest.databinding.SelectedActivityBinding;
import ria.com.riatest.ui.core.activity.BaseActivity;


public class SelectedActivity extends BaseActivity<SelectedActivityBinding> {

    public static final String TITLE = "title";
    private static final String FRAGMENT_NAME = "fragment_name";

    public static void startActivityWithFragment(Context context, String fragmentName, Bundle bundle, String title) {
        bundle.putString(FRAGMENT_NAME, fragmentName);
        bundle.putString(TITLE, title);
        Intent intent = new Intent(context, SelectedActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.selected_activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        String fragmentName = extras.getString(FRAGMENT_NAME);
        String title = extras.getString(TITLE);

        getSupportActionBar().setTitle(title);

        Fragment fragment = Fragment.instantiate(this, fragmentName, extras);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.selected_container, fragment)
                .commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = super.onOptionsItemSelected(item);
        if (!result) {
            if (item.getItemId() == android.R.id.home) {
                super.onBackPressed();
                return true;
            }
        }
        return result;
    }
}

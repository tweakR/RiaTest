package ria.com.riatest.ui.core.activity;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import dmax.dialog.SpotsDialog;
import ria.com.riatest.R;


public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected B binding;
    private SpotsDialog progress;
    private short progressCounter;
    private FragmentManager fragmentManager;

    public static void startActivityAsRoot(Activity prevActivity, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        prevActivity.startActivity(intent);
        ActivityCompat.finishAffinity(prevActivity);
    }

    public abstract int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    public void setToolbarTitle(String title) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }

    public void showProgress() {
        progressCounter++;
        runOnUiThread(() -> {
            if (progress == null) {
                progress = new SpotsDialog(this, R.style.Custom);
                progress.setOnCancelListener(dialogInterface -> progressCounter = 0);
                progress.setCancelable(true);
            }
            if (!progress.isShowing()) {
                progress.show();
            }
        });
    }

    public void hideProgress() {
        if (progressCounter > 0) progressCounter--;
        runOnUiThread(() -> {
            if (progress != null && progress.isShowing() && progressCounter <= 0) {
                progress.dismiss();
            }
        });
    }

}

package com.example.ironsourcedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class MainActivity extends AppCompatActivity implements InterstitialListener {

    private final String APP_KEY = "85460dcd";

    private final static String Interstitial_TAG = "interstitial_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInterstitial = findViewById(R.id.btn_load_interstitial);

        btnInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IronSource.loadInterstitial();
            }
        });
        IntegrationHelper.validateIntegration(this);

        IronSource.init(this, APP_KEY, IronSource.AD_UNIT.INTERSTITIAL);
        IronSource.setInterstitialListener(this);
    }

    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

    @Override
    public void onInterstitialAdReady() {
        Log.d(Interstitial_TAG, "ready");
        if (!MainActivity.this.isFinishing() && !MainActivity.this.isDestroyed()) {
            IronSource.showInterstitial();
        }
    }

    @Override
    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
        Log.d(Interstitial_TAG, "load failed");

    }

    @Override
    public void onInterstitialAdOpened() {

    }

    @Override
    public void onInterstitialAdClosed() {

    }

    @Override
    public void onInterstitialAdShowSucceeded() {
        Log.d(Interstitial_TAG, "show success");

    }

    @Override
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onInterstitialAdClicked() {

    }
}
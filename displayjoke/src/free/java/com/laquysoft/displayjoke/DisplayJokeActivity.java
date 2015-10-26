package com.laquysoft.displayjoke;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.laquysoft.displayjoke.R;


public class DisplayJokeActivity extends ActionBarActivity {

    private static final String LOG_TAG = DisplayJokeActivity.class.getSimpleName();
    InterstitialAd mInterstitialAd;

    String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayjoke_activity);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
                mJoke = getIntent().getStringExtra("jokestring");
                tellJoke(mJoke);
            }
        });
        requestNewInterstitial();

        if(mInterstitialAd.isLoaded()){
            Log.d(LOG_TAG, "mInterstitialAd loaded");
            mInterstitialAd.show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(String joke){
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("C8FD96A7288C5DA600C5ED87CA38B207")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }
}

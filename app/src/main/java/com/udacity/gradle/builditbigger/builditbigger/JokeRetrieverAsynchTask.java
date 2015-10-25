package com.udacity.gradle.builditbigger.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.laquysoft.builditbigger.backend.myApi.MyApi;
import com.laquysoft.displayjoke.DisplayJokeActivity;

import java.io.IOException;

/**
 * Created by joaobiriba on 24/10/2015.
 */
class JokeRetrieverAsynchTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokestelling-1108.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent myIntent = new Intent(context, DisplayJokeActivity.class);
        myIntent.putExtra("jokestring", result);
        context.startActivity(myIntent);
    }
}
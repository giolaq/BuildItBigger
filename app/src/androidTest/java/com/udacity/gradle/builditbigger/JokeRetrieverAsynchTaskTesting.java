package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.laquysoft.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.JokeRetrieverAsynchTask;

import java.io.IOException;

/**
 * Created by joaobiriba on 24/10/2015.
 */
public class JokeRetrieverAsynchTaskTesting extends AndroidTestCase {
    public void testVerifyNotEmptyJoke() {

        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://jokestelling-1108.appspot.com/_ah/api/");

        MyApi myApiService = builder.build();
        try {
            assertTrue(!"".equals(myApiService.tellJoke().execute().getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

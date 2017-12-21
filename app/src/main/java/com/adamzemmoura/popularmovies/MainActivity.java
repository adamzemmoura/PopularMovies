package com.adamzemmoura.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private final String TMDb_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    private final String API_KEY = getResources().getString(R.string.api_key);
    private final String CHOSEN_LANG = "en-US";
    private final String POPULAR_MOVIES_URL = TMDb_BASE_URL + "?api_key=" + API_KEY
            + "&language=" + CHOSEN_LANG;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tv_movie_data_display);

        fetchPopularMoviesJsonData();
    }

    private void fetchPopularMoviesJsonData() {


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(POPULAR_MOVIES_URL)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.v(TAG, response.body().string());
                } else {
                    // TODO : alert user about error using Alert Dialog Fragment
                }
            }
        });

    }

}
